package com.hthk.crm.web.rest;

import com.hthk.crm.domain.OfferProduct;
import com.hthk.crm.service.OfferProductService;
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
 * REST controller for managing {@link com.hthk.crm.domain.OfferProduct}.
 */
@RestController
@RequestMapping("/api")
public class OfferProductResource {

    private final Logger log = LoggerFactory.getLogger(OfferProductResource.class);

    private static final String ENTITY_NAME = "offerProduct";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OfferProductService offerProductService;

    public OfferProductResource(OfferProductService offerProductService) {
        this.offerProductService = offerProductService;
    }

    /**
     * {@code POST  /offer-products} : Create a new offerProduct.
     *
     * @param offerProduct the offerProduct to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new offerProduct, or with status {@code 400 (Bad Request)} if the offerProduct has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/offer-products")
    public ResponseEntity<OfferProduct> createOfferProduct(@Valid @RequestBody OfferProduct offerProduct) throws URISyntaxException {
        log.debug("REST request to save OfferProduct : {}", offerProduct);
        if (offerProduct.getId() != null) {
            throw new BadRequestAlertException("A new offerProduct cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OfferProduct result = offerProductService.save(offerProduct);
        return ResponseEntity.created(new URI("/api/offer-products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /offer-products} : Updates an existing offerProduct.
     *
     * @param offerProduct the offerProduct to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated offerProduct,
     * or with status {@code 400 (Bad Request)} if the offerProduct is not valid,
     * or with status {@code 500 (Internal Server Error)} if the offerProduct couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/offer-products")
    public ResponseEntity<OfferProduct> updateOfferProduct(@Valid @RequestBody OfferProduct offerProduct) throws URISyntaxException {
        log.debug("REST request to update OfferProduct : {}", offerProduct);
        if (offerProduct.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OfferProduct result = offerProductService.save(offerProduct);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, offerProduct.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /offer-products} : get all the offerProducts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of offerProducts in body.
     */
    @GetMapping("/offer-products")
    public ResponseEntity<List<OfferProduct>> getAllOfferProducts(Pageable pageable) {
        log.debug("REST request to get a page of OfferProducts");
        Page<OfferProduct> page = offerProductService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /offer-products/:id} : get the "id" offerProduct.
     *
     * @param id the id of the offerProduct to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the offerProduct, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/offer-products/{id}")
    public ResponseEntity<OfferProduct> getOfferProduct(@PathVariable String id) {
        log.debug("REST request to get OfferProduct : {}", id);
        Optional<OfferProduct> offerProduct = offerProductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(offerProduct);
    }

    /**
     * {@code DELETE  /offer-products/:id} : delete the "id" offerProduct.
     *
     * @param id the id of the offerProduct to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/offer-products/{id}")
    public ResponseEntity<Void> deleteOfferProduct(@PathVariable String id) {
        log.debug("REST request to delete OfferProduct : {}", id);
        offerProductService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
