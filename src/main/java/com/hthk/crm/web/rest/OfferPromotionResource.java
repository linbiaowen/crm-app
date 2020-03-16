package com.hthk.crm.web.rest;

import com.hthk.crm.domain.OfferPromotion;
import com.hthk.crm.service.OfferPromotionService;
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
 * REST controller for managing {@link com.hthk.crm.domain.OfferPromotion}.
 */
@RestController
@RequestMapping("/api")
public class OfferPromotionResource {

    private final Logger log = LoggerFactory.getLogger(OfferPromotionResource.class);

    private static final String ENTITY_NAME = "offerPromotion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OfferPromotionService offerPromotionService;

    public OfferPromotionResource(OfferPromotionService offerPromotionService) {
        this.offerPromotionService = offerPromotionService;
    }

    /**
     * {@code POST  /offer-promotions} : Create a new offerPromotion.
     *
     * @param offerPromotion the offerPromotion to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new offerPromotion, or with status {@code 400 (Bad Request)} if the offerPromotion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/offer-promotions")
    public ResponseEntity<OfferPromotion> createOfferPromotion(@Valid @RequestBody OfferPromotion offerPromotion) throws URISyntaxException {
        log.debug("REST request to save OfferPromotion : {}", offerPromotion);
        if (offerPromotion.getId() != null) {
            throw new BadRequestAlertException("A new offerPromotion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OfferPromotion result = offerPromotionService.save(offerPromotion);
        return ResponseEntity.created(new URI("/api/offer-promotions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /offer-promotions} : Updates an existing offerPromotion.
     *
     * @param offerPromotion the offerPromotion to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated offerPromotion,
     * or with status {@code 400 (Bad Request)} if the offerPromotion is not valid,
     * or with status {@code 500 (Internal Server Error)} if the offerPromotion couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/offer-promotions")
    public ResponseEntity<OfferPromotion> updateOfferPromotion(@Valid @RequestBody OfferPromotion offerPromotion) throws URISyntaxException {
        log.debug("REST request to update OfferPromotion : {}", offerPromotion);
        if (offerPromotion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OfferPromotion result = offerPromotionService.save(offerPromotion);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, offerPromotion.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /offer-promotions} : get all the offerPromotions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of offerPromotions in body.
     */
    @GetMapping("/offer-promotions")
    public ResponseEntity<List<OfferPromotion>> getAllOfferPromotions(Pageable pageable) {
        log.debug("REST request to get a page of OfferPromotions");
        Page<OfferPromotion> page = offerPromotionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /offer-promotions/:id} : get the "id" offerPromotion.
     *
     * @param id the id of the offerPromotion to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the offerPromotion, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/offer-promotions/{id}")
    public ResponseEntity<OfferPromotion> getOfferPromotion(@PathVariable String id) {
        log.debug("REST request to get OfferPromotion : {}", id);
        Optional<OfferPromotion> offerPromotion = offerPromotionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(offerPromotion);
    }

    /**
     * {@code DELETE  /offer-promotions/:id} : delete the "id" offerPromotion.
     *
     * @param id the id of the offerPromotion to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/offer-promotions/{id}")
    public ResponseEntity<Void> deleteOfferPromotion(@PathVariable String id) {
        log.debug("REST request to delete OfferPromotion : {}", id);
        offerPromotionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
