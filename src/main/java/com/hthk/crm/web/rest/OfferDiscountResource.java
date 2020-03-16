package com.hthk.crm.web.rest;

import com.hthk.crm.domain.OfferDiscount;
import com.hthk.crm.service.OfferDiscountService;
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
 * REST controller for managing {@link com.hthk.crm.domain.OfferDiscount}.
 */
@RestController
@RequestMapping("/api")
public class OfferDiscountResource {

    private final Logger log = LoggerFactory.getLogger(OfferDiscountResource.class);

    private static final String ENTITY_NAME = "offerDiscount";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OfferDiscountService offerDiscountService;

    public OfferDiscountResource(OfferDiscountService offerDiscountService) {
        this.offerDiscountService = offerDiscountService;
    }

    /**
     * {@code POST  /offer-discounts} : Create a new offerDiscount.
     *
     * @param offerDiscount the offerDiscount to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new offerDiscount, or with status {@code 400 (Bad Request)} if the offerDiscount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/offer-discounts")
    public ResponseEntity<OfferDiscount> createOfferDiscount(@Valid @RequestBody OfferDiscount offerDiscount) throws URISyntaxException {
        log.debug("REST request to save OfferDiscount : {}", offerDiscount);
        if (offerDiscount.getId() != null) {
            throw new BadRequestAlertException("A new offerDiscount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OfferDiscount result = offerDiscountService.save(offerDiscount);
        return ResponseEntity.created(new URI("/api/offer-discounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /offer-discounts} : Updates an existing offerDiscount.
     *
     * @param offerDiscount the offerDiscount to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated offerDiscount,
     * or with status {@code 400 (Bad Request)} if the offerDiscount is not valid,
     * or with status {@code 500 (Internal Server Error)} if the offerDiscount couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/offer-discounts")
    public ResponseEntity<OfferDiscount> updateOfferDiscount(@Valid @RequestBody OfferDiscount offerDiscount) throws URISyntaxException {
        log.debug("REST request to update OfferDiscount : {}", offerDiscount);
        if (offerDiscount.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OfferDiscount result = offerDiscountService.save(offerDiscount);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, offerDiscount.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /offer-discounts} : get all the offerDiscounts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of offerDiscounts in body.
     */
    @GetMapping("/offer-discounts")
    public ResponseEntity<List<OfferDiscount>> getAllOfferDiscounts(Pageable pageable) {
        log.debug("REST request to get a page of OfferDiscounts");
        Page<OfferDiscount> page = offerDiscountService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /offer-discounts/:id} : get the "id" offerDiscount.
     *
     * @param id the id of the offerDiscount to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the offerDiscount, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/offer-discounts/{id}")
    public ResponseEntity<OfferDiscount> getOfferDiscount(@PathVariable String id) {
        log.debug("REST request to get OfferDiscount : {}", id);
        Optional<OfferDiscount> offerDiscount = offerDiscountService.findOne(id);
        return ResponseUtil.wrapOrNotFound(offerDiscount);
    }

    /**
     * {@code DELETE  /offer-discounts/:id} : delete the "id" offerDiscount.
     *
     * @param id the id of the offerDiscount to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/offer-discounts/{id}")
    public ResponseEntity<Void> deleteOfferDiscount(@PathVariable String id) {
        log.debug("REST request to delete OfferDiscount : {}", id);
        offerDiscountService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
