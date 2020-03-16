package com.hthk.crm.service;

import com.hthk.crm.domain.SubscriptionProduct;
import com.hthk.crm.repository.SubscriptionProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SubscriptionProduct}.
 */
@Service
public class SubscriptionProductService {

    private final Logger log = LoggerFactory.getLogger(SubscriptionProductService.class);

    private final SubscriptionProductRepository subscriptionProductRepository;

    public SubscriptionProductService(SubscriptionProductRepository subscriptionProductRepository) {
        this.subscriptionProductRepository = subscriptionProductRepository;
    }

    /**
     * Save a subscriptionProduct.
     *
     * @param subscriptionProduct the entity to save.
     * @return the persisted entity.
     */
    public SubscriptionProduct save(SubscriptionProduct subscriptionProduct) {
        log.debug("Request to save SubscriptionProduct : {}", subscriptionProduct);
        return subscriptionProductRepository.save(subscriptionProduct);
    }

    /**
     * Get all the subscriptionProducts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<SubscriptionProduct> findAll(Pageable pageable) {
        log.debug("Request to get all SubscriptionProducts");
        return subscriptionProductRepository.findAll(pageable);
    }

    /**
     * Get one subscriptionProduct by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<SubscriptionProduct> findOne(String id) {
        log.debug("Request to get SubscriptionProduct : {}", id);
        return subscriptionProductRepository.findById(id);
    }

    /**
     * Delete the subscriptionProduct by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete SubscriptionProduct : {}", id);
        subscriptionProductRepository.deleteById(id);
    }
}
