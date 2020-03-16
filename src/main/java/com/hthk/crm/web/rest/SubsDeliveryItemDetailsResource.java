package com.hthk.crm.web.rest;

import com.hthk.crm.domain.SubsDeliveryItemDetails;
import com.hthk.crm.service.SubsDeliveryItemDetailsService;
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
 * REST controller for managing {@link com.hthk.crm.domain.SubsDeliveryItemDetails}.
 */
@RestController
@RequestMapping("/api")
public class SubsDeliveryItemDetailsResource {

    private final Logger log = LoggerFactory.getLogger(SubsDeliveryItemDetailsResource.class);

    private static final String ENTITY_NAME = "subsDeliveryItemDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SubsDeliveryItemDetailsService subsDeliveryItemDetailsService;

    public SubsDeliveryItemDetailsResource(SubsDeliveryItemDetailsService subsDeliveryItemDetailsService) {
        this.subsDeliveryItemDetailsService = subsDeliveryItemDetailsService;
    }

    /**
     * {@code POST  /subs-delivery-item-details} : Create a new subsDeliveryItemDetails.
     *
     * @param subsDeliveryItemDetails the subsDeliveryItemDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subsDeliveryItemDetails, or with status {@code 400 (Bad Request)} if the subsDeliveryItemDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/subs-delivery-item-details")
    public ResponseEntity<SubsDeliveryItemDetails> createSubsDeliveryItemDetails(@Valid @RequestBody SubsDeliveryItemDetails subsDeliveryItemDetails) throws URISyntaxException {
        log.debug("REST request to save SubsDeliveryItemDetails : {}", subsDeliveryItemDetails);
        if (subsDeliveryItemDetails.getId() != null) {
            throw new BadRequestAlertException("A new subsDeliveryItemDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SubsDeliveryItemDetails result = subsDeliveryItemDetailsService.save(subsDeliveryItemDetails);
        return ResponseEntity.created(new URI("/api/subs-delivery-item-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /subs-delivery-item-details} : Updates an existing subsDeliveryItemDetails.
     *
     * @param subsDeliveryItemDetails the subsDeliveryItemDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subsDeliveryItemDetails,
     * or with status {@code 400 (Bad Request)} if the subsDeliveryItemDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the subsDeliveryItemDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/subs-delivery-item-details")
    public ResponseEntity<SubsDeliveryItemDetails> updateSubsDeliveryItemDetails(@Valid @RequestBody SubsDeliveryItemDetails subsDeliveryItemDetails) throws URISyntaxException {
        log.debug("REST request to update SubsDeliveryItemDetails : {}", subsDeliveryItemDetails);
        if (subsDeliveryItemDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SubsDeliveryItemDetails result = subsDeliveryItemDetailsService.save(subsDeliveryItemDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, subsDeliveryItemDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /subs-delivery-item-details} : get all the subsDeliveryItemDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subsDeliveryItemDetails in body.
     */
    @GetMapping("/subs-delivery-item-details")
    public ResponseEntity<List<SubsDeliveryItemDetails>> getAllSubsDeliveryItemDetails(Pageable pageable) {
        log.debug("REST request to get a page of SubsDeliveryItemDetails");
        Page<SubsDeliveryItemDetails> page = subsDeliveryItemDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /subs-delivery-item-details/:id} : get the "id" subsDeliveryItemDetails.
     *
     * @param id the id of the subsDeliveryItemDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subsDeliveryItemDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/subs-delivery-item-details/{id}")
    public ResponseEntity<SubsDeliveryItemDetails> getSubsDeliveryItemDetails(@PathVariable String id) {
        log.debug("REST request to get SubsDeliveryItemDetails : {}", id);
        Optional<SubsDeliveryItemDetails> subsDeliveryItemDetails = subsDeliveryItemDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(subsDeliveryItemDetails);
    }

    /**
     * {@code DELETE  /subs-delivery-item-details/:id} : delete the "id" subsDeliveryItemDetails.
     *
     * @param id the id of the subsDeliveryItemDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/subs-delivery-item-details/{id}")
    public ResponseEntity<Void> deleteSubsDeliveryItemDetails(@PathVariable String id) {
        log.debug("REST request to delete SubsDeliveryItemDetails : {}", id);
        subsDeliveryItemDetailsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
