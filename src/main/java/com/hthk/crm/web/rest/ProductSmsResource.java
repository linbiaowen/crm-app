package com.hthk.crm.web.rest;

import com.hthk.crm.domain.ProductSms;
import com.hthk.crm.service.ProductSmsService;
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
 * REST controller for managing {@link com.hthk.crm.domain.ProductSms}.
 */
@RestController
@RequestMapping("/api")
public class ProductSmsResource {

    private final Logger log = LoggerFactory.getLogger(ProductSmsResource.class);

    private static final String ENTITY_NAME = "productSms";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductSmsService productSmsService;

    public ProductSmsResource(ProductSmsService productSmsService) {
        this.productSmsService = productSmsService;
    }

    /**
     * {@code POST  /product-sms} : Create a new productSms.
     *
     * @param productSms the productSms to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productSms, or with status {@code 400 (Bad Request)} if the productSms has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-sms")
    public ResponseEntity<ProductSms> createProductSms(@Valid @RequestBody ProductSms productSms) throws URISyntaxException {
        log.debug("REST request to save ProductSms : {}", productSms);
        if (productSms.getId() != null) {
            throw new BadRequestAlertException("A new productSms cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductSms result = productSmsService.save(productSms);
        return ResponseEntity.created(new URI("/api/product-sms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-sms} : Updates an existing productSms.
     *
     * @param productSms the productSms to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productSms,
     * or with status {@code 400 (Bad Request)} if the productSms is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productSms couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-sms")
    public ResponseEntity<ProductSms> updateProductSms(@Valid @RequestBody ProductSms productSms) throws URISyntaxException {
        log.debug("REST request to update ProductSms : {}", productSms);
        if (productSms.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductSms result = productSmsService.save(productSms);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productSms.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /product-sms} : get all the productSms.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productSms in body.
     */
    @GetMapping("/product-sms")
    public ResponseEntity<List<ProductSms>> getAllProductSms(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("productspec-is-null".equals(filter)) {
            log.debug("REST request to get all ProductSmss where productSpec is null");
            return new ResponseEntity<>(productSmsService.findAllWhereProductSpecIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of ProductSms");
        Page<ProductSms> page = productSmsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /product-sms/:id} : get the "id" productSms.
     *
     * @param id the id of the productSms to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productSms, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-sms/{id}")
    public ResponseEntity<ProductSms> getProductSms(@PathVariable String id) {
        log.debug("REST request to get ProductSms : {}", id);
        Optional<ProductSms> productSms = productSmsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productSms);
    }

    /**
     * {@code DELETE  /product-sms/:id} : delete the "id" productSms.
     *
     * @param id the id of the productSms to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-sms/{id}")
    public ResponseEntity<Void> deleteProductSms(@PathVariable String id) {
        log.debug("REST request to delete ProductSms : {}", id);
        productSmsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
