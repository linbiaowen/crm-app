package com.hthk.crm.web.rest;

import com.hthk.crm.domain.OrderProcessStatus;
import com.hthk.crm.service.OrderProcessStatusService;
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
 * REST controller for managing {@link com.hthk.crm.domain.OrderProcessStatus}.
 */
@RestController
@RequestMapping("/api")
public class OrderProcessStatusResource {

    private final Logger log = LoggerFactory.getLogger(OrderProcessStatusResource.class);

    private static final String ENTITY_NAME = "orderProcessStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderProcessStatusService orderProcessStatusService;

    public OrderProcessStatusResource(OrderProcessStatusService orderProcessStatusService) {
        this.orderProcessStatusService = orderProcessStatusService;
    }

    /**
     * {@code POST  /order-process-statuses} : Create a new orderProcessStatus.
     *
     * @param orderProcessStatus the orderProcessStatus to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderProcessStatus, or with status {@code 400 (Bad Request)} if the orderProcessStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/order-process-statuses")
    public ResponseEntity<OrderProcessStatus> createOrderProcessStatus(@Valid @RequestBody OrderProcessStatus orderProcessStatus) throws URISyntaxException {
        log.debug("REST request to save OrderProcessStatus : {}", orderProcessStatus);
        if (orderProcessStatus.getId() != null) {
            throw new BadRequestAlertException("A new orderProcessStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderProcessStatus result = orderProcessStatusService.save(orderProcessStatus);
        return ResponseEntity.created(new URI("/api/order-process-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /order-process-statuses} : Updates an existing orderProcessStatus.
     *
     * @param orderProcessStatus the orderProcessStatus to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderProcessStatus,
     * or with status {@code 400 (Bad Request)} if the orderProcessStatus is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderProcessStatus couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/order-process-statuses")
    public ResponseEntity<OrderProcessStatus> updateOrderProcessStatus(@Valid @RequestBody OrderProcessStatus orderProcessStatus) throws URISyntaxException {
        log.debug("REST request to update OrderProcessStatus : {}", orderProcessStatus);
        if (orderProcessStatus.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrderProcessStatus result = orderProcessStatusService.save(orderProcessStatus);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderProcessStatus.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /order-process-statuses} : get all the orderProcessStatuses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderProcessStatuses in body.
     */
    @GetMapping("/order-process-statuses")
    public ResponseEntity<List<OrderProcessStatus>> getAllOrderProcessStatuses(Pageable pageable) {
        log.debug("REST request to get a page of OrderProcessStatuses");
        Page<OrderProcessStatus> page = orderProcessStatusService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /order-process-statuses/:id} : get the "id" orderProcessStatus.
     *
     * @param id the id of the orderProcessStatus to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderProcessStatus, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/order-process-statuses/{id}")
    public ResponseEntity<OrderProcessStatus> getOrderProcessStatus(@PathVariable String id) {
        log.debug("REST request to get OrderProcessStatus : {}", id);
        Optional<OrderProcessStatus> orderProcessStatus = orderProcessStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderProcessStatus);
    }

    /**
     * {@code DELETE  /order-process-statuses/:id} : delete the "id" orderProcessStatus.
     *
     * @param id the id of the orderProcessStatus to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/order-process-statuses/{id}")
    public ResponseEntity<Void> deleteOrderProcessStatus(@PathVariable String id) {
        log.debug("REST request to delete OrderProcessStatus : {}", id);
        orderProcessStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
