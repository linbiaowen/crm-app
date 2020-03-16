package com.hthk.crm.web.rest;

import com.hthk.crm.domain.CustAddress;
import com.hthk.crm.service.CustAddressService;
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
 * REST controller for managing {@link com.hthk.crm.domain.CustAddress}.
 */
@RestController
@RequestMapping("/api")
public class CustAddressResource {

    private final Logger log = LoggerFactory.getLogger(CustAddressResource.class);

    private static final String ENTITY_NAME = "custAddress";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustAddressService custAddressService;

    public CustAddressResource(CustAddressService custAddressService) {
        this.custAddressService = custAddressService;
    }

    /**
     * {@code POST  /cust-addresses} : Create a new custAddress.
     *
     * @param custAddress the custAddress to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new custAddress, or with status {@code 400 (Bad Request)} if the custAddress has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cust-addresses")
    public ResponseEntity<CustAddress> createCustAddress(@Valid @RequestBody CustAddress custAddress) throws URISyntaxException {
        log.debug("REST request to save CustAddress : {}", custAddress);
        if (custAddress.getId() != null) {
            throw new BadRequestAlertException("A new custAddress cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustAddress result = custAddressService.save(custAddress);
        return ResponseEntity.created(new URI("/api/cust-addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cust-addresses} : Updates an existing custAddress.
     *
     * @param custAddress the custAddress to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated custAddress,
     * or with status {@code 400 (Bad Request)} if the custAddress is not valid,
     * or with status {@code 500 (Internal Server Error)} if the custAddress couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cust-addresses")
    public ResponseEntity<CustAddress> updateCustAddress(@Valid @RequestBody CustAddress custAddress) throws URISyntaxException {
        log.debug("REST request to update CustAddress : {}", custAddress);
        if (custAddress.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustAddress result = custAddressService.save(custAddress);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, custAddress.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cust-addresses} : get all the custAddresses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of custAddresses in body.
     */
    @GetMapping("/cust-addresses")
    public ResponseEntity<List<CustAddress>> getAllCustAddresses(Pageable pageable) {
        log.debug("REST request to get a page of CustAddresses");
        Page<CustAddress> page = custAddressService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cust-addresses/:id} : get the "id" custAddress.
     *
     * @param id the id of the custAddress to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the custAddress, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cust-addresses/{id}")
    public ResponseEntity<CustAddress> getCustAddress(@PathVariable String id) {
        log.debug("REST request to get CustAddress : {}", id);
        Optional<CustAddress> custAddress = custAddressService.findOne(id);
        return ResponseUtil.wrapOrNotFound(custAddress);
    }

    /**
     * {@code DELETE  /cust-addresses/:id} : delete the "id" custAddress.
     *
     * @param id the id of the custAddress to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cust-addresses/{id}")
    public ResponseEntity<Void> deleteCustAddress(@PathVariable String id) {
        log.debug("REST request to delete CustAddress : {}", id);
        custAddressService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
