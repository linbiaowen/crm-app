package com.hthk.crm.web.rest;

import com.hthk.crm.domain.CustCommOptoutMaster;
import com.hthk.crm.service.CustCommOptoutMasterService;
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
 * REST controller for managing {@link com.hthk.crm.domain.CustCommOptoutMaster}.
 */
@RestController
@RequestMapping("/api")
public class CustCommOptoutMasterResource {

    private final Logger log = LoggerFactory.getLogger(CustCommOptoutMasterResource.class);

    private static final String ENTITY_NAME = "custCommOptoutMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustCommOptoutMasterService custCommOptoutMasterService;

    public CustCommOptoutMasterResource(CustCommOptoutMasterService custCommOptoutMasterService) {
        this.custCommOptoutMasterService = custCommOptoutMasterService;
    }

    /**
     * {@code POST  /cust-comm-optout-masters} : Create a new custCommOptoutMaster.
     *
     * @param custCommOptoutMaster the custCommOptoutMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new custCommOptoutMaster, or with status {@code 400 (Bad Request)} if the custCommOptoutMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cust-comm-optout-masters")
    public ResponseEntity<CustCommOptoutMaster> createCustCommOptoutMaster(@Valid @RequestBody CustCommOptoutMaster custCommOptoutMaster) throws URISyntaxException {
        log.debug("REST request to save CustCommOptoutMaster : {}", custCommOptoutMaster);
        if (custCommOptoutMaster.getId() != null) {
            throw new BadRequestAlertException("A new custCommOptoutMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustCommOptoutMaster result = custCommOptoutMasterService.save(custCommOptoutMaster);
        return ResponseEntity.created(new URI("/api/cust-comm-optout-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cust-comm-optout-masters} : Updates an existing custCommOptoutMaster.
     *
     * @param custCommOptoutMaster the custCommOptoutMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated custCommOptoutMaster,
     * or with status {@code 400 (Bad Request)} if the custCommOptoutMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the custCommOptoutMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cust-comm-optout-masters")
    public ResponseEntity<CustCommOptoutMaster> updateCustCommOptoutMaster(@Valid @RequestBody CustCommOptoutMaster custCommOptoutMaster) throws URISyntaxException {
        log.debug("REST request to update CustCommOptoutMaster : {}", custCommOptoutMaster);
        if (custCommOptoutMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustCommOptoutMaster result = custCommOptoutMasterService.save(custCommOptoutMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, custCommOptoutMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cust-comm-optout-masters} : get all the custCommOptoutMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of custCommOptoutMasters in body.
     */
    @GetMapping("/cust-comm-optout-masters")
    public ResponseEntity<List<CustCommOptoutMaster>> getAllCustCommOptoutMasters(Pageable pageable) {
        log.debug("REST request to get a page of CustCommOptoutMasters");
        Page<CustCommOptoutMaster> page = custCommOptoutMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cust-comm-optout-masters/:id} : get the "id" custCommOptoutMaster.
     *
     * @param id the id of the custCommOptoutMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the custCommOptoutMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cust-comm-optout-masters/{id}")
    public ResponseEntity<CustCommOptoutMaster> getCustCommOptoutMaster(@PathVariable String id) {
        log.debug("REST request to get CustCommOptoutMaster : {}", id);
        Optional<CustCommOptoutMaster> custCommOptoutMaster = custCommOptoutMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(custCommOptoutMaster);
    }

    /**
     * {@code DELETE  /cust-comm-optout-masters/:id} : delete the "id" custCommOptoutMaster.
     *
     * @param id the id of the custCommOptoutMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cust-comm-optout-masters/{id}")
    public ResponseEntity<Void> deleteCustCommOptoutMaster(@PathVariable String id) {
        log.debug("REST request to delete CustCommOptoutMaster : {}", id);
        custCommOptoutMasterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
