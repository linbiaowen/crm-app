package com.hthk.crm.web.rest;

import com.hthk.crm.domain.SubscriptionGroup;
import com.hthk.crm.service.SubscriptionGroupService;
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
 * REST controller for managing {@link com.hthk.crm.domain.SubscriptionGroup}.
 */
@RestController
@RequestMapping("/api")
public class SubscriptionGroupResource {

    private final Logger log = LoggerFactory.getLogger(SubscriptionGroupResource.class);

    private static final String ENTITY_NAME = "subscriptionGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SubscriptionGroupService subscriptionGroupService;

    public SubscriptionGroupResource(SubscriptionGroupService subscriptionGroupService) {
        this.subscriptionGroupService = subscriptionGroupService;
    }

    /**
     * {@code POST  /subscription-groups} : Create a new subscriptionGroup.
     *
     * @param subscriptionGroup the subscriptionGroup to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subscriptionGroup, or with status {@code 400 (Bad Request)} if the subscriptionGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/subscription-groups")
    public ResponseEntity<SubscriptionGroup> createSubscriptionGroup(@Valid @RequestBody SubscriptionGroup subscriptionGroup) throws URISyntaxException {
        log.debug("REST request to save SubscriptionGroup : {}", subscriptionGroup);
        if (subscriptionGroup.getId() != null) {
            throw new BadRequestAlertException("A new subscriptionGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SubscriptionGroup result = subscriptionGroupService.save(subscriptionGroup);
        return ResponseEntity.created(new URI("/api/subscription-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /subscription-groups} : Updates an existing subscriptionGroup.
     *
     * @param subscriptionGroup the subscriptionGroup to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subscriptionGroup,
     * or with status {@code 400 (Bad Request)} if the subscriptionGroup is not valid,
     * or with status {@code 500 (Internal Server Error)} if the subscriptionGroup couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/subscription-groups")
    public ResponseEntity<SubscriptionGroup> updateSubscriptionGroup(@Valid @RequestBody SubscriptionGroup subscriptionGroup) throws URISyntaxException {
        log.debug("REST request to update SubscriptionGroup : {}", subscriptionGroup);
        if (subscriptionGroup.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SubscriptionGroup result = subscriptionGroupService.save(subscriptionGroup);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, subscriptionGroup.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /subscription-groups} : get all the subscriptionGroups.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subscriptionGroups in body.
     */
    @GetMapping("/subscription-groups")
    public ResponseEntity<List<SubscriptionGroup>> getAllSubscriptionGroups(Pageable pageable) {
        log.debug("REST request to get a page of SubscriptionGroups");
        Page<SubscriptionGroup> page = subscriptionGroupService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /subscription-groups/:id} : get the "id" subscriptionGroup.
     *
     * @param id the id of the subscriptionGroup to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subscriptionGroup, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/subscription-groups/{id}")
    public ResponseEntity<SubscriptionGroup> getSubscriptionGroup(@PathVariable String id) {
        log.debug("REST request to get SubscriptionGroup : {}", id);
        Optional<SubscriptionGroup> subscriptionGroup = subscriptionGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(subscriptionGroup);
    }

    /**
     * {@code DELETE  /subscription-groups/:id} : delete the "id" subscriptionGroup.
     *
     * @param id the id of the subscriptionGroup to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/subscription-groups/{id}")
    public ResponseEntity<Void> deleteSubscriptionGroup(@PathVariable String id) {
        log.debug("REST request to delete SubscriptionGroup : {}", id);
        subscriptionGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
