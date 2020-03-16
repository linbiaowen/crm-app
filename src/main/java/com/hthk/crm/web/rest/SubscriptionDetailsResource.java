package com.hthk.crm.web.rest;

import com.hthk.crm.domain.SubscriptionDetails;
import com.hthk.crm.service.SubscriptionDetailsService;
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
 * REST controller for managing {@link com.hthk.crm.domain.SubscriptionDetails}.
 */
@RestController
@RequestMapping("/api")
public class SubscriptionDetailsResource {

    private final Logger log = LoggerFactory.getLogger(SubscriptionDetailsResource.class);

    private static final String ENTITY_NAME = "subscriptionDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SubscriptionDetailsService subscriptionDetailsService;

    public SubscriptionDetailsResource(SubscriptionDetailsService subscriptionDetailsService) {
        this.subscriptionDetailsService = subscriptionDetailsService;
    }

    /**
     * {@code POST  /subscription-details} : Create a new subscriptionDetails.
     *
     * @param subscriptionDetails the subscriptionDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subscriptionDetails, or with status {@code 400 (Bad Request)} if the subscriptionDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/subscription-details")
    public ResponseEntity<SubscriptionDetails> createSubscriptionDetails(@Valid @RequestBody SubscriptionDetails subscriptionDetails) throws URISyntaxException {
        log.debug("REST request to save SubscriptionDetails : {}", subscriptionDetails);
        if (subscriptionDetails.getId() != null) {
            throw new BadRequestAlertException("A new subscriptionDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SubscriptionDetails result = subscriptionDetailsService.save(subscriptionDetails);
        return ResponseEntity.created(new URI("/api/subscription-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /subscription-details} : Updates an existing subscriptionDetails.
     *
     * @param subscriptionDetails the subscriptionDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subscriptionDetails,
     * or with status {@code 400 (Bad Request)} if the subscriptionDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the subscriptionDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/subscription-details")
    public ResponseEntity<SubscriptionDetails> updateSubscriptionDetails(@Valid @RequestBody SubscriptionDetails subscriptionDetails) throws URISyntaxException {
        log.debug("REST request to update SubscriptionDetails : {}", subscriptionDetails);
        if (subscriptionDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SubscriptionDetails result = subscriptionDetailsService.save(subscriptionDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, subscriptionDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /subscription-details} : get all the subscriptionDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subscriptionDetails in body.
     */
    @GetMapping("/subscription-details")
    public ResponseEntity<List<SubscriptionDetails>> getAllSubscriptionDetails(Pageable pageable) {
        log.debug("REST request to get a page of SubscriptionDetails");
        Page<SubscriptionDetails> page = subscriptionDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /subscription-details/:id} : get the "id" subscriptionDetails.
     *
     * @param id the id of the subscriptionDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subscriptionDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/subscription-details/{id}")
    public ResponseEntity<SubscriptionDetails> getSubscriptionDetails(@PathVariable String id) {
        log.debug("REST request to get SubscriptionDetails : {}", id);
        Optional<SubscriptionDetails> subscriptionDetails = subscriptionDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(subscriptionDetails);
    }

    /**
     * {@code DELETE  /subscription-details/:id} : delete the "id" subscriptionDetails.
     *
     * @param id the id of the subscriptionDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/subscription-details/{id}")
    public ResponseEntity<Void> deleteSubscriptionDetails(@PathVariable String id) {
        log.debug("REST request to delete SubscriptionDetails : {}", id);
        subscriptionDetailsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
