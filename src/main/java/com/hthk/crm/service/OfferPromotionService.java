package com.hthk.crm.service;

import com.hthk.crm.domain.OfferPromotion;
import com.hthk.crm.repository.OfferPromotionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OfferPromotion}.
 */
@Service
public class OfferPromotionService {

    private final Logger log = LoggerFactory.getLogger(OfferPromotionService.class);

    private final OfferPromotionRepository offerPromotionRepository;

    public OfferPromotionService(OfferPromotionRepository offerPromotionRepository) {
        this.offerPromotionRepository = offerPromotionRepository;
    }

    /**
     * Save a offerPromotion.
     *
     * @param offerPromotion the entity to save.
     * @return the persisted entity.
     */
    public OfferPromotion save(OfferPromotion offerPromotion) {
        log.debug("Request to save OfferPromotion : {}", offerPromotion);
        return offerPromotionRepository.save(offerPromotion);
    }

    /**
     * Get all the offerPromotions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<OfferPromotion> findAll(Pageable pageable) {
        log.debug("Request to get all OfferPromotions");
        return offerPromotionRepository.findAll(pageable);
    }

    /**
     * Get one offerPromotion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<OfferPromotion> findOne(String id) {
        log.debug("Request to get OfferPromotion : {}", id);
        return offerPromotionRepository.findById(id);
    }

    /**
     * Delete the offerPromotion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete OfferPromotion : {}", id);
        offerPromotionRepository.deleteById(id);
    }
}
