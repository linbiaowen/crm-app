package com.hthk.crm.web.rest;

import com.hthk.crm.domain.ModelCategory;
import com.hthk.crm.service.ModelCategoryService;
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
 * REST controller for managing {@link com.hthk.crm.domain.ModelCategory}.
 */
@RestController
@RequestMapping("/api")
public class ModelCategoryResource {

    private final Logger log = LoggerFactory.getLogger(ModelCategoryResource.class);

    private static final String ENTITY_NAME = "modelCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ModelCategoryService modelCategoryService;

    public ModelCategoryResource(ModelCategoryService modelCategoryService) {
        this.modelCategoryService = modelCategoryService;
    }

    /**
     * {@code POST  /model-categories} : Create a new modelCategory.
     *
     * @param modelCategory the modelCategory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new modelCategory, or with status {@code 400 (Bad Request)} if the modelCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/model-categories")
    public ResponseEntity<ModelCategory> createModelCategory(@Valid @RequestBody ModelCategory modelCategory) throws URISyntaxException {
        log.debug("REST request to save ModelCategory : {}", modelCategory);
        if (modelCategory.getId() != null) {
            throw new BadRequestAlertException("A new modelCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ModelCategory result = modelCategoryService.save(modelCategory);
        return ResponseEntity.created(new URI("/api/model-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /model-categories} : Updates an existing modelCategory.
     *
     * @param modelCategory the modelCategory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated modelCategory,
     * or with status {@code 400 (Bad Request)} if the modelCategory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the modelCategory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/model-categories")
    public ResponseEntity<ModelCategory> updateModelCategory(@Valid @RequestBody ModelCategory modelCategory) throws URISyntaxException {
        log.debug("REST request to update ModelCategory : {}", modelCategory);
        if (modelCategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ModelCategory result = modelCategoryService.save(modelCategory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, modelCategory.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /model-categories} : get all the modelCategories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of modelCategories in body.
     */
    @GetMapping("/model-categories")
    public ResponseEntity<List<ModelCategory>> getAllModelCategories(Pageable pageable) {
        log.debug("REST request to get a page of ModelCategories");
        Page<ModelCategory> page = modelCategoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /model-categories/:id} : get the "id" modelCategory.
     *
     * @param id the id of the modelCategory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the modelCategory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/model-categories/{id}")
    public ResponseEntity<ModelCategory> getModelCategory(@PathVariable String id) {
        log.debug("REST request to get ModelCategory : {}", id);
        Optional<ModelCategory> modelCategory = modelCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(modelCategory);
    }

    /**
     * {@code DELETE  /model-categories/:id} : delete the "id" modelCategory.
     *
     * @param id the id of the modelCategory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/model-categories/{id}")
    public ResponseEntity<Void> deleteModelCategory(@PathVariable String id) {
        log.debug("REST request to delete ModelCategory : {}", id);
        modelCategoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
