package com.hthk.crm.web.rest;

import com.hthk.crm.domain.BillCycle;
import com.hthk.crm.service.BillCycleService;
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
 * REST controller for managing {@link com.hthk.crm.domain.BillCycle}.
 */
@RestController
@RequestMapping("/api")
public class BillCycleResource {

    private final Logger log = LoggerFactory.getLogger(BillCycleResource.class);

    private static final String ENTITY_NAME = "billCycle";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BillCycleService billCycleService;

    public BillCycleResource(BillCycleService billCycleService) {
        this.billCycleService = billCycleService;
    }

    /**
     * {@code POST  /bill-cycles} : Create a new billCycle.
     *
     * @param billCycle the billCycle to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new billCycle, or with status {@code 400 (Bad Request)} if the billCycle has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bill-cycles")
    public ResponseEntity<BillCycle> createBillCycle(@Valid @RequestBody BillCycle billCycle) throws URISyntaxException {
        log.debug("REST request to save BillCycle : {}", billCycle);
        if (billCycle.getId() != null) {
            throw new BadRequestAlertException("A new billCycle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BillCycle result = billCycleService.save(billCycle);
        return ResponseEntity.created(new URI("/api/bill-cycles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bill-cycles} : Updates an existing billCycle.
     *
     * @param billCycle the billCycle to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billCycle,
     * or with status {@code 400 (Bad Request)} if the billCycle is not valid,
     * or with status {@code 500 (Internal Server Error)} if the billCycle couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bill-cycles")
    public ResponseEntity<BillCycle> updateBillCycle(@Valid @RequestBody BillCycle billCycle) throws URISyntaxException {
        log.debug("REST request to update BillCycle : {}", billCycle);
        if (billCycle.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BillCycle result = billCycleService.save(billCycle);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, billCycle.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bill-cycles} : get all the billCycles.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of billCycles in body.
     */
    @GetMapping("/bill-cycles")
    public ResponseEntity<List<BillCycle>> getAllBillCycles(Pageable pageable) {
        log.debug("REST request to get a page of BillCycles");
        Page<BillCycle> page = billCycleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bill-cycles/:id} : get the "id" billCycle.
     *
     * @param id the id of the billCycle to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the billCycle, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bill-cycles/{id}")
    public ResponseEntity<BillCycle> getBillCycle(@PathVariable String id) {
        log.debug("REST request to get BillCycle : {}", id);
        Optional<BillCycle> billCycle = billCycleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(billCycle);
    }

    /**
     * {@code DELETE  /bill-cycles/:id} : delete the "id" billCycle.
     *
     * @param id the id of the billCycle to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bill-cycles/{id}")
    public ResponseEntity<Void> deleteBillCycle(@PathVariable String id) {
        log.debug("REST request to delete BillCycle : {}", id);
        billCycleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
