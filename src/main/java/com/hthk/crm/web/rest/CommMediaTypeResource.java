package com.hthk.crm.web.rest;

import com.hthk.crm.domain.CommMediaType;
import com.hthk.crm.service.CommMediaTypeService;
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
 * REST controller for managing {@link com.hthk.crm.domain.CommMediaType}.
 */
@RestController
@RequestMapping("/api")
public class CommMediaTypeResource {

    private final Logger log = LoggerFactory.getLogger(CommMediaTypeResource.class);

    private static final String ENTITY_NAME = "commMediaType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommMediaTypeService commMediaTypeService;

    public CommMediaTypeResource(CommMediaTypeService commMediaTypeService) {
        this.commMediaTypeService = commMediaTypeService;
    }

    /**
     * {@code POST  /comm-media-types} : Create a new commMediaType.
     *
     * @param commMediaType the commMediaType to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new commMediaType, or with status {@code 400 (Bad Request)} if the commMediaType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/comm-media-types")
    public ResponseEntity<CommMediaType> createCommMediaType(@Valid @RequestBody CommMediaType commMediaType) throws URISyntaxException {
        log.debug("REST request to save CommMediaType : {}", commMediaType);
        if (commMediaType.getId() != null) {
            throw new BadRequestAlertException("A new commMediaType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommMediaType result = commMediaTypeService.save(commMediaType);
        return ResponseEntity.created(new URI("/api/comm-media-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /comm-media-types} : Updates an existing commMediaType.
     *
     * @param commMediaType the commMediaType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commMediaType,
     * or with status {@code 400 (Bad Request)} if the commMediaType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the commMediaType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/comm-media-types")
    public ResponseEntity<CommMediaType> updateCommMediaType(@Valid @RequestBody CommMediaType commMediaType) throws URISyntaxException {
        log.debug("REST request to update CommMediaType : {}", commMediaType);
        if (commMediaType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CommMediaType result = commMediaTypeService.save(commMediaType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, commMediaType.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /comm-media-types} : get all the commMediaTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of commMediaTypes in body.
     */
    @GetMapping("/comm-media-types")
    public ResponseEntity<List<CommMediaType>> getAllCommMediaTypes(Pageable pageable) {
        log.debug("REST request to get a page of CommMediaTypes");
        Page<CommMediaType> page = commMediaTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /comm-media-types/:id} : get the "id" commMediaType.
     *
     * @param id the id of the commMediaType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the commMediaType, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/comm-media-types/{id}")
    public ResponseEntity<CommMediaType> getCommMediaType(@PathVariable String id) {
        log.debug("REST request to get CommMediaType : {}", id);
        Optional<CommMediaType> commMediaType = commMediaTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(commMediaType);
    }

    /**
     * {@code DELETE  /comm-media-types/:id} : delete the "id" commMediaType.
     *
     * @param id the id of the commMediaType to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/comm-media-types/{id}")
    public ResponseEntity<Void> deleteCommMediaType(@PathVariable String id) {
        log.debug("REST request to delete CommMediaType : {}", id);
        commMediaTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
