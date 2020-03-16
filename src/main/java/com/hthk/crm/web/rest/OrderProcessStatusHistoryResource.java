package com.hthk.crm.web.rest;

import com.hthk.crm.domain.OrderProcessStatusHistory;
import com.hthk.crm.service.OrderProcessStatusHistoryService;
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
 * REST controller for managing {@link com.hthk.crm.domain.OrderProcessStatusHistory}.
 */
@RestController
@RequestMapping("/api")
public class OrderProcessStatusHistoryResource {

    private final Logger log = LoggerFactory.getLogger(OrderProcessStatusHistoryResource.class);

    private static final String ENTITY_NAME = "orderProcessStatusHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderProcessStatusHistoryService orderProcessStatusHistoryService;

    public OrderProcessStatusHistoryResource(OrderProcessStatusHistoryService orderProcessStatusHistoryService) {
        this.orderProcessStatusHistoryService = orderProcessStatusHistoryService;
    }

    /**
     * {@code POST  /order-process-status-histories} : Create a new orderProcessStatusHistory.
     *
     * @param orderProcessStatusHistory the orderProcessStatusHistory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderProcessStatusHistory, or with status {@code 400 (Bad Request)} if the orderProcessStatusHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/order-process-status-histories")
    public ResponseEntity<OrderProcessStatusHistory> createOrderProcessStatusHistory(@Valid @RequestBody OrderProcessStatusHistory orderProcessStatusHistory) throws URISyntaxException {
        log.debug("REST request to save OrderProcessStatusHistory : {}", orderProcessStatusHistory);
        if (orderProcessStatusHistory.getId() != null) {
            throw new BadRequestAlertException("A new orderProcessStatusHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderProcessStatusHistory result = orderProcessStatusHistoryService.save(orderProcessStatusHistory);
        return ResponseEntity.created(new URI("/api/order-process-status-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /order-process-status-histories} : Updates an existing orderProcessStatusHistory.
     *
     * @param orderProcessStatusHistory the orderProcessStatusHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderProcessStatusHistory,
     * or with status {@code 400 (Bad Request)} if the orderProcessStatusHistory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderProcessStatusHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/order-process-status-histories")
    public ResponseEntity<OrderProcessStatusHistory> updateOrderProcessStatusHistory(@Valid @RequestBody OrderProcessStatusHistory orderProcessStatusHistory) throws URISyntaxException {
        log.debug("REST request to update OrderProcessStatusHistory : {}", orderProcessStatusHistory);
        if (orderProcessStatusHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrderProcessStatusHistory result = orderProcessStatusHistoryService.save(orderProcessStatusHistory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderProcessStatusHistory.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /order-process-status-histories} : get all the orderProcessStatusHistories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderProcessStatusHistories in body.
     */
    @GetMapping("/order-process-status-histories")
    public ResponseEntity<List<OrderProcessStatusHistory>> getAllOrderProcessStatusHistories(Pageable pageable) {
        log.debug("REST request to get a page of OrderProcessStatusHistories");
        Page<OrderProcessStatusHistory> page = orderProcessStatusHistoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /order-process-status-histories/:id} : get the "id" orderProcessStatusHistory.
     *
     * @param id the id of the orderProcessStatusHistory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderProcessStatusHistory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/order-process-status-histories/{id}")
    public ResponseEntity<OrderProcessStatusHistory> getOrderProcessStatusHistory(@PathVariable String id) {
        log.debug("REST request to get OrderProcessStatusHistory : {}", id);
        Optional<OrderProcessStatusHistory> orderProcessStatusHistory = orderProcessStatusHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderProcessStatusHistory);
    }

    /**
     * {@code DELETE  /order-process-status-histories/:id} : delete the "id" orderProcessStatusHistory.
     *
     * @param id the id of the orderProcessStatusHistory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/order-process-status-histories/{id}")
    public ResponseEntity<Void> deleteOrderProcessStatusHistory(@PathVariable String id) {
        log.debug("REST request to delete OrderProcessStatusHistory : {}", id);
        orderProcessStatusHistoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
