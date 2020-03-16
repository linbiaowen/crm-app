package com.hthk.crm.web.rest;

import com.hthk.crm.domain.ProductAttributes;
import com.hthk.crm.service.ProductAttributesService;
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
 * REST controller for managing {@link com.hthk.crm.domain.ProductAttributes}.
 */
@RestController
@RequestMapping("/api")
public class ProductAttributesResource {

    private final Logger log = LoggerFactory.getLogger(ProductAttributesResource.class);

    private static final String ENTITY_NAME = "productAttributes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductAttributesService productAttributesService;

    public ProductAttributesResource(ProductAttributesService productAttributesService) {
        this.productAttributesService = productAttributesService;
    }

    /**
     * {@code POST  /product-attributes} : Create a new productAttributes.
     *
     * @param productAttributes the productAttributes to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productAttributes, or with status {@code 400 (Bad Request)} if the productAttributes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-attributes")
    public ResponseEntity<ProductAttributes> createProductAttributes(@Valid @RequestBody ProductAttributes productAttributes) throws URISyntaxException {
        log.debug("REST request to save ProductAttributes : {}", productAttributes);
        if (productAttributes.getId() != null) {
            throw new BadRequestAlertException("A new productAttributes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductAttributes result = productAttributesService.save(productAttributes);
        return ResponseEntity.created(new URI("/api/product-attributes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-attributes} : Updates an existing productAttributes.
     *
     * @param productAttributes the productAttributes to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productAttributes,
     * or with status {@code 400 (Bad Request)} if the productAttributes is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productAttributes couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-attributes")
    public ResponseEntity<ProductAttributes> updateProductAttributes(@Valid @RequestBody ProductAttributes productAttributes) throws URISyntaxException {
        log.debug("REST request to update ProductAttributes : {}", productAttributes);
        if (productAttributes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductAttributes result = productAttributesService.save(productAttributes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productAttributes.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /product-attributes} : get all the productAttributes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productAttributes in body.
     */
    @GetMapping("/product-attributes")
    public ResponseEntity<List<ProductAttributes>> getAllProductAttributes(Pageable pageable) {
        log.debug("REST request to get a page of ProductAttributes");
        Page<ProductAttributes> page = productAttributesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /product-attributes/:id} : get the "id" productAttributes.
     *
     * @param id the id of the productAttributes to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productAttributes, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-attributes/{id}")
    public ResponseEntity<ProductAttributes> getProductAttributes(@PathVariable String id) {
        log.debug("REST request to get ProductAttributes : {}", id);
        Optional<ProductAttributes> productAttributes = productAttributesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productAttributes);
    }

    /**
     * {@code DELETE  /product-attributes/:id} : delete the "id" productAttributes.
     *
     * @param id the id of the productAttributes to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-attributes/{id}")
    public ResponseEntity<Void> deleteProductAttributes(@PathVariable String id) {
        log.debug("REST request to delete ProductAttributes : {}", id);
        productAttributesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
