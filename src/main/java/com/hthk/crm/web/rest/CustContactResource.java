package com.hthk.crm.web.rest;

import com.hthk.crm.domain.CustContact;
import com.hthk.crm.service.CustContactService;
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
 * REST controller for managing {@link com.hthk.crm.domain.CustContact}.
 */
@RestController
@RequestMapping("/api")
public class CustContactResource {

    private final Logger log = LoggerFactory.getLogger(CustContactResource.class);

    private static final String ENTITY_NAME = "custContact";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustContactService custContactService;

    public CustContactResource(CustContactService custContactService) {
        this.custContactService = custContactService;
    }

    /**
     * {@code POST  /cust-contacts} : Create a new custContact.
     *
     * @param custContact the custContact to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new custContact, or with status {@code 400 (Bad Request)} if the custContact has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cust-contacts")
    public ResponseEntity<CustContact> createCustContact(@Valid @RequestBody CustContact custContact) throws URISyntaxException {
        log.debug("REST request to save CustContact : {}", custContact);
        if (custContact.getId() != null) {
            throw new BadRequestAlertException("A new custContact cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustContact result = custContactService.save(custContact);
        return ResponseEntity.created(new URI("/api/cust-contacts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cust-contacts} : Updates an existing custContact.
     *
     * @param custContact the custContact to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated custContact,
     * or with status {@code 400 (Bad Request)} if the custContact is not valid,
     * or with status {@code 500 (Internal Server Error)} if the custContact couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cust-contacts")
    public ResponseEntity<CustContact> updateCustContact(@Valid @RequestBody CustContact custContact) throws URISyntaxException {
        log.debug("REST request to update CustContact : {}", custContact);
        if (custContact.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustContact result = custContactService.save(custContact);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, custContact.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cust-contacts} : get all the custContacts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of custContacts in body.
     */
    @GetMapping("/cust-contacts")
    public ResponseEntity<List<CustContact>> getAllCustContacts(Pageable pageable) {
        log.debug("REST request to get a page of CustContacts");
        Page<CustContact> page = custContactService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cust-contacts/:id} : get the "id" custContact.
     *
     * @param id the id of the custContact to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the custContact, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cust-contacts/{id}")
    public ResponseEntity<CustContact> getCustContact(@PathVariable String id) {
        log.debug("REST request to get CustContact : {}", id);
        Optional<CustContact> custContact = custContactService.findOne(id);
        return ResponseUtil.wrapOrNotFound(custContact);
    }

    /**
     * {@code DELETE  /cust-contacts/:id} : delete the "id" custContact.
     *
     * @param id the id of the custContact to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cust-contacts/{id}")
    public ResponseEntity<Void> deleteCustContact(@PathVariable String id) {
        log.debug("REST request to delete CustContact : {}", id);
        custContactService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
