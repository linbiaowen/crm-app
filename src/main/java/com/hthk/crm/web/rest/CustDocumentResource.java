package com.hthk.crm.web.rest;

import com.hthk.crm.domain.CustDocument;
import com.hthk.crm.service.CustDocumentService;
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
 * REST controller for managing {@link com.hthk.crm.domain.CustDocument}.
 */
@RestController
@RequestMapping("/api")
public class CustDocumentResource {

    private final Logger log = LoggerFactory.getLogger(CustDocumentResource.class);

    private static final String ENTITY_NAME = "custDocument";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustDocumentService custDocumentService;

    public CustDocumentResource(CustDocumentService custDocumentService) {
        this.custDocumentService = custDocumentService;
    }

    /**
     * {@code POST  /cust-documents} : Create a new custDocument.
     *
     * @param custDocument the custDocument to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new custDocument, or with status {@code 400 (Bad Request)} if the custDocument has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cust-documents")
    public ResponseEntity<CustDocument> createCustDocument(@Valid @RequestBody CustDocument custDocument) throws URISyntaxException {
        log.debug("REST request to save CustDocument : {}", custDocument);
        if (custDocument.getId() != null) {
            throw new BadRequestAlertException("A new custDocument cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustDocument result = custDocumentService.save(custDocument);
        return ResponseEntity.created(new URI("/api/cust-documents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cust-documents} : Updates an existing custDocument.
     *
     * @param custDocument the custDocument to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated custDocument,
     * or with status {@code 400 (Bad Request)} if the custDocument is not valid,
     * or with status {@code 500 (Internal Server Error)} if the custDocument couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cust-documents")
    public ResponseEntity<CustDocument> updateCustDocument(@Valid @RequestBody CustDocument custDocument) throws URISyntaxException {
        log.debug("REST request to update CustDocument : {}", custDocument);
        if (custDocument.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustDocument result = custDocumentService.save(custDocument);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, custDocument.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cust-documents} : get all the custDocuments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of custDocuments in body.
     */
    @GetMapping("/cust-documents")
    public ResponseEntity<List<CustDocument>> getAllCustDocuments(Pageable pageable) {
        log.debug("REST request to get a page of CustDocuments");
        Page<CustDocument> page = custDocumentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cust-documents/:id} : get the "id" custDocument.
     *
     * @param id the id of the custDocument to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the custDocument, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cust-documents/{id}")
    public ResponseEntity<CustDocument> getCustDocument(@PathVariable String id) {
        log.debug("REST request to get CustDocument : {}", id);
        Optional<CustDocument> custDocument = custDocumentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(custDocument);
    }

    /**
     * {@code DELETE  /cust-documents/:id} : delete the "id" custDocument.
     *
     * @param id the id of the custDocument to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cust-documents/{id}")
    public ResponseEntity<Void> deleteCustDocument(@PathVariable String id) {
        log.debug("REST request to delete CustDocument : {}", id);
        custDocumentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
