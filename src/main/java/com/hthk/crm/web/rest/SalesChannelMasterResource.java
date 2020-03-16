package com.hthk.crm.web.rest;

import com.hthk.crm.domain.SalesChannelMaster;
import com.hthk.crm.service.SalesChannelMasterService;
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
 * REST controller for managing {@link com.hthk.crm.domain.SalesChannelMaster}.
 */
@RestController
@RequestMapping("/api")
public class SalesChannelMasterResource {

    private final Logger log = LoggerFactory.getLogger(SalesChannelMasterResource.class);

    private static final String ENTITY_NAME = "salesChannelMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesChannelMasterService salesChannelMasterService;

    public SalesChannelMasterResource(SalesChannelMasterService salesChannelMasterService) {
        this.salesChannelMasterService = salesChannelMasterService;
    }

    /**
     * {@code POST  /sales-channel-masters} : Create a new salesChannelMaster.
     *
     * @param salesChannelMaster the salesChannelMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesChannelMaster, or with status {@code 400 (Bad Request)} if the salesChannelMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales-channel-masters")
    public ResponseEntity<SalesChannelMaster> createSalesChannelMaster(@Valid @RequestBody SalesChannelMaster salesChannelMaster) throws URISyntaxException {
        log.debug("REST request to save SalesChannelMaster : {}", salesChannelMaster);
        if (salesChannelMaster.getId() != null) {
            throw new BadRequestAlertException("A new salesChannelMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SalesChannelMaster result = salesChannelMasterService.save(salesChannelMaster);
        return ResponseEntity.created(new URI("/api/sales-channel-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sales-channel-masters} : Updates an existing salesChannelMaster.
     *
     * @param salesChannelMaster the salesChannelMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesChannelMaster,
     * or with status {@code 400 (Bad Request)} if the salesChannelMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesChannelMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales-channel-masters")
    public ResponseEntity<SalesChannelMaster> updateSalesChannelMaster(@Valid @RequestBody SalesChannelMaster salesChannelMaster) throws URISyntaxException {
        log.debug("REST request to update SalesChannelMaster : {}", salesChannelMaster);
        if (salesChannelMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SalesChannelMaster result = salesChannelMasterService.save(salesChannelMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, salesChannelMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sales-channel-masters} : get all the salesChannelMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesChannelMasters in body.
     */
    @GetMapping("/sales-channel-masters")
    public ResponseEntity<List<SalesChannelMaster>> getAllSalesChannelMasters(Pageable pageable) {
        log.debug("REST request to get a page of SalesChannelMasters");
        Page<SalesChannelMaster> page = salesChannelMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sales-channel-masters/:id} : get the "id" salesChannelMaster.
     *
     * @param id the id of the salesChannelMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesChannelMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales-channel-masters/{id}")
    public ResponseEntity<SalesChannelMaster> getSalesChannelMaster(@PathVariable String id) {
        log.debug("REST request to get SalesChannelMaster : {}", id);
        Optional<SalesChannelMaster> salesChannelMaster = salesChannelMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salesChannelMaster);
    }

    /**
     * {@code DELETE  /sales-channel-masters/:id} : delete the "id" salesChannelMaster.
     *
     * @param id the id of the salesChannelMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales-channel-masters/{id}")
    public ResponseEntity<Void> deleteSalesChannelMaster(@PathVariable String id) {
        log.debug("REST request to delete SalesChannelMaster : {}", id);
        salesChannelMasterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
