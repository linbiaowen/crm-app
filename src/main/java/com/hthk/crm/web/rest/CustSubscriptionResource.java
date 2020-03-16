package com.hthk.crm.web.rest;

import com.hthk.crm.domain.CustSubscription;
import com.hthk.crm.service.CustSubscriptionService;
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
 * REST controller for managing {@link com.hthk.crm.domain.CustSubscription}.
 */
@RestController
@RequestMapping("/api")
public class CustSubscriptionResource {

    private final Logger log = LoggerFactory.getLogger(CustSubscriptionResource.class);

    private static final String ENTITY_NAME = "custSubscription";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustSubscriptionService custSubscriptionService;

    public CustSubscriptionResource(CustSubscriptionService custSubscriptionService) {
        this.custSubscriptionService = custSubscriptionService;
    }

    /**
     * {@code POST  /cust-subscriptions} : Create a new custSubscription.
     *
     * @param custSubscription the custSubscription to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new custSubscription, or with status {@code 400 (Bad Request)} if the custSubscription has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cust-subscriptions")
    public ResponseEntity<CustSubscription> createCustSubscription(@Valid @RequestBody CustSubscription custSubscription) throws URISyntaxException {
        log.debug("REST request to save CustSubscription : {}", custSubscription);
        if (custSubscription.getId() != null) {
            throw new BadRequestAlertException("A new custSubscription cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustSubscription result = custSubscriptionService.save(custSubscription);
        return ResponseEntity.created(new URI("/api/cust-subscriptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cust-subscriptions} : Updates an existing custSubscription.
     *
     * @param custSubscription the custSubscription to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated custSubscription,
     * or with status {@code 400 (Bad Request)} if the custSubscription is not valid,
     * or with status {@code 500 (Internal Server Error)} if the custSubscription couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cust-subscriptions")
    public ResponseEntity<CustSubscription> updateCustSubscription(@Valid @RequestBody CustSubscription custSubscription) throws URISyntaxException {
        log.debug("REST request to update CustSubscription : {}", custSubscription);
        if (custSubscription.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustSubscription result = custSubscriptionService.save(custSubscription);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, custSubscription.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cust-subscriptions} : get all the custSubscriptions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of custSubscriptions in body.
     */
    @GetMapping("/cust-subscriptions")
    public ResponseEntity<List<CustSubscription>> getAllCustSubscriptions(Pageable pageable) {
        log.debug("REST request to get a page of CustSubscriptions");
        Page<CustSubscription> page = custSubscriptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cust-subscriptions/:id} : get the "id" custSubscription.
     *
     * @param id the id of the custSubscription to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the custSubscription, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cust-subscriptions/{id}")
    public ResponseEntity<CustSubscription> getCustSubscription(@PathVariable String id) {
        log.debug("REST request to get CustSubscription : {}", id);
        Optional<CustSubscription> custSubscription = custSubscriptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(custSubscription);
    }

    /**
     * {@code DELETE  /cust-subscriptions/:id} : delete the "id" custSubscription.
     *
     * @param id the id of the custSubscription to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cust-subscriptions/{id}")
    public ResponseEntity<Void> deleteCustSubscription(@PathVariable String id) {
        log.debug("REST request to delete CustSubscription : {}", id);
        custSubscriptionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
