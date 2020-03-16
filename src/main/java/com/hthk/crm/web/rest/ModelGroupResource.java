package com.hthk.crm.web.rest;

import com.hthk.crm.domain.ModelGroup;
import com.hthk.crm.service.ModelGroupService;
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
 * REST controller for managing {@link com.hthk.crm.domain.ModelGroup}.
 */
@RestController
@RequestMapping("/api")
public class ModelGroupResource {

    private final Logger log = LoggerFactory.getLogger(ModelGroupResource.class);

    private static final String ENTITY_NAME = "modelGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ModelGroupService modelGroupService;

    public ModelGroupResource(ModelGroupService modelGroupService) {
        this.modelGroupService = modelGroupService;
    }

    /**
     * {@code POST  /model-groups} : Create a new modelGroup.
     *
     * @param modelGroup the modelGroup to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new modelGroup, or with status {@code 400 (Bad Request)} if the modelGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/model-groups")
    public ResponseEntity<ModelGroup> createModelGroup(@Valid @RequestBody ModelGroup modelGroup) throws URISyntaxException {
        log.debug("REST request to save ModelGroup : {}", modelGroup);
        if (modelGroup.getId() != null) {
            throw new BadRequestAlertException("A new modelGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ModelGroup result = modelGroupService.save(modelGroup);
        return ResponseEntity.created(new URI("/api/model-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /model-groups} : Updates an existing modelGroup.
     *
     * @param modelGroup the modelGroup to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated modelGroup,
     * or with status {@code 400 (Bad Request)} if the modelGroup is not valid,
     * or with status {@code 500 (Internal Server Error)} if the modelGroup couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/model-groups")
    public ResponseEntity<ModelGroup> updateModelGroup(@Valid @RequestBody ModelGroup modelGroup) throws URISyntaxException {
        log.debug("REST request to update ModelGroup : {}", modelGroup);
        if (modelGroup.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ModelGroup result = modelGroupService.save(modelGroup);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, modelGroup.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /model-groups} : get all the modelGroups.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of modelGroups in body.
     */
    @GetMapping("/model-groups")
    public ResponseEntity<List<ModelGroup>> getAllModelGroups(Pageable pageable) {
        log.debug("REST request to get a page of ModelGroups");
        Page<ModelGroup> page = modelGroupService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /model-groups/:id} : get the "id" modelGroup.
     *
     * @param id the id of the modelGroup to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the modelGroup, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/model-groups/{id}")
    public ResponseEntity<ModelGroup> getModelGroup(@PathVariable String id) {
        log.debug("REST request to get ModelGroup : {}", id);
        Optional<ModelGroup> modelGroup = modelGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(modelGroup);
    }

    /**
     * {@code DELETE  /model-groups/:id} : delete the "id" modelGroup.
     *
     * @param id the id of the modelGroup to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/model-groups/{id}")
    public ResponseEntity<Void> deleteModelGroup(@PathVariable String id) {
        log.debug("REST request to delete ModelGroup : {}", id);
        modelGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
