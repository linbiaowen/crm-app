package com.hthk.crm.web.rest;

import com.hthk.crm.domain.SimInventory;
import com.hthk.crm.service.SimInventoryService;
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
 * REST controller for managing {@link com.hthk.crm.domain.SimInventory}.
 */
@RestController
@RequestMapping("/api")
public class SimInventoryResource {

    private final Logger log = LoggerFactory.getLogger(SimInventoryResource.class);

    private static final String ENTITY_NAME = "simInventory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SimInventoryService simInventoryService;

    public SimInventoryResource(SimInventoryService simInventoryService) {
        this.simInventoryService = simInventoryService;
    }

    /**
     * {@code POST  /sim-inventories} : Create a new simInventory.
     *
     * @param simInventory the simInventory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new simInventory, or with status {@code 400 (Bad Request)} if the simInventory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sim-inventories")
    public ResponseEntity<SimInventory> createSimInventory(@Valid @RequestBody SimInventory simInventory) throws URISyntaxException {
        log.debug("REST request to save SimInventory : {}", simInventory);
        if (simInventory.getId() != null) {
            throw new BadRequestAlertException("A new simInventory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SimInventory result = simInventoryService.save(simInventory);
        return ResponseEntity.created(new URI("/api/sim-inventories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sim-inventories} : Updates an existing simInventory.
     *
     * @param simInventory the simInventory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated simInventory,
     * or with status {@code 400 (Bad Request)} if the simInventory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the simInventory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sim-inventories")
    public ResponseEntity<SimInventory> updateSimInventory(@Valid @RequestBody SimInventory simInventory) throws URISyntaxException {
        log.debug("REST request to update SimInventory : {}", simInventory);
        if (simInventory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SimInventory result = simInventoryService.save(simInventory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, simInventory.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sim-inventories} : get all the simInventories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of simInventories in body.
     */
    @GetMapping("/sim-inventories")
    public ResponseEntity<List<SimInventory>> getAllSimInventories(Pageable pageable) {
        log.debug("REST request to get a page of SimInventories");
        Page<SimInventory> page = simInventoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sim-inventories/:id} : get the "id" simInventory.
     *
     * @param id the id of the simInventory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the simInventory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sim-inventories/{id}")
    public ResponseEntity<SimInventory> getSimInventory(@PathVariable String id) {
        log.debug("REST request to get SimInventory : {}", id);
        Optional<SimInventory> simInventory = simInventoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(simInventory);
    }

    /**
     * {@code DELETE  /sim-inventories/:id} : delete the "id" simInventory.
     *
     * @param id the id of the simInventory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sim-inventories/{id}")
    public ResponseEntity<Void> deleteSimInventory(@PathVariable String id) {
        log.debug("REST request to delete SimInventory : {}", id);
        simInventoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
