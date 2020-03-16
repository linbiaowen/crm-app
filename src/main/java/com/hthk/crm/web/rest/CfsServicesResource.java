package com.hthk.crm.web.rest;

import com.hthk.crm.domain.CfsServices;
import com.hthk.crm.service.CfsServicesService;
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
 * REST controller for managing {@link com.hthk.crm.domain.CfsServices}.
 */
@RestController
@RequestMapping("/api")
public class CfsServicesResource {

    private final Logger log = LoggerFactory.getLogger(CfsServicesResource.class);

    private static final String ENTITY_NAME = "cfsServices";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CfsServicesService cfsServicesService;

    public CfsServicesResource(CfsServicesService cfsServicesService) {
        this.cfsServicesService = cfsServicesService;
    }

    /**
     * {@code POST  /cfs-services} : Create a new cfsServices.
     *
     * @param cfsServices the cfsServices to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cfsServices, or with status {@code 400 (Bad Request)} if the cfsServices has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cfs-services")
    public ResponseEntity<CfsServices> createCfsServices(@Valid @RequestBody CfsServices cfsServices) throws URISyntaxException {
        log.debug("REST request to save CfsServices : {}", cfsServices);
        if (cfsServices.getId() != null) {
            throw new BadRequestAlertException("A new cfsServices cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CfsServices result = cfsServicesService.save(cfsServices);
        return ResponseEntity.created(new URI("/api/cfs-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cfs-services} : Updates an existing cfsServices.
     *
     * @param cfsServices the cfsServices to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cfsServices,
     * or with status {@code 400 (Bad Request)} if the cfsServices is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cfsServices couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cfs-services")
    public ResponseEntity<CfsServices> updateCfsServices(@Valid @RequestBody CfsServices cfsServices) throws URISyntaxException {
        log.debug("REST request to update CfsServices : {}", cfsServices);
        if (cfsServices.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CfsServices result = cfsServicesService.save(cfsServices);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cfsServices.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cfs-services} : get all the cfsServices.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cfsServices in body.
     */
    @GetMapping("/cfs-services")
    public ResponseEntity<List<CfsServices>> getAllCfsServices(Pageable pageable) {
        log.debug("REST request to get a page of CfsServices");
        Page<CfsServices> page = cfsServicesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cfs-services/:id} : get the "id" cfsServices.
     *
     * @param id the id of the cfsServices to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cfsServices, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cfs-services/{id}")
    public ResponseEntity<CfsServices> getCfsServices(@PathVariable String id) {
        log.debug("REST request to get CfsServices : {}", id);
        Optional<CfsServices> cfsServices = cfsServicesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cfsServices);
    }

    /**
     * {@code DELETE  /cfs-services/:id} : delete the "id" cfsServices.
     *
     * @param id the id of the cfsServices to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cfs-services/{id}")
    public ResponseEntity<Void> deleteCfsServices(@PathVariable String id) {
        log.debug("REST request to delete CfsServices : {}", id);
        cfsServicesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
