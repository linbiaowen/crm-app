package com.hthk.crm.web.rest;

import com.hthk.crm.domain.ProductSpecification;
import com.hthk.crm.service.ProductSpecificationService;
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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.hthk.crm.domain.ProductSpecification}.
 */
@RestController
@RequestMapping("/api")
public class ProductSpecificationResource {

    private final Logger log = LoggerFactory.getLogger(ProductSpecificationResource.class);

    private static final String ENTITY_NAME = "productSpecification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductSpecificationService productSpecificationService;

    public ProductSpecificationResource(ProductSpecificationService productSpecificationService) {
        this.productSpecificationService = productSpecificationService;
    }

    /**
     * {@code POST  /product-specifications} : Create a new productSpecification.
     *
     * @param productSpecification the productSpecification to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productSpecification, or with status {@code 400 (Bad Request)} if the productSpecification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-specifications")
    public ResponseEntity<ProductSpecification> createProductSpecification(@Valid @RequestBody ProductSpecification productSpecification) throws URISyntaxException {
        log.debug("REST request to save ProductSpecification : {}", productSpecification);
        if (productSpecification.getId() != null) {
            throw new BadRequestAlertException("A new productSpecification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductSpecification result = productSpecificationService.save(productSpecification);
        return ResponseEntity.created(new URI("/api/product-specifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-specifications} : Updates an existing productSpecification.
     *
     * @param productSpecification the productSpecification to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productSpecification,
     * or with status {@code 400 (Bad Request)} if the productSpecification is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productSpecification couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-specifications")
    public ResponseEntity<ProductSpecification> updateProductSpecification(@Valid @RequestBody ProductSpecification productSpecification) throws URISyntaxException {
        log.debug("REST request to update ProductSpecification : {}", productSpecification);
        if (productSpecification.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductSpecification result = productSpecificationService.save(productSpecification);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productSpecification.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /product-specifications} : get all the productSpecifications.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productSpecifications in body.
     */
    @GetMapping("/product-specifications")
    public ResponseEntity<List<ProductSpecification>> getAllProductSpecifications(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("product-is-null".equals(filter)) {
            log.debug("REST request to get all ProductSpecifications where product is null");
            return new ResponseEntity<>(productSpecificationService.findAllWhereProductIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of ProductSpecifications");
        Page<ProductSpecification> page = productSpecificationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /product-specifications/:id} : get the "id" productSpecification.
     *
     * @param id the id of the productSpecification to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productSpecification, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-specifications/{id}")
    public ResponseEntity<ProductSpecification> getProductSpecification(@PathVariable String id) {
        log.debug("REST request to get ProductSpecification : {}", id);
        Optional<ProductSpecification> productSpecification = productSpecificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productSpecification);
    }

    /**
     * {@code DELETE  /product-specifications/:id} : delete the "id" productSpecification.
     *
     * @param id the id of the productSpecification to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-specifications/{id}")
    public ResponseEntity<Void> deleteProductSpecification(@PathVariable String id) {
        log.debug("REST request to delete ProductSpecification : {}", id);
        productSpecificationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
