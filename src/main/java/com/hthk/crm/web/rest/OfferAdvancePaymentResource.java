package com.hthk.crm.web.rest;

import com.hthk.crm.domain.OfferAdvancePayment;
import com.hthk.crm.service.OfferAdvancePaymentService;
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

/**
 * REST controller for managing {@link com.hthk.crm.domain.OfferAdvancePayment}.
 */
@RestController
@RequestMapping("/api")
public class OfferAdvancePaymentResource {

    private final Logger log = LoggerFactory.getLogger(OfferAdvancePaymentResource.class);

    private static final String ENTITY_NAME = "offerAdvancePayment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OfferAdvancePaymentService offerAdvancePaymentService;

    public OfferAdvancePaymentResource(OfferAdvancePaymentService offerAdvancePaymentService) {
        this.offerAdvancePaymentService = offerAdvancePaymentService;
    }

    /**
     * {@code POST  /offer-advance-payments} : Create a new offerAdvancePayment.
     *
     * @param offerAdvancePayment the offerAdvancePayment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new offerAdvancePayment, or with status {@code 400 (Bad Request)} if the offerAdvancePayment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/offer-advance-payments")
    public ResponseEntity<OfferAdvancePayment> createOfferAdvancePayment(@Valid @RequestBody OfferAdvancePayment offerAdvancePayment) throws URISyntaxException {
        log.debug("REST request to save OfferAdvancePayment : {}", offerAdvancePayment);
        if (offerAdvancePayment.getId() != null) {
            throw new BadRequestAlertException("A new offerAdvancePayment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OfferAdvancePayment result = offerAdvancePaymentService.save(offerAdvancePayment);
        return ResponseEntity.created(new URI("/api/offer-advance-payments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /offer-advance-payments} : Updates an existing offerAdvancePayment.
     *
     * @param offerAdvancePayment the offerAdvancePayment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated offerAdvancePayment,
     * or with status {@code 400 (Bad Request)} if the offerAdvancePayment is not valid,
     * or with status {@code 500 (Internal Server Error)} if the offerAdvancePayment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/offer-advance-payments")
    public ResponseEntity<OfferAdvancePayment> updateOfferAdvancePayment(@Valid @RequestBody OfferAdvancePayment offerAdvancePayment) throws URISyntaxException {
        log.debug("REST request to update OfferAdvancePayment : {}", offerAdvancePayment);
        if (offerAdvancePayment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OfferAdvancePayment result = offerAdvancePaymentService.save(offerAdvancePayment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, offerAdvancePayment.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /offer-advance-payments} : get all the offerAdvancePayments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of offerAdvancePayments in body.
     */
    @GetMapping("/offer-advance-payments")
    public ResponseEntity<List<OfferAdvancePayment>> getAllOfferAdvancePayments(Pageable pageable) {
        log.debug("REST request to get a page of OfferAdvancePayments");
        Page<OfferAdvancePayment> page = offerAdvancePaymentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /offer-advance-payments/:id} : get the "id" offerAdvancePayment.
     *
     * @param id the id of the offerAdvancePayment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the offerAdvancePayment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/offer-advance-payments/{id}")
    public ResponseEntity<OfferAdvancePayment> getOfferAdvancePayment(@PathVariable String id) {
        log.debug("REST request to get OfferAdvancePayment : {}", id);
        Optional<OfferAdvancePayment> offerAdvancePayment = offerAdvancePaymentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(offerAdvancePayment);
    }

    /**
     * {@code DELETE  /offer-advance-payments/:id} : delete the "id" offerAdvancePayment.
     *
     * @param id the id of the offerAdvancePayment to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/offer-advance-payments/{id}")
    public ResponseEntity<Void> deleteOfferAdvancePayment(@PathVariable String id) {
        log.debug("REST request to delete OfferAdvancePayment : {}", id);
        offerAdvancePaymentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
