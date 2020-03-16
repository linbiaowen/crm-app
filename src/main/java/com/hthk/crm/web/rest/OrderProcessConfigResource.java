package com.hthk.crm.web.rest;

import com.hthk.crm.domain.OrderProcessConfig;
import com.hthk.crm.service.OrderProcessConfigService;
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
 * REST controller for managing {@link com.hthk.crm.domain.OrderProcessConfig}.
 */
@RestController
@RequestMapping("/api")
public class OrderProcessConfigResource {

    private final Logger log = LoggerFactory.getLogger(OrderProcessConfigResource.class);

    private static final String ENTITY_NAME = "orderProcessConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderProcessConfigService orderProcessConfigService;

    public OrderProcessConfigResource(OrderProcessConfigService orderProcessConfigService) {
        this.orderProcessConfigService = orderProcessConfigService;
    }

    /**
     * {@code POST  /order-process-configs} : Create a new orderProcessConfig.
     *
     * @param orderProcessConfig the orderProcessConfig to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderProcessConfig, or with status {@code 400 (Bad Request)} if the orderProcessConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/order-process-configs")
    public ResponseEntity<OrderProcessConfig> createOrderProcessConfig(@Valid @RequestBody OrderProcessConfig orderProcessConfig) throws URISyntaxException {
        log.debug("REST request to save OrderProcessConfig : {}", orderProcessConfig);
        if (orderProcessConfig.getId() != null) {
            throw new BadRequestAlertException("A new orderProcessConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderProcessConfig result = orderProcessConfigService.save(orderProcessConfig);
        return ResponseEntity.created(new URI("/api/order-process-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /order-process-configs} : Updates an existing orderProcessConfig.
     *
     * @param orderProcessConfig the orderProcessConfig to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderProcessConfig,
     * or with status {@code 400 (Bad Request)} if the orderProcessConfig is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderProcessConfig couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/order-process-configs")
    public ResponseEntity<OrderProcessConfig> updateOrderProcessConfig(@Valid @RequestBody OrderProcessConfig orderProcessConfig) throws URISyntaxException {
        log.debug("REST request to update OrderProcessConfig : {}", orderProcessConfig);
        if (orderProcessConfig.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrderProcessConfig result = orderProcessConfigService.save(orderProcessConfig);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderProcessConfig.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /order-process-configs} : get all the orderProcessConfigs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderProcessConfigs in body.
     */
    @GetMapping("/order-process-configs")
    public ResponseEntity<List<OrderProcessConfig>> getAllOrderProcessConfigs(Pageable pageable) {
        log.debug("REST request to get a page of OrderProcessConfigs");
        Page<OrderProcessConfig> page = orderProcessConfigService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /order-process-configs/:id} : get the "id" orderProcessConfig.
     *
     * @param id the id of the orderProcessConfig to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderProcessConfig, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/order-process-configs/{id}")
    public ResponseEntity<OrderProcessConfig> getOrderProcessConfig(@PathVariable String id) {
        log.debug("REST request to get OrderProcessConfig : {}", id);
        Optional<OrderProcessConfig> orderProcessConfig = orderProcessConfigService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderProcessConfig);
    }

    /**
     * {@code DELETE  /order-process-configs/:id} : delete the "id" orderProcessConfig.
     *
     * @param id the id of the orderProcessConfig to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/order-process-configs/{id}")
    public ResponseEntity<Void> deleteOrderProcessConfig(@PathVariable String id) {
        log.debug("REST request to delete OrderProcessConfig : {}", id);
        orderProcessConfigService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
