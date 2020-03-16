package com.hthk.crm.web.rest;

import com.hthk.crm.domain.SubsOrderDetails;
import com.hthk.crm.service.SubsOrderDetailsService;
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
 * REST controller for managing {@link com.hthk.crm.domain.SubsOrderDetails}.
 */
@RestController
@RequestMapping("/api")
public class SubsOrderDetailsResource {

    private final Logger log = LoggerFactory.getLogger(SubsOrderDetailsResource.class);

    private static final String ENTITY_NAME = "subsOrderDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SubsOrderDetailsService subsOrderDetailsService;

    public SubsOrderDetailsResource(SubsOrderDetailsService subsOrderDetailsService) {
        this.subsOrderDetailsService = subsOrderDetailsService;
    }

    /**
     * {@code POST  /subs-order-details} : Create a new subsOrderDetails.
     *
     * @param subsOrderDetails the subsOrderDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subsOrderDetails, or with status {@code 400 (Bad Request)} if the subsOrderDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/subs-order-details")
    public ResponseEntity<SubsOrderDetails> createSubsOrderDetails(@Valid @RequestBody SubsOrderDetails subsOrderDetails) throws URISyntaxException {
        log.debug("REST request to save SubsOrderDetails : {}", subsOrderDetails);
        if (subsOrderDetails.getId() != null) {
            throw new BadRequestAlertException("A new subsOrderDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SubsOrderDetails result = subsOrderDetailsService.save(subsOrderDetails);
        return ResponseEntity.created(new URI("/api/subs-order-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /subs-order-details} : Updates an existing subsOrderDetails.
     *
     * @param subsOrderDetails the subsOrderDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subsOrderDetails,
     * or with status {@code 400 (Bad Request)} if the subsOrderDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the subsOrderDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/subs-order-details")
    public ResponseEntity<SubsOrderDetails> updateSubsOrderDetails(@Valid @RequestBody SubsOrderDetails subsOrderDetails) throws URISyntaxException {
        log.debug("REST request to update SubsOrderDetails : {}", subsOrderDetails);
        if (subsOrderDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SubsOrderDetails result = subsOrderDetailsService.save(subsOrderDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, subsOrderDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /subs-order-details} : get all the subsOrderDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subsOrderDetails in body.
     */
    @GetMapping("/subs-order-details")
    public ResponseEntity<List<SubsOrderDetails>> getAllSubsOrderDetails(Pageable pageable) {
        log.debug("REST request to get a page of SubsOrderDetails");
        Page<SubsOrderDetails> page = subsOrderDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /subs-order-details/:id} : get the "id" subsOrderDetails.
     *
     * @param id the id of the subsOrderDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subsOrderDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/subs-order-details/{id}")
    public ResponseEntity<SubsOrderDetails> getSubsOrderDetails(@PathVariable String id) {
        log.debug("REST request to get SubsOrderDetails : {}", id);
        Optional<SubsOrderDetails> subsOrderDetails = subsOrderDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(subsOrderDetails);
    }

    /**
     * {@code DELETE  /subs-order-details/:id} : delete the "id" subsOrderDetails.
     *
     * @param id the id of the subsOrderDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/subs-order-details/{id}")
    public ResponseEntity<Void> deleteSubsOrderDetails(@PathVariable String id) {
        log.debug("REST request to delete SubsOrderDetails : {}", id);
        subsOrderDetailsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
