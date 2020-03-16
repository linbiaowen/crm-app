package com.hthk.crm.web.rest;

import com.hthk.crm.domain.CommOptoutType;
import com.hthk.crm.service.CommOptoutTypeService;
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
 * REST controller for managing {@link com.hthk.crm.domain.CommOptoutType}.
 */
@RestController
@RequestMapping("/api")
public class CommOptoutTypeResource {

    private final Logger log = LoggerFactory.getLogger(CommOptoutTypeResource.class);

    private static final String ENTITY_NAME = "commOptoutType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommOptoutTypeService commOptoutTypeService;

    public CommOptoutTypeResource(CommOptoutTypeService commOptoutTypeService) {
        this.commOptoutTypeService = commOptoutTypeService;
    }

    /**
     * {@code POST  /comm-optout-types} : Create a new commOptoutType.
     *
     * @param commOptoutType the commOptoutType to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new commOptoutType, or with status {@code 400 (Bad Request)} if the commOptoutType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/comm-optout-types")
    public ResponseEntity<CommOptoutType> createCommOptoutType(@Valid @RequestBody CommOptoutType commOptoutType) throws URISyntaxException {
        log.debug("REST request to save CommOptoutType : {}", commOptoutType);
        if (commOptoutType.getId() != null) {
            throw new BadRequestAlertException("A new commOptoutType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommOptoutType result = commOptoutTypeService.save(commOptoutType);
        return ResponseEntity.created(new URI("/api/comm-optout-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /comm-optout-types} : Updates an existing commOptoutType.
     *
     * @param commOptoutType the commOptoutType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commOptoutType,
     * or with status {@code 400 (Bad Request)} if the commOptoutType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the commOptoutType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/comm-optout-types")
    public ResponseEntity<CommOptoutType> updateCommOptoutType(@Valid @RequestBody CommOptoutType commOptoutType) throws URISyntaxException {
        log.debug("REST request to update CommOptoutType : {}", commOptoutType);
        if (commOptoutType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CommOptoutType result = commOptoutTypeService.save(commOptoutType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, commOptoutType.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /comm-optout-types} : get all the commOptoutTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of commOptoutTypes in body.
     */
    @GetMapping("/comm-optout-types")
    public ResponseEntity<List<CommOptoutType>> getAllCommOptoutTypes(Pageable pageable) {
        log.debug("REST request to get a page of CommOptoutTypes");
        Page<CommOptoutType> page = commOptoutTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /comm-optout-types/:id} : get the "id" commOptoutType.
     *
     * @param id the id of the commOptoutType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the commOptoutType, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/comm-optout-types/{id}")
    public ResponseEntity<CommOptoutType> getCommOptoutType(@PathVariable String id) {
        log.debug("REST request to get CommOptoutType : {}", id);
        Optional<CommOptoutType> commOptoutType = commOptoutTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(commOptoutType);
    }

    /**
     * {@code DELETE  /comm-optout-types/:id} : delete the "id" commOptoutType.
     *
     * @param id the id of the commOptoutType to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/comm-optout-types/{id}")
    public ResponseEntity<Void> deleteCommOptoutType(@PathVariable String id) {
        log.debug("REST request to delete CommOptoutType : {}", id);
        commOptoutTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
