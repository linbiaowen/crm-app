package com.hthk.crm.service;

import com.hthk.crm.domain.OfferDiscount;
import com.hthk.crm.repository.OfferDiscountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OfferDiscount}.
 */
@Service
public class OfferDiscountService {

    private final Logger log = LoggerFactory.getLogger(OfferDiscountService.class);

    private final OfferDiscountRepository offerDiscountRepository;

    public OfferDiscountService(OfferDiscountRepository offerDiscountRepository) {
        this.offerDiscountRepository = offerDiscountRepository;
    }

    /**
     * Save a offerDiscount.
     *
     * @param offerDiscount the entity to save.
     * @return the persisted entity.
     */
    public OfferDiscount save(OfferDiscount offerDiscount) {
        log.debug("Request to save OfferDiscount : {}", offerDiscount);
        return offerDiscountRepository.save(offerDiscount);
    }

    /**
     * Get all the offerDiscounts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<OfferDiscount> findAll(Pageable pageable) {
        log.debug("Request to get all OfferDiscounts");
        return offerDiscountRepository.findAll(pageable);
    }

    /**
     * Get one offerDiscount by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<OfferDiscount> findOne(String id) {
        log.debug("Request to get OfferDiscount : {}", id);
        return offerDiscountRepository.findById(id);
    }

    /**
     * Delete the offerDiscount by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete OfferDiscount : {}", id);
        offerDiscountRepository.deleteById(id);
    }
}
