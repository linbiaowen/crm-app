package com.hthk.crm.web.rest;

import com.hthk.crm.domain.ResourceSpecification;
import com.hthk.crm.service.ResourceSpecificationService;
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
 * REST controller for managing {@link com.hthk.crm.domain.ResourceSpecification}.
 */
@RestController
@RequestMapping("/api")
public class ResourceSpecificationResource {

    private final Logger log = LoggerFactory.getLogger(ResourceSpecificationResource.class);

    private static final String ENTITY_NAME = "resourceSpecification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResourceSpecificationService resourceSpecificationService;

    public ResourceSpecificationResource(ResourceSpecificationService resourceSpecificationService) {
        this.resourceSpecificationService = resourceSpecificationService;
    }

    /**
     * {@code POST  /resource-specifications} : Create a new resourceSpecification.
     *
     * @param resourceSpecification the resourceSpecification to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resourceSpecification, or with status {@code 400 (Bad Request)} if the resourceSpecification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/resource-specifications")
    public ResponseEntity<ResourceSpecification> createResourceSpecification(@Valid @RequestBody ResourceSpecification resourceSpecification) throws URISyntaxException {
        log.debug("REST request to save ResourceSpecification : {}", resourceSpecification);
        if (resourceSpecification.getId() != null) {
            throw new BadRequestAlertException("A new resourceSpecification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResourceSpecification result = resourceSpecificationService.save(resourceSpecification);
        return ResponseEntity.created(new URI("/api/resource-specifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /resource-specifications} : Updates an existing resourceSpecification.
     *
     * @param resourceSpecification the resourceSpecification to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resourceSpecification,
     * or with status {@code 400 (Bad Request)} if the resourceSpecification is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resourceSpecification couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/resource-specifications")
    public ResponseEntity<ResourceSpecification> updateResourceSpecification(@Valid @RequestBody ResourceSpecification resourceSpecification) throws URISyntaxException {
        log.debug("REST request to update ResourceSpecification : {}", resourceSpecification);
        if (resourceSpecification.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ResourceSpecification result = resourceSpecificationService.save(resourceSpecification);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resourceSpecification.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /resource-specifications} : get all the resourceSpecifications.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resourceSpecifications in body.
     */
    @GetMapping("/resource-specifications")
    public ResponseEntity<List<ResourceSpecification>> getAllResourceSpecifications(Pageable pageable) {
        log.debug("REST request to get a page of ResourceSpecifications");
        Page<ResourceSpecification> page = resourceSpecificationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /resource-specifications/:id} : get the "id" resourceSpecification.
     *
     * @param id the id of the resourceSpecification to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resourceSpecification, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/resource-specifications/{id}")
    public ResponseEntity<ResourceSpecification> getResourceSpecification(@PathVariable String id) {
        log.debug("REST request to get ResourceSpecification : {}", id);
        Optional<ResourceSpecification> resourceSpecification = resourceSpecificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resourceSpecification);
    }

    /**
     * {@code DELETE  /resource-specifications/:id} : delete the "id" resourceSpecification.
     *
     * @param id the id of the resourceSpecification to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/resource-specifications/{id}")
    public ResponseEntity<Void> deleteResourceSpecification(@PathVariable String id) {
        log.debug("REST request to delete ResourceSpecification : {}", id);
        resourceSpecificationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
