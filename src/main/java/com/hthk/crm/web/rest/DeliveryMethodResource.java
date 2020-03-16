package com.hthk.crm.web.rest;

import com.hthk.crm.domain.DeliveryMethod;
import com.hthk.crm.service.DeliveryMethodService;
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
 * REST controller for managing {@link com.hthk.crm.domain.DeliveryMethod}.
 */
@RestController
@RequestMapping("/api")
public class DeliveryMethodResource {

    private final Logger log = LoggerFactory.getLogger(DeliveryMethodResource.class);

    private static final String ENTITY_NAME = "deliveryMethod";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeliveryMethodService deliveryMethodService;

    public DeliveryMethodResource(DeliveryMethodService deliveryMethodService) {
        this.deliveryMethodService = deliveryMethodService;
    }

    /**
     * {@code POST  /delivery-methods} : Create a new deliveryMethod.
     *
     * @param deliveryMethod the deliveryMethod to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deliveryMethod, or with status {@code 400 (Bad Request)} if the deliveryMethod has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/delivery-methods")
    public ResponseEntity<DeliveryMethod> createDeliveryMethod(@Valid @RequestBody DeliveryMethod deliveryMethod) throws URISyntaxException {
        log.debug("REST request to save DeliveryMethod : {}", deliveryMethod);
        if (deliveryMethod.getId() != null) {
            throw new BadRequestAlertException("A new deliveryMethod cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeliveryMethod result = deliveryMethodService.save(deliveryMethod);
        return ResponseEntity.created(new URI("/api/delivery-methods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /delivery-methods} : Updates an existing deliveryMethod.
     *
     * @param deliveryMethod the deliveryMethod to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliveryMethod,
     * or with status {@code 400 (Bad Request)} if the deliveryMethod is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deliveryMethod couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/delivery-methods")
    public ResponseEntity<DeliveryMethod> updateDeliveryMethod(@Valid @RequestBody DeliveryMethod deliveryMethod) throws URISyntaxException {
        log.debug("REST request to update DeliveryMethod : {}", deliveryMethod);
        if (deliveryMethod.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DeliveryMethod result = deliveryMethodService.save(deliveryMethod);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deliveryMethod.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /delivery-methods} : get all the deliveryMethods.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deliveryMethods in body.
     */
    @GetMapping("/delivery-methods")
    public ResponseEntity<List<DeliveryMethod>> getAllDeliveryMethods(Pageable pageable) {
        log.debug("REST request to get a page of DeliveryMethods");
        Page<DeliveryMethod> page = deliveryMethodService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /delivery-methods/:id} : get the "id" deliveryMethod.
     *
     * @param id the id of the deliveryMethod to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deliveryMethod, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/delivery-methods/{id}")
    public ResponseEntity<DeliveryMethod> getDeliveryMethod(@PathVariable String id) {
        log.debug("REST request to get DeliveryMethod : {}", id);
        Optional<DeliveryMethod> deliveryMethod = deliveryMethodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deliveryMethod);
    }

    /**
     * {@code DELETE  /delivery-methods/:id} : delete the "id" deliveryMethod.
     *
     * @param id the id of the deliveryMethod to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/delivery-methods/{id}")
    public ResponseEntity<Void> deleteDeliveryMethod(@PathVariable String id) {
        log.debug("REST request to delete DeliveryMethod : {}", id);
        deliveryMethodService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
