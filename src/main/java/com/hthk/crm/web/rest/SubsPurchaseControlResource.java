package com.hthk.crm.web.rest;

import com.hthk.crm.domain.SubsPurchaseControl;
import com.hthk.crm.service.SubsPurchaseControlService;
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
 * REST controller for managing {@link com.hthk.crm.domain.SubsPurchaseControl}.
 */
@RestController
@RequestMapping("/api")
public class SubsPurchaseControlResource {

    private final Logger log = LoggerFactory.getLogger(SubsPurchaseControlResource.class);

    private static final String ENTITY_NAME = "subsPurchaseControl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SubsPurchaseControlService subsPurchaseControlService;

    public SubsPurchaseControlResource(SubsPurchaseControlService subsPurchaseControlService) {
        this.subsPurchaseControlService = subsPurchaseControlService;
    }

    /**
     * {@code POST  /subs-purchase-controls} : Create a new subsPurchaseControl.
     *
     * @param subsPurchaseControl the subsPurchaseControl to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subsPurchaseControl, or with status {@code 400 (Bad Request)} if the subsPurchaseControl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/subs-purchase-controls")
    public ResponseEntity<SubsPurchaseControl> createSubsPurchaseControl(@Valid @RequestBody SubsPurchaseControl subsPurchaseControl) throws URISyntaxException {
        log.debug("REST request to save SubsPurchaseControl : {}", subsPurchaseControl);
        if (subsPurchaseControl.getId() != null) {
            throw new BadRequestAlertException("A new subsPurchaseControl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SubsPurchaseControl result = subsPurchaseControlService.save(subsPurchaseControl);
        return ResponseEntity.created(new URI("/api/subs-purchase-controls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /subs-purchase-controls} : Updates an existing subsPurchaseControl.
     *
     * @param subsPurchaseControl the subsPurchaseControl to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subsPurchaseControl,
     * or with status {@code 400 (Bad Request)} if the subsPurchaseControl is not valid,
     * or with status {@code 500 (Internal Server Error)} if the subsPurchaseControl couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/subs-purchase-controls")
    public ResponseEntity<SubsPurchaseControl> updateSubsPurchaseControl(@Valid @RequestBody SubsPurchaseControl subsPurchaseControl) throws URISyntaxException {
        log.debug("REST request to update SubsPurchaseControl : {}", subsPurchaseControl);
        if (subsPurchaseControl.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SubsPurchaseControl result = subsPurchaseControlService.save(subsPurchaseControl);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, subsPurchaseControl.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /subs-purchase-controls} : get all the subsPurchaseControls.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subsPurchaseControls in body.
     */
    @GetMapping("/subs-purchase-controls")
    public ResponseEntity<List<SubsPurchaseControl>> getAllSubsPurchaseControls(Pageable pageable) {
        log.debug("REST request to get a page of SubsPurchaseControls");
        Page<SubsPurchaseControl> page = subsPurchaseControlService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /subs-purchase-controls/:id} : get the "id" subsPurchaseControl.
     *
     * @param id the id of the subsPurchaseControl to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subsPurchaseControl, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/subs-purchase-controls/{id}")
    public ResponseEntity<SubsPurchaseControl> getSubsPurchaseControl(@PathVariable String id) {
        log.debug("REST request to get SubsPurchaseControl : {}", id);
        Optional<SubsPurchaseControl> subsPurchaseControl = subsPurchaseControlService.findOne(id);
        return ResponseUtil.wrapOrNotFound(subsPurchaseControl);
    }

    /**
     * {@code DELETE  /subs-purchase-controls/:id} : delete the "id" subsPurchaseControl.
     *
     * @param id the id of the subsPurchaseControl to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/subs-purchase-controls/{id}")
    public ResponseEntity<Void> deleteSubsPurchaseControl(@PathVariable String id) {
        log.debug("REST request to delete SubsPurchaseControl : {}", id);
        subsPurchaseControlService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
