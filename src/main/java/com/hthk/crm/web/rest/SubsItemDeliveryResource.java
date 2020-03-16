package com.hthk.crm.web.rest;

import com.hthk.crm.domain.SubsItemDelivery;
import com.hthk.crm.service.SubsItemDeliveryService;
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
 * REST controller for managing {@link com.hthk.crm.domain.SubsItemDelivery}.
 */
@RestController
@RequestMapping("/api")
public class SubsItemDeliveryResource {

    private final Logger log = LoggerFactory.getLogger(SubsItemDeliveryResource.class);

    private static final String ENTITY_NAME = "subsItemDelivery";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SubsItemDeliveryService subsItemDeliveryService;

    public SubsItemDeliveryResource(SubsItemDeliveryService subsItemDeliveryService) {
        this.subsItemDeliveryService = subsItemDeliveryService;
    }

    /**
     * {@code POST  /subs-item-deliveries} : Create a new subsItemDelivery.
     *
     * @param subsItemDelivery the subsItemDelivery to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subsItemDelivery, or with status {@code 400 (Bad Request)} if the subsItemDelivery has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/subs-item-deliveries")
    public ResponseEntity<SubsItemDelivery> createSubsItemDelivery(@Valid @RequestBody SubsItemDelivery subsItemDelivery) throws URISyntaxException {
        log.debug("REST request to save SubsItemDelivery : {}", subsItemDelivery);
        if (subsItemDelivery.getId() != null) {
            throw new BadRequestAlertException("A new subsItemDelivery cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SubsItemDelivery result = subsItemDeliveryService.save(subsItemDelivery);
        return ResponseEntity.created(new URI("/api/subs-item-deliveries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /subs-item-deliveries} : Updates an existing subsItemDelivery.
     *
     * @param subsItemDelivery the subsItemDelivery to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subsItemDelivery,
     * or with status {@code 400 (Bad Request)} if the subsItemDelivery is not valid,
     * or with status {@code 500 (Internal Server Error)} if the subsItemDelivery couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/subs-item-deliveries")
    public ResponseEntity<SubsItemDelivery> updateSubsItemDelivery(@Valid @RequestBody SubsItemDelivery subsItemDelivery) throws URISyntaxException {
        log.debug("REST request to update SubsItemDelivery : {}", subsItemDelivery);
        if (subsItemDelivery.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SubsItemDelivery result = subsItemDeliveryService.save(subsItemDelivery);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, subsItemDelivery.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /subs-item-deliveries} : get all the subsItemDeliveries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subsItemDeliveries in body.
     */
    @GetMapping("/subs-item-deliveries")
    public ResponseEntity<List<SubsItemDelivery>> getAllSubsItemDeliveries(Pageable pageable) {
        log.debug("REST request to get a page of SubsItemDeliveries");
        Page<SubsItemDelivery> page = subsItemDeliveryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /subs-item-deliveries/:id} : get the "id" subsItemDelivery.
     *
     * @param id the id of the subsItemDelivery to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subsItemDelivery, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/subs-item-deliveries/{id}")
    public ResponseEntity<SubsItemDelivery> getSubsItemDelivery(@PathVariable String id) {
        log.debug("REST request to get SubsItemDelivery : {}", id);
        Optional<SubsItemDelivery> subsItemDelivery = subsItemDeliveryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(subsItemDelivery);
    }

    /**
     * {@code DELETE  /subs-item-deliveries/:id} : delete the "id" subsItemDelivery.
     *
     * @param id the id of the subsItemDelivery to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/subs-item-deliveries/{id}")
    public ResponseEntity<Void> deleteSubsItemDelivery(@PathVariable String id) {
        log.debug("REST request to delete SubsItemDelivery : {}", id);
        subsItemDeliveryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
