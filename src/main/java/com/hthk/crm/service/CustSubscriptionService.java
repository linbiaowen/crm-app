package com.hthk.crm.service;

import com.hthk.crm.domain.CustSubscription;
import com.hthk.crm.repository.CustSubscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CustSubscription}.
 */
@Service
public class CustSubscriptionService {

    private final Logger log = LoggerFactory.getLogger(CustSubscriptionService.class);

    private final CustSubscriptionRepository custSubscriptionRepository;

    public CustSubscriptionService(CustSubscriptionRepository custSubscriptionRepository) {
        this.custSubscriptionRepository = custSubscriptionRepository;
    }

    /**
     * Save a custSubscription.
     *
     * @param custSubscription the entity to save.
     * @return the persisted entity.
     */
    public CustSubscription save(CustSubscription custSubscription) {
        log.debug("Request to save CustSubscription : {}", custSubscription);
        return custSubscriptionRepository.save(custSubscription);
    }

    /**
     * Get all the custSubscriptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<CustSubscription> findAll(Pageable pageable) {
        log.debug("Request to get all CustSubscriptions");
        return custSubscriptionRepository.findAll(pageable);
    }

    /**
     * Get one custSubscription by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<CustSubscription> findOne(String id) {
        log.debug("Request to get CustSubscription : {}", id);
        return custSubscriptionRepository.findById(id);
    }

    /**
     * Delete the custSubscription by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete CustSubscription : {}", id);
        custSubscriptionRepository.deleteById(id);
    }
}
