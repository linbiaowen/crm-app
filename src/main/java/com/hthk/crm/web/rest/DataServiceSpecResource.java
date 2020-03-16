package com.hthk.crm.web.rest;

import com.hthk.crm.domain.DataServiceSpec;
import com.hthk.crm.service.DataServiceSpecService;
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
 * REST controller for managing {@link com.hthk.crm.domain.DataServiceSpec}.
 */
@RestController
@RequestMapping("/api")
public class DataServiceSpecResource {

    private final Logger log = LoggerFactory.getLogger(DataServiceSpecResource.class);

    private static final String ENTITY_NAME = "dataServiceSpec";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DataServiceSpecService dataServiceSpecService;

    public DataServiceSpecResource(DataServiceSpecService dataServiceSpecService) {
        this.dataServiceSpecService = dataServiceSpecService;
    }

    /**
     * {@code POST  /data-service-specs} : Create a new dataServiceSpec.
     *
     * @param dataServiceSpec the dataServiceSpec to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dataServiceSpec, or with status {@code 400 (Bad Request)} if the dataServiceSpec has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/data-service-specs")
    public ResponseEntity<DataServiceSpec> createDataServiceSpec(@Valid @RequestBody DataServiceSpec dataServiceSpec) throws URISyntaxException {
        log.debug("REST request to save DataServiceSpec : {}", dataServiceSpec);
        if (dataServiceSpec.getId() != null) {
            throw new BadRequestAlertException("A new dataServiceSpec cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DataServiceSpec result = dataServiceSpecService.save(dataServiceSpec);
        return ResponseEntity.created(new URI("/api/data-service-specs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /data-service-specs} : Updates an existing dataServiceSpec.
     *
     * @param dataServiceSpec the dataServiceSpec to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dataServiceSpec,
     * or with status {@code 400 (Bad Request)} if the dataServiceSpec is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dataServiceSpec couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/data-service-specs")
    public ResponseEntity<DataServiceSpec> updateDataServiceSpec(@Valid @RequestBody DataServiceSpec dataServiceSpec) throws URISyntaxException {
        log.debug("REST request to update DataServiceSpec : {}", dataServiceSpec);
        if (dataServiceSpec.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DataServiceSpec result = dataServiceSpecService.save(dataServiceSpec);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dataServiceSpec.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /data-service-specs} : get all the dataServiceSpecs.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dataServiceSpecs in body.
     */
    @GetMapping("/data-service-specs")
    public ResponseEntity<List<DataServiceSpec>> getAllDataServiceSpecs(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("cfsservices-is-null".equals(filter)) {
            log.debug("REST request to get all DataServiceSpecs where cfsServices is null");
            return new ResponseEntity<>(dataServiceSpecService.findAllWhereCfsServicesIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of DataServiceSpecs");
        Page<DataServiceSpec> page = dataServiceSpecService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /data-service-specs/:id} : get the "id" dataServiceSpec.
     *
     * @param id the id of the dataServiceSpec to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dataServiceSpec, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/data-service-specs/{id}")
    public ResponseEntity<DataServiceSpec> getDataServiceSpec(@PathVariable String id) {
        log.debug("REST request to get DataServiceSpec : {}", id);
        Optional<DataServiceSpec> dataServiceSpec = dataServiceSpecService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dataServiceSpec);
    }

    /**
     * {@code DELETE  /data-service-specs/:id} : delete the "id" dataServiceSpec.
     *
     * @param id the id of the dataServiceSpec to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/data-service-specs/{id}")
    public ResponseEntity<Void> deleteDataServiceSpec(@PathVariable String id) {
        log.debug("REST request to delete DataServiceSpec : {}", id);
        dataServiceSpecService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
