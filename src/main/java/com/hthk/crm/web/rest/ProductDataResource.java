package com.hthk.crm.web.rest;

import com.hthk.crm.domain.ProductData;
import com.hthk.crm.service.ProductDataService;
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
 * REST controller for managing {@link com.hthk.crm.domain.ProductData}.
 */
@RestController
@RequestMapping("/api")
public class ProductDataResource {

    private final Logger log = LoggerFactory.getLogger(ProductDataResource.class);

    private static final String ENTITY_NAME = "productData";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductDataService productDataService;

    public ProductDataResource(ProductDataService productDataService) {
        this.productDataService = productDataService;
    }

    /**
     * {@code POST  /product-data} : Create a new productData.
     *
     * @param productData the productData to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productData, or with status {@code 400 (Bad Request)} if the productData has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-data")
    public ResponseEntity<ProductData> createProductData(@Valid @RequestBody ProductData productData) throws URISyntaxException {
        log.debug("REST request to save ProductData : {}", productData);
        if (productData.getId() != null) {
            throw new BadRequestAlertException("A new productData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductData result = productDataService.save(productData);
        return ResponseEntity.created(new URI("/api/product-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-data} : Updates an existing productData.
     *
     * @param productData the productData to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productData,
     * or with status {@code 400 (Bad Request)} if the productData is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productData couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-data")
    public ResponseEntity<ProductData> updateProductData(@Valid @RequestBody ProductData productData) throws URISyntaxException {
        log.debug("REST request to update ProductData : {}", productData);
        if (productData.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductData result = productDataService.save(productData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productData.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /product-data} : get all the productData.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productData in body.
     */
    @GetMapping("/product-data")
    public ResponseEntity<List<ProductData>> getAllProductData(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("productspec-is-null".equals(filter)) {
            log.debug("REST request to get all ProductDatas where productSpec is null");
            return new ResponseEntity<>(productDataService.findAllWhereProductSpecIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of ProductData");
        Page<ProductData> page = productDataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /product-data/:id} : get the "id" productData.
     *
     * @param id the id of the productData to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productData, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-data/{id}")
    public ResponseEntity<ProductData> getProductData(@PathVariable String id) {
        log.debug("REST request to get ProductData : {}", id);
        Optional<ProductData> productData = productDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productData);
    }

    /**
     * {@code DELETE  /product-data/:id} : delete the "id" productData.
     *
     * @param id the id of the productData to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-data/{id}")
    public ResponseEntity<Void> deleteProductData(@PathVariable String id) {
        log.debug("REST request to delete ProductData : {}", id);
        productDataService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
