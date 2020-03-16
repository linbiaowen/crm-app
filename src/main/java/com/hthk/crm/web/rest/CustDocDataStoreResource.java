package com.hthk.crm.web.rest;

import com.hthk.crm.domain.CustDocDataStore;
import com.hthk.crm.service.CustDocDataStoreService;
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
 * REST controller for managing {@link com.hthk.crm.domain.CustDocDataStore}.
 */
@RestController
@RequestMapping("/api")
public class CustDocDataStoreResource {

    private final Logger log = LoggerFactory.getLogger(CustDocDataStoreResource.class);

    private static final String ENTITY_NAME = "custDocDataStore";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustDocDataStoreService custDocDataStoreService;

    public CustDocDataStoreResource(CustDocDataStoreService custDocDataStoreService) {
        this.custDocDataStoreService = custDocDataStoreService;
    }

    /**
     * {@code POST  /cust-doc-data-stores} : Create a new custDocDataStore.
     *
     * @param custDocDataStore the custDocDataStore to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new custDocDataStore, or with status {@code 400 (Bad Request)} if the custDocDataStore has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cust-doc-data-stores")
    public ResponseEntity<CustDocDataStore> createCustDocDataStore(@Valid @RequestBody CustDocDataStore custDocDataStore) throws URISyntaxException {
        log.debug("REST request to save CustDocDataStore : {}", custDocDataStore);
        if (custDocDataStore.getId() != null) {
            throw new BadRequestAlertException("A new custDocDataStore cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustDocDataStore result = custDocDataStoreService.save(custDocDataStore);
        return ResponseEntity.created(new URI("/api/cust-doc-data-stores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cust-doc-data-stores} : Updates an existing custDocDataStore.
     *
     * @param custDocDataStore the custDocDataStore to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated custDocDataStore,
     * or with status {@code 400 (Bad Request)} if the custDocDataStore is not valid,
     * or with status {@code 500 (Internal Server Error)} if the custDocDataStore couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cust-doc-data-stores")
    public ResponseEntity<CustDocDataStore> updateCustDocDataStore(@Valid @RequestBody CustDocDataStore custDocDataStore) throws URISyntaxException {
        log.debug("REST request to update CustDocDataStore : {}", custDocDataStore);
        if (custDocDataStore.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustDocDataStore result = custDocDataStoreService.save(custDocDataStore);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, custDocDataStore.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cust-doc-data-stores} : get all the custDocDataStores.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of custDocDataStores in body.
     */
    @GetMapping("/cust-doc-data-stores")
    public ResponseEntity<List<CustDocDataStore>> getAllCustDocDataStores(Pageable pageable) {
        log.debug("REST request to get a page of CustDocDataStores");
        Page<CustDocDataStore> page = custDocDataStoreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cust-doc-data-stores/:id} : get the "id" custDocDataStore.
     *
     * @param id the id of the custDocDataStore to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the custDocDataStore, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cust-doc-data-stores/{id}")
    public ResponseEntity<CustDocDataStore> getCustDocDataStore(@PathVariable String id) {
        log.debug("REST request to get CustDocDataStore : {}", id);
        Optional<CustDocDataStore> custDocDataStore = custDocDataStoreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(custDocDataStore);
    }

    /**
     * {@code DELETE  /cust-doc-data-stores/:id} : delete the "id" custDocDataStore.
     *
     * @param id the id of the custDocDataStore to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cust-doc-data-stores/{id}")
    public ResponseEntity<Void> deleteCustDocDataStore(@PathVariable String id) {
        log.debug("REST request to delete CustDocDataStore : {}", id);
        custDocDataStoreService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
