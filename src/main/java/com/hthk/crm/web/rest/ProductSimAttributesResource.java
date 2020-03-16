package com.hthk.crm.web.rest;

import com.hthk.crm.domain.ProductSimAttributes;
import com.hthk.crm.service.ProductSimAttributesService;
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
 * REST controller for managing {@link com.hthk.crm.domain.ProductSimAttributes}.
 */
@RestController
@RequestMapping("/api")
public class ProductSimAttributesResource {

    private final Logger log = LoggerFactory.getLogger(ProductSimAttributesResource.class);

    private static final String ENTITY_NAME = "productSimAttributes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductSimAttributesService productSimAttributesService;

    public ProductSimAttributesResource(ProductSimAttributesService productSimAttributesService) {
        this.productSimAttributesService = productSimAttributesService;
    }

    /**
     * {@code POST  /product-sim-attributes} : Create a new productSimAttributes.
     *
     * @param productSimAttributes the productSimAttributes to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productSimAttributes, or with status {@code 400 (Bad Request)} if the productSimAttributes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-sim-attributes")
    public ResponseEntity<ProductSimAttributes> createProductSimAttributes(@Valid @RequestBody ProductSimAttributes productSimAttributes) throws URISyntaxException {
        log.debug("REST request to save ProductSimAttributes : {}", productSimAttributes);
        if (productSimAttributes.getId() != null) {
            throw new BadRequestAlertException("A new productSimAttributes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductSimAttributes result = productSimAttributesService.save(productSimAttributes);
        return ResponseEntity.created(new URI("/api/product-sim-attributes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-sim-attributes} : Updates an existing productSimAttributes.
     *
     * @param productSimAttributes the productSimAttributes to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productSimAttributes,
     * or with status {@code 400 (Bad Request)} if the productSimAttributes is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productSimAttributes couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-sim-attributes")
    public ResponseEntity<ProductSimAttributes> updateProductSimAttributes(@Valid @RequestBody ProductSimAttributes productSimAttributes) throws URISyntaxException {
        log.debug("REST request to update ProductSimAttributes : {}", productSimAttributes);
        if (productSimAttributes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductSimAttributes result = productSimAttributesService.save(productSimAttributes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productSimAttributes.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /product-sim-attributes} : get all the productSimAttributes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productSimAttributes in body.
     */
    @GetMapping("/product-sim-attributes")
    public ResponseEntity<List<ProductSimAttributes>> getAllProductSimAttributes(Pageable pageable) {
        log.debug("REST request to get a page of ProductSimAttributes");
        Page<ProductSimAttributes> page = productSimAttributesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /product-sim-attributes/:id} : get the "id" productSimAttributes.
     *
     * @param id the id of the productSimAttributes to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productSimAttributes, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-sim-attributes/{id}")
    public ResponseEntity<ProductSimAttributes> getProductSimAttributes(@PathVariable String id) {
        log.debug("REST request to get ProductSimAttributes : {}", id);
        Optional<ProductSimAttributes> productSimAttributes = productSimAttributesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productSimAttributes);
    }

    /**
     * {@code DELETE  /product-sim-attributes/:id} : delete the "id" productSimAttributes.
     *
     * @param id the id of the productSimAttributes to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-sim-attributes/{id}")
    public ResponseEntity<Void> deleteProductSimAttributes(@PathVariable String id) {
        log.debug("REST request to delete ProductSimAttributes : {}", id);
        productSimAttributesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
