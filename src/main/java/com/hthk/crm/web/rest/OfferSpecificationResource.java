package com.hthk.crm.web.rest;

import com.hthk.crm.domain.OfferSpecification;
import com.hthk.crm.service.OfferSpecificationService;
import com.hthk.crm.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.hthk.crm.domain.OfferSpecification}.
 */
@RestController
@RequestMapping("/api")
public class OfferSpecificationResource {

    private final Logger log = LoggerFactory.getLogger(OfferSpecificationResource.class);

    private static final String ENTITY_NAME = "offerSpecification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OfferSpecificationService offerSpecificationService;

    public OfferSpecificationResource(OfferSpecificationService offerSpecificationService) {
        this.offerSpecificationService = offerSpecificationService;
    }

    /**
     * {@code POST  /offer-specifications} : Create a new offerSpecification.
     *
     * @param offerSpecification the offerSpecification to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new offerSpecification, or with status {@code 400 (Bad Request)} if the offerSpecification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/offer-specifications")
    public ResponseEntity<OfferSpecification> createOfferSpecification(@Valid @RequestBody OfferSpecification offerSpecification) throws URISyntaxException {
        log.debug("REST request to save OfferSpecification : {}", offerSpecification);
        if (offerSpecification.getId() != null) {
            throw new BadRequestAlertException("A new offerSpecification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OfferSpecification result = offerSpecificationService.save(offerSpecification);
        return ResponseEntity.created(new URI("/api/offer-specifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /offer-specifications} : Updates an existing offerSpecification.
     *
     * @param offerSpecification the offerSpecification to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated offerSpecification,
     * or with status {@code 400 (Bad Request)} if the offerSpecification is not valid,
     * or with status {@code 500 (Internal Server Error)} if the offerSpecification couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/offer-specifications")
    public ResponseEntity<OfferSpecification> updateOfferSpecification(@Valid @RequestBody OfferSpecification offerSpecification) throws URISyntaxException {
        log.debug("REST request to update OfferSpecification : {}", offerSpecification);
        if (offerSpecification.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OfferSpecification result = offerSpecificationService.save(offerSpecification);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, offerSpecification.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /offer-specifications} : get all the offerSpecifications.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of offerSpecifications in body.
     */
    @GetMapping("/offer-specifications")
    public ResponseEntity<List<OfferSpecification>> getAllOfferSpecifications(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("offer-is-null".equals(filter)) {
            log.debug("REST request to get all OfferSpecifications where offer is null");
            return new ResponseEntity<>(offerSpecificationService.findAllWhereOfferIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of OfferSpecifications");
        Page<OfferSpecification> page = offerSpecificationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /offer-specifications/:id} : get the "id" offerSpecification.
     *
     * @param id the id of the offerSpecification to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the offerSpecification, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/offer-specifications/{id}")
    public ResponseEntity<OfferSpecification> getOfferSpecification(@PathVariable String id) {
        log.debug("REST request to get OfferSpecification : {}", id);
        Optional<OfferSpecification> offerSpecification = offerSpecificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(offerSpecification);
    }

    /**
     * {@code DELETE  /offer-specifications/:id} : delete the "id" offerSpecification.
     *
     * @param id the id of the offerSpecification to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/offer-specifications/{id}")
    public ResponseEntity<Void> deleteOfferSpecification(@PathVariable String id) {
        log.debug("REST request to delete OfferSpecification : {}", id);
        offerSpecificationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
