package com.hthk.crm.web.rest;

import com.hthk.crm.domain.CustAcctBlackList;
import com.hthk.crm.service.CustAcctBlackListService;
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
 * REST controller for managing {@link com.hthk.crm.domain.CustAcctBlackList}.
 */
@RestController
@RequestMapping("/api")
public class CustAcctBlackListResource {

    private final Logger log = LoggerFactory.getLogger(CustAcctBlackListResource.class);

    private static final String ENTITY_NAME = "custAcctBlackList";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustAcctBlackListService custAcctBlackListService;

    public CustAcctBlackListResource(CustAcctBlackListService custAcctBlackListService) {
        this.custAcctBlackListService = custAcctBlackListService;
    }

    /**
     * {@code POST  /cust-acct-black-lists} : Create a new custAcctBlackList.
     *
     * @param custAcctBlackList the custAcctBlackList to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new custAcctBlackList, or with status {@code 400 (Bad Request)} if the custAcctBlackList has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cust-acct-black-lists")
    public ResponseEntity<CustAcctBlackList> createCustAcctBlackList(@Valid @RequestBody CustAcctBlackList custAcctBlackList) throws URISyntaxException {
        log.debug("REST request to save CustAcctBlackList : {}", custAcctBlackList);
        if (custAcctBlackList.getId() != null) {
            throw new BadRequestAlertException("A new custAcctBlackList cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustAcctBlackList result = custAcctBlackListService.save(custAcctBlackList);
        return ResponseEntity.created(new URI("/api/cust-acct-black-lists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cust-acct-black-lists} : Updates an existing custAcctBlackList.
     *
     * @param custAcctBlackList the custAcctBlackList to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated custAcctBlackList,
     * or with status {@code 400 (Bad Request)} if the custAcctBlackList is not valid,
     * or with status {@code 500 (Internal Server Error)} if the custAcctBlackList couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cust-acct-black-lists")
    public ResponseEntity<CustAcctBlackList> updateCustAcctBlackList(@Valid @RequestBody CustAcctBlackList custAcctBlackList) throws URISyntaxException {
        log.debug("REST request to update CustAcctBlackList : {}", custAcctBlackList);
        if (custAcctBlackList.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustAcctBlackList result = custAcctBlackListService.save(custAcctBlackList);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, custAcctBlackList.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cust-acct-black-lists} : get all the custAcctBlackLists.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of custAcctBlackLists in body.
     */
    @GetMapping("/cust-acct-black-lists")
    public ResponseEntity<List<CustAcctBlackList>> getAllCustAcctBlackLists(Pageable pageable) {
        log.debug("REST request to get a page of CustAcctBlackLists");
        Page<CustAcctBlackList> page = custAcctBlackListService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cust-acct-black-lists/:id} : get the "id" custAcctBlackList.
     *
     * @param id the id of the custAcctBlackList to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the custAcctBlackList, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cust-acct-black-lists/{id}")
    public ResponseEntity<CustAcctBlackList> getCustAcctBlackList(@PathVariable String id) {
        log.debug("REST request to get CustAcctBlackList : {}", id);
        Optional<CustAcctBlackList> custAcctBlackList = custAcctBlackListService.findOne(id);
        return ResponseUtil.wrapOrNotFound(custAcctBlackList);
    }

    /**
     * {@code DELETE  /cust-acct-black-lists/:id} : delete the "id" custAcctBlackList.
     *
     * @param id the id of the custAcctBlackList to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cust-acct-black-lists/{id}")
    public ResponseEntity<Void> deleteCustAcctBlackList(@PathVariable String id) {
        log.debug("REST request to delete CustAcctBlackList : {}", id);
        custAcctBlackListService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
