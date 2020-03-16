package com.hthk.crm.web.rest;

import com.hthk.crm.domain.DeliveryOption;
import com.hthk.crm.service.DeliveryOptionService;
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
 * REST controller for managing {@link com.hthk.crm.domain.DeliveryOption}.
 */
@RestController
@RequestMapping("/api")
public class DeliveryOptionResource {

    private final Logger log = LoggerFactory.getLogger(DeliveryOptionResource.class);

    private static final String ENTITY_NAME = "deliveryOption";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeliveryOptionService deliveryOptionService;

    public DeliveryOptionResource(DeliveryOptionService deliveryOptionService) {
        this.deliveryOptionService = deliveryOptionService;
    }

    /**
     * {@code POST  /delivery-options} : Create a new deliveryOption.
     *
     * @param deliveryOption the deliveryOption to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deliveryOption, or with status {@code 400 (Bad Request)} if the deliveryOption has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/delivery-options")
    public ResponseEntity<DeliveryOption> createDeliveryOption(@Valid @RequestBody DeliveryOption deliveryOption) throws URISyntaxException {
        log.debug("REST request to save DeliveryOption : {}", deliveryOption);
        if (deliveryOption.getId() != null) {
            throw new BadRequestAlertException("A new deliveryOption cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeliveryOption result = deliveryOptionService.save(deliveryOption);
        return ResponseEntity.created(new URI("/api/delivery-options/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /delivery-options} : Updates an existing deliveryOption.
     *
     * @param deliveryOption the deliveryOption to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliveryOption,
     * or with status {@code 400 (Bad Request)} if the deliveryOption is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deliveryOption couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/delivery-options")
    public ResponseEntity<DeliveryOption> updateDeliveryOption(@Valid @RequestBody DeliveryOption deliveryOption) throws URISyntaxException {
        log.debug("REST request to update DeliveryOption : {}", deliveryOption);
        if (deliveryOption.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DeliveryOption result = deliveryOptionService.save(deliveryOption);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deliveryOption.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /delivery-options} : get all the deliveryOptions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deliveryOptions in body.
     */
    @GetMapping("/delivery-options")
    public ResponseEntity<List<DeliveryOption>> getAllDeliveryOptions(Pageable pageable) {
        log.debug("REST request to get a page of DeliveryOptions");
        Page<DeliveryOption> page = deliveryOptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /delivery-options/:id} : get the "id" deliveryOption.
     *
     * @param id the id of the deliveryOption to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deliveryOption, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/delivery-options/{id}")
    public ResponseEntity<DeliveryOption> getDeliveryOption(@PathVariable String id) {
        log.debug("REST request to get DeliveryOption : {}", id);
        Optional<DeliveryOption> deliveryOption = deliveryOptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deliveryOption);
    }

    /**
     * {@code DELETE  /delivery-options/:id} : delete the "id" deliveryOption.
     *
     * @param id the id of the deliveryOption to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/delivery-options/{id}")
    public ResponseEntity<Void> deleteDeliveryOption(@PathVariable String id) {
        log.debug("REST request to delete DeliveryOption : {}", id);
        deliveryOptionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
