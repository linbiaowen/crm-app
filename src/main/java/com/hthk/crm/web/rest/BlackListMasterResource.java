package com.hthk.crm.web.rest;

import com.hthk.crm.domain.BlackListMaster;
import com.hthk.crm.service.BlackListMasterService;
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
 * REST controller for managing {@link com.hthk.crm.domain.BlackListMaster}.
 */
@RestController
@RequestMapping("/api")
public class BlackListMasterResource {

    private final Logger log = LoggerFactory.getLogger(BlackListMasterResource.class);

    private static final String ENTITY_NAME = "blackListMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BlackListMasterService blackListMasterService;

    public BlackListMasterResource(BlackListMasterService blackListMasterService) {
        this.blackListMasterService = blackListMasterService;
    }

    /**
     * {@code POST  /black-list-masters} : Create a new blackListMaster.
     *
     * @param blackListMaster the blackListMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new blackListMaster, or with status {@code 400 (Bad Request)} if the blackListMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/black-list-masters")
    public ResponseEntity<BlackListMaster> createBlackListMaster(@Valid @RequestBody BlackListMaster blackListMaster) throws URISyntaxException {
        log.debug("REST request to save BlackListMaster : {}", blackListMaster);
        if (blackListMaster.getId() != null) {
            throw new BadRequestAlertException("A new blackListMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BlackListMaster result = blackListMasterService.save(blackListMaster);
        return ResponseEntity.created(new URI("/api/black-list-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /black-list-masters} : Updates an existing blackListMaster.
     *
     * @param blackListMaster the blackListMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated blackListMaster,
     * or with status {@code 400 (Bad Request)} if the blackListMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the blackListMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/black-list-masters")
    public ResponseEntity<BlackListMaster> updateBlackListMaster(@Valid @RequestBody BlackListMaster blackListMaster) throws URISyntaxException {
        log.debug("REST request to update BlackListMaster : {}", blackListMaster);
        if (blackListMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BlackListMaster result = blackListMasterService.save(blackListMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, blackListMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /black-list-masters} : get all the blackListMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of blackListMasters in body.
     */
    @GetMapping("/black-list-masters")
    public ResponseEntity<List<BlackListMaster>> getAllBlackListMasters(Pageable pageable) {
        log.debug("REST request to get a page of BlackListMasters");
        Page<BlackListMaster> page = blackListMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /black-list-masters/:id} : get the "id" blackListMaster.
     *
     * @param id the id of the blackListMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the blackListMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/black-list-masters/{id}")
    public ResponseEntity<BlackListMaster> getBlackListMaster(@PathVariable String id) {
        log.debug("REST request to get BlackListMaster : {}", id);
        Optional<BlackListMaster> blackListMaster = blackListMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(blackListMaster);
    }

    /**
     * {@code DELETE  /black-list-masters/:id} : delete the "id" blackListMaster.
     *
     * @param id the id of the blackListMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/black-list-masters/{id}")
    public ResponseEntity<Void> deleteBlackListMaster(@PathVariable String id) {
        log.debug("REST request to delete BlackListMaster : {}", id);
        blackListMasterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
