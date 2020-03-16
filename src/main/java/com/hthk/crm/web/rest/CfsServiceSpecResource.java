package com.hthk.crm.web.rest;

import com.hthk.crm.domain.CfsServiceSpec;
import com.hthk.crm.service.CfsServiceSpecService;
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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.hthk.crm.domain.CfsServiceSpec}.
 */
@RestController
@RequestMapping("/api")
public class CfsServiceSpecResource {

    private final Logger log = LoggerFactory.getLogger(CfsServiceSpecResource.class);

    private static final String ENTITY_NAME = "cfsServiceSpec";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CfsServiceSpecService cfsServiceSpecService;

    public CfsServiceSpecResource(CfsServiceSpecService cfsServiceSpecService) {
        this.cfsServiceSpecService = cfsServiceSpecService;
    }

    /**
     * {@code POST  /cfs-service-specs} : Create a new cfsServiceSpec.
     *
     * @param cfsServiceSpec the cfsServiceSpec to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cfsServiceSpec, or with status {@code 400 (Bad Request)} if the cfsServiceSpec has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cfs-service-specs")
    public ResponseEntity<CfsServiceSpec> createCfsServiceSpec(@Valid @RequestBody CfsServiceSpec cfsServiceSpec) throws URISyntaxException {
        log.debug("REST request to save CfsServiceSpec : {}", cfsServiceSpec);
        if (cfsServiceSpec.getId() != null) {
            throw new BadRequestAlertException("A new cfsServiceSpec cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CfsServiceSpec result = cfsServiceSpecService.save(cfsServiceSpec);
        return ResponseEntity.created(new URI("/api/cfs-service-specs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cfs-service-specs} : Updates an existing cfsServiceSpec.
     *
     * @param cfsServiceSpec the cfsServiceSpec to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cfsServiceSpec,
     * or with status {@code 400 (Bad Request)} if the cfsServiceSpec is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cfsServiceSpec couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cfs-service-specs")
    public ResponseEntity<CfsServiceSpec> updateCfsServiceSpec(@Valid @RequestBody CfsServiceSpec cfsServiceSpec) throws URISyntaxException {
        log.debug("REST request to update CfsServiceSpec : {}", cfsServiceSpec);
        if (cfsServiceSpec.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CfsServiceSpec result = cfsServiceSpecService.save(cfsServiceSpec);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cfsServiceSpec.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cfs-service-specs} : get all the cfsServiceSpecs.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cfsServiceSpecs in body.
     */
    @GetMapping("/cfs-service-specs")
    public ResponseEntity<List<CfsServiceSpec>> getAllCfsServiceSpecs(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("cfsservices-is-null".equals(filter)) {
            log.debug("REST request to get all CfsServiceSpecs where cfsServices is null");
            return new ResponseEntity<>(cfsServiceSpecService.findAllWhereCfsServicesIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of CfsServiceSpecs");
        Page<CfsServiceSpec> page = cfsServiceSpecService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cfs-service-specs/:id} : get the "id" cfsServiceSpec.
     *
     * @param id the id of the cfsServiceSpec to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cfsServiceSpec, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cfs-service-specs/{id}")
    public ResponseEntity<CfsServiceSpec> getCfsServiceSpec(@PathVariable String id) {
        log.debug("REST request to get CfsServiceSpec : {}", id);
        Optional<CfsServiceSpec> cfsServiceSpec = cfsServiceSpecService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cfsServiceSpec);
    }

    /**
     * {@code DELETE  /cfs-service-specs/:id} : delete the "id" cfsServiceSpec.
     *
     * @param id the id of the cfsServiceSpec to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cfs-service-specs/{id}")
    public ResponseEntity<Void> deleteCfsServiceSpec(@PathVariable String id) {
        log.debug("REST request to delete CfsServiceSpec : {}", id);
        cfsServiceSpecService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
