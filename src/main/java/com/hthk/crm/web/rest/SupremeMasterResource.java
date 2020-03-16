package com.hthk.crm.web.rest;

import com.hthk.crm.domain.SupremeMaster;
import com.hthk.crm.service.SupremeMasterService;
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
 * REST controller for managing {@link com.hthk.crm.domain.SupremeMaster}.
 */
@RestController
@RequestMapping("/api")
public class SupremeMasterResource {

    private final Logger log = LoggerFactory.getLogger(SupremeMasterResource.class);

    private static final String ENTITY_NAME = "supremeMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SupremeMasterService supremeMasterService;

    public SupremeMasterResource(SupremeMasterService supremeMasterService) {
        this.supremeMasterService = supremeMasterService;
    }

    /**
     * {@code POST  /supreme-masters} : Create a new supremeMaster.
     *
     * @param supremeMaster the supremeMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new supremeMaster, or with status {@code 400 (Bad Request)} if the supremeMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/supreme-masters")
    public ResponseEntity<SupremeMaster> createSupremeMaster(@Valid @RequestBody SupremeMaster supremeMaster) throws URISyntaxException {
        log.debug("REST request to save SupremeMaster : {}", supremeMaster);
        if (supremeMaster.getId() != null) {
            throw new BadRequestAlertException("A new supremeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SupremeMaster result = supremeMasterService.save(supremeMaster);
        return ResponseEntity.created(new URI("/api/supreme-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /supreme-masters} : Updates an existing supremeMaster.
     *
     * @param supremeMaster the supremeMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated supremeMaster,
     * or with status {@code 400 (Bad Request)} if the supremeMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the supremeMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/supreme-masters")
    public ResponseEntity<SupremeMaster> updateSupremeMaster(@Valid @RequestBody SupremeMaster supremeMaster) throws URISyntaxException {
        log.debug("REST request to update SupremeMaster : {}", supremeMaster);
        if (supremeMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SupremeMaster result = supremeMasterService.save(supremeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, supremeMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /supreme-masters} : get all the supremeMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of supremeMasters in body.
     */
    @GetMapping("/supreme-masters")
    public ResponseEntity<List<SupremeMaster>> getAllSupremeMasters(Pageable pageable) {
        log.debug("REST request to get a page of SupremeMasters");
        Page<SupremeMaster> page = supremeMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /supreme-masters/:id} : get the "id" supremeMaster.
     *
     * @param id the id of the supremeMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the supremeMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/supreme-masters/{id}")
    public ResponseEntity<SupremeMaster> getSupremeMaster(@PathVariable String id) {
        log.debug("REST request to get SupremeMaster : {}", id);
        Optional<SupremeMaster> supremeMaster = supremeMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(supremeMaster);
    }

    /**
     * {@code DELETE  /supreme-masters/:id} : delete the "id" supremeMaster.
     *
     * @param id the id of the supremeMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/supreme-masters/{id}")
    public ResponseEntity<Void> deleteSupremeMaster(@PathVariable String id) {
        log.debug("REST request to delete SupremeMaster : {}", id);
        supremeMasterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
