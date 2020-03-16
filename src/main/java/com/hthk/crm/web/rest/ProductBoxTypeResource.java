package com.hthk.crm.web.rest;

import com.hthk.crm.domain.ProductBoxType;
import com.hthk.crm.service.ProductBoxTypeService;
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
 * REST controller for managing {@link com.hthk.crm.domain.ProductBoxType}.
 */
@RestController
@RequestMapping("/api")
public class ProductBoxTypeResource {

    private final Logger log = LoggerFactory.getLogger(ProductBoxTypeResource.class);

    private static final String ENTITY_NAME = "productBoxType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductBoxTypeService productBoxTypeService;

    public ProductBoxTypeResource(ProductBoxTypeService productBoxTypeService) {
        this.productBoxTypeService = productBoxTypeService;
    }

    /**
     * {@code POST  /product-box-types} : Create a new productBoxType.
     *
     * @param productBoxType the productBoxType to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productBoxType, or with status {@code 400 (Bad Request)} if the productBoxType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-box-types")
    public ResponseEntity<ProductBoxType> createProductBoxType(@Valid @RequestBody ProductBoxType productBoxType) throws URISyntaxException {
        log.debug("REST request to save ProductBoxType : {}", productBoxType);
        if (productBoxType.getId() != null) {
            throw new BadRequestAlertException("A new productBoxType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductBoxType result = productBoxTypeService.save(productBoxType);
        return ResponseEntity.created(new URI("/api/product-box-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-box-types} : Updates an existing productBoxType.
     *
     * @param productBoxType the productBoxType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productBoxType,
     * or with status {@code 400 (Bad Request)} if the productBoxType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productBoxType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-box-types")
    public ResponseEntity<ProductBoxType> updateProductBoxType(@Valid @RequestBody ProductBoxType productBoxType) throws URISyntaxException {
        log.debug("REST request to update ProductBoxType : {}", productBoxType);
        if (productBoxType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductBoxType result = productBoxTypeService.save(productBoxType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productBoxType.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /product-box-types} : get all the productBoxTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productBoxTypes in body.
     */
    @GetMapping("/product-box-types")
    public ResponseEntity<List<ProductBoxType>> getAllProductBoxTypes(Pageable pageable) {
        log.debug("REST request to get a page of ProductBoxTypes");
        Page<ProductBoxType> page = productBoxTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /product-box-types/:id} : get the "id" productBoxType.
     *
     * @param id the id of the productBoxType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productBoxType, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-box-types/{id}")
    public ResponseEntity<ProductBoxType> getProductBoxType(@PathVariable String id) {
        log.debug("REST request to get ProductBoxType : {}", id);
        Optional<ProductBoxType> productBoxType = productBoxTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productBoxType);
    }

    /**
     * {@code DELETE  /product-box-types/:id} : delete the "id" productBoxType.
     *
     * @param id the id of the productBoxType to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-box-types/{id}")
    public ResponseEntity<Void> deleteProductBoxType(@PathVariable String id) {
        log.debug("REST request to delete ProductBoxType : {}", id);
        productBoxTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
