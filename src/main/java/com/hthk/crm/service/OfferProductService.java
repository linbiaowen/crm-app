package com.hthk.crm.service;

import com.hthk.crm.domain.OfferProduct;
import com.hthk.crm.repository.OfferProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OfferProduct}.
 */
@Service
public class OfferProductService {

    private final Logger log = LoggerFactory.getLogger(OfferProductService.class);

    private final OfferProductRepository offerProductRepository;

    public OfferProductService(OfferProductRepository offerProductRepository) {
        this.offerProductRepository = offerProductRepository;
    }

    /**
     * Save a offerProduct.
     *
     * @param offerProduct the entity to save.
     * @return the persisted entity.
     */
    public OfferProduct save(OfferProduct offerProduct) {
        log.debug("Request to save OfferProduct : {}", offerProduct);
        return offerProductRepository.save(offerProduct);
    }

    /**
     * Get all the offerProducts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<OfferProduct> findAll(Pageable pageable) {
        log.debug("Request to get all OfferProducts");
        return offerProductRepository.findAll(pageable);
    }

    /**
     * Get one offerProduct by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<OfferProduct> findOne(String id) {
        log.debug("Request to get OfferProduct : {}", id);
        return offerProductRepository.findById(id);
    }

    /**
     * Delete the offerProduct by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete OfferProduct : {}", id);
        offerProductRepository.deleteById(id);
    }
}
