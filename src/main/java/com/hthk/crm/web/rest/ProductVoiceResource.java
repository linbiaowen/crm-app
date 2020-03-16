package com.hthk.crm.web.rest;

import com.hthk.crm.domain.ProductVoice;
import com.hthk.crm.service.ProductVoiceService;
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
 * REST controller for managing {@link com.hthk.crm.domain.ProductVoice}.
 */
@RestController
@RequestMapping("/api")
public class ProductVoiceResource {

    private final Logger log = LoggerFactory.getLogger(ProductVoiceResource.class);

    private static final String ENTITY_NAME = "productVoice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductVoiceService productVoiceService;

    public ProductVoiceResource(ProductVoiceService productVoiceService) {
        this.productVoiceService = productVoiceService;
    }

    /**
     * {@code POST  /product-voices} : Create a new productVoice.
     *
     * @param productVoice the productVoice to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productVoice, or with status {@code 400 (Bad Request)} if the productVoice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-voices")
    public ResponseEntity<ProductVoice> createProductVoice(@Valid @RequestBody ProductVoice productVoice) throws URISyntaxException {
        log.debug("REST request to save ProductVoice : {}", productVoice);
        if (productVoice.getId() != null) {
            throw new BadRequestAlertException("A new productVoice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductVoice result = productVoiceService.save(productVoice);
        return ResponseEntity.created(new URI("/api/product-voices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-voices} : Updates an existing productVoice.
     *
     * @param productVoice the productVoice to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productVoice,
     * or with status {@code 400 (Bad Request)} if the productVoice is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productVoice couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-voices")
    public ResponseEntity<ProductVoice> updateProductVoice(@Valid @RequestBody ProductVoice productVoice) throws URISyntaxException {
        log.debug("REST request to update ProductVoice : {}", productVoice);
        if (productVoice.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductVoice result = productVoiceService.save(productVoice);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productVoice.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /product-voices} : get all the productVoices.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productVoices in body.
     */
    @GetMapping("/product-voices")
    public ResponseEntity<List<ProductVoice>> getAllProductVoices(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("productspec-is-null".equals(filter)) {
            log.debug("REST request to get all ProductVoices where productSpec is null");
            return new ResponseEntity<>(productVoiceService.findAllWhereProductSpecIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of ProductVoices");
        Page<ProductVoice> page = productVoiceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /product-voices/:id} : get the "id" productVoice.
     *
     * @param id the id of the productVoice to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productVoice, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-voices/{id}")
    public ResponseEntity<ProductVoice> getProductVoice(@PathVariable String id) {
        log.debug("REST request to get ProductVoice : {}", id);
        Optional<ProductVoice> productVoice = productVoiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productVoice);
    }

    /**
     * {@code DELETE  /product-voices/:id} : delete the "id" productVoice.
     *
     * @param id the id of the productVoice to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-voices/{id}")
    public ResponseEntity<Void> deleteProductVoice(@PathVariable String id) {
        log.debug("REST request to delete ProductVoice : {}", id);
        productVoiceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
