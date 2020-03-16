package com.hthk.crm.service;

import com.hthk.crm.domain.SubscriptionDetails;
import com.hthk.crm.repository.SubscriptionDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SubscriptionDetails}.
 */
@Service
public class SubscriptionDetailsService {

    private final Logger log = LoggerFactory.getLogger(SubscriptionDetailsService.class);

    private final SubscriptionDetailsRepository subscriptionDetailsRepository;

    public SubscriptionDetailsService(SubscriptionDetailsRepository subscriptionDetailsRepository) {
        this.subscriptionDetailsRepository = subscriptionDetailsRepository;
    }

    /**
     * Save a subscriptionDetails.
     *
     * @param subscriptionDetails the entity to save.
     * @return the persisted entity.
     */
    public SubscriptionDetails save(SubscriptionDetails subscriptionDetails) {
        log.debug("Request to save SubscriptionDetails : {}", subscriptionDetails);
        return subscriptionDetailsRepository.save(subscriptionDetails);
    }

    /**
     * Get all the subscriptionDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<SubscriptionDetails> findAll(Pageable pageable) {
        log.debug("Request to get all SubscriptionDetails");
        return subscriptionDetailsRepository.findAll(pageable);
    }

    /**
     * Get one subscriptionDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<SubscriptionDetails> findOne(String id) {
        log.debug("Request to get SubscriptionDetails : {}", id);
        return subscriptionDetailsRepository.findById(id);
    }

    /**
     * Delete the subscriptionDetails by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete SubscriptionDetails : {}", id);
        subscriptionDetailsRepository.deleteById(id);
    }
}
