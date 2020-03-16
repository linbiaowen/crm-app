package com.hthk.crm.web.rest;

import com.hthk.crm.domain.SubscriptionProduct;
import com.hthk.crm.service.SubscriptionProductService;
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
 * REST controller for managing {@link com.hthk.crm.domain.SubscriptionProduct}.
 */
@RestController
@RequestMapping("/api")
public class SubscriptionProductResource {

    private final Logger log = LoggerFactory.getLogger(SubscriptionProductResource.class);

    private static final String ENTITY_NAME = "subscriptionProduct";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SubscriptionProductService subscriptionProductService;

    public SubscriptionProductResource(SubscriptionProductService subscriptionProductService) {
        this.subscriptionProductService = subscriptionProductService;
    }

    /**
     * {@code POST  /subscription-products} : Create a new subscriptionProduct.
     *
     * @param subscriptionProduct the subscriptionProduct to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subscriptionProduct, or with status {@code 400 (Bad Request)} if the subscriptionProduct has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/subscription-products")
    public ResponseEntity<SubscriptionProduct> createSubscriptionProduct(@Valid @RequestBody SubscriptionProduct subscriptionProduct) throws URISyntaxException {
        log.debug("REST request to save SubscriptionProduct : {}", subscriptionProduct);
        if (subscriptionProduct.getId() != null) {
            throw new BadRequestAlertException("A new subscriptionProduct cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SubscriptionProduct result = subscriptionProductService.save(subscriptionProduct);
        return ResponseEntity.created(new URI("/api/subscription-products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /subscription-products} : Updates an existing subscriptionProduct.
     *
     * @param subscriptionProduct the subscriptionProduct to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subscriptionProduct,
     * or with status {@code 400 (Bad Request)} if the subscriptionProduct is not valid,
     * or with status {@code 500 (Internal Server Error)} if the subscriptionProduct couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/subscription-products")
    public ResponseEntity<SubscriptionProduct> updateSubscriptionProduct(@Valid @RequestBody SubscriptionProduct subscriptionProduct) throws URISyntaxException {
        log.debug("REST request to update SubscriptionProduct : {}", subscriptionProduct);
        if (subscriptionProduct.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SubscriptionProduct result = subscriptionProductService.save(subscriptionProduct);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, subscriptionProduct.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /subscription-products} : get all the subscriptionProducts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subscriptionProducts in body.
     */
    @GetMapping("/subscription-products")
    public ResponseEntity<List<SubscriptionProduct>> getAllSubscriptionProducts(Pageable pageable) {
        log.debug("REST request to get a page of SubscriptionProducts");
        Page<SubscriptionProduct> page = subscriptionProductService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /subscription-products/:id} : get the "id" subscriptionProduct.
     *
     * @param id the id of the subscriptionProduct to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subscriptionProduct, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/subscription-products/{id}")
    public ResponseEntity<SubscriptionProduct> getSubscriptionProduct(@PathVariable String id) {
        log.debug("REST request to get SubscriptionProduct : {}", id);
        Optional<SubscriptionProduct> subscriptionProduct = subscriptionProductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(subscriptionProduct);
    }

    /**
     * {@code DELETE  /subscription-products/:id} : delete the "id" subscriptionProduct.
     *
     * @param id the id of the subscriptionProduct to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/subscription-products/{id}")
    public ResponseEntity<Void> deleteSubscriptionProduct(@PathVariable String id) {
        log.debug("REST request to delete SubscriptionProduct : {}", id);
        subscriptionProductService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
