package com.hthk.crm.web.rest;

import com.hthk.crm.domain.OrderMaster;
import com.hthk.crm.service.OrderMasterService;
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
 * REST controller for managing {@link com.hthk.crm.domain.OrderMaster}.
 */
@RestController
@RequestMapping("/api")
public class OrderMasterResource {

    private final Logger log = LoggerFactory.getLogger(OrderMasterResource.class);

    private static final String ENTITY_NAME = "orderMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderMasterService orderMasterService;

    public OrderMasterResource(OrderMasterService orderMasterService) {
        this.orderMasterService = orderMasterService;
    }

    /**
     * {@code POST  /order-masters} : Create a new orderMaster.
     *
     * @param orderMaster the orderMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderMaster, or with status {@code 400 (Bad Request)} if the orderMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/order-masters")
    public ResponseEntity<OrderMaster> createOrderMaster(@Valid @RequestBody OrderMaster orderMaster) throws URISyntaxException {
        log.debug("REST request to save OrderMaster : {}", orderMaster);
        if (orderMaster.getId() != null) {
            throw new BadRequestAlertException("A new orderMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderMaster result = orderMasterService.save(orderMaster);
        return ResponseEntity.created(new URI("/api/order-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /order-masters} : Updates an existing orderMaster.
     *
     * @param orderMaster the orderMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderMaster,
     * or with status {@code 400 (Bad Request)} if the orderMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/order-masters")
    public ResponseEntity<OrderMaster> updateOrderMaster(@Valid @RequestBody OrderMaster orderMaster) throws URISyntaxException {
        log.debug("REST request to update OrderMaster : {}", orderMaster);
        if (orderMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrderMaster result = orderMasterService.save(orderMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /order-masters} : get all the orderMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderMasters in body.
     */
    @GetMapping("/order-masters")
    public ResponseEntity<List<OrderMaster>> getAllOrderMasters(Pageable pageable) {
        log.debug("REST request to get a page of OrderMasters");
        Page<OrderMaster> page = orderMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /order-masters/:id} : get the "id" orderMaster.
     *
     * @param id the id of the orderMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/order-masters/{id}")
    public ResponseEntity<OrderMaster> getOrderMaster(@PathVariable String id) {
        log.debug("REST request to get OrderMaster : {}", id);
        Optional<OrderMaster> orderMaster = orderMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderMaster);
    }

    /**
     * {@code DELETE  /order-masters/:id} : delete the "id" orderMaster.
     *
     * @param id the id of the orderMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/order-masters/{id}")
    public ResponseEntity<Void> deleteOrderMaster(@PathVariable String id) {
        log.debug("REST request to delete OrderMaster : {}", id);
        orderMasterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
