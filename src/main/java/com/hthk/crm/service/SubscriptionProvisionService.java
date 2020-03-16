package com.hthk.crm.service;

import com.hthk.crm.domain.SubscriptionProvision;
import com.hthk.crm.repository.SubscriptionProvisionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SubscriptionProvision}.
 */
@Service
public class SubscriptionProvisionService {

    private final Logger log = LoggerFactory.getLogger(SubscriptionProvisionService.class);

    private final SubscriptionProvisionRepository subscriptionProvisionRepository;

    public SubscriptionProvisionService(SubscriptionProvisionRepository subscriptionProvisionRepository) {
        this.subscriptionProvisionRepository = subscriptionProvisionRepository;
    }

    /**
     * Save a subscriptionProvision.
     *
     * @param subscriptionProvision the entity to save.
     * @return the persisted entity.
     */
    public SubscriptionProvision save(SubscriptionProvision subscriptionProvision) {
        log.debug("Request to save SubscriptionProvision : {}", subscriptionProvision);
        return subscriptionProvisionRepository.save(subscriptionProvision);
    }

    /**
     * Get all the subscriptionProvisions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<SubscriptionProvision> findAll(Pageable pageable) {
        log.debug("Request to get all SubscriptionProvisions");
        return subscriptionProvisionRepository.findAll(pageable);
    }

    /**
     * Get one subscriptionProvision by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<SubscriptionProvision> findOne(String id) {
        log.debug("Request to get SubscriptionProvision : {}", id);
        return subscriptionProvisionRepository.findById(id);
    }

    /**
     * Delete the subscriptionProvision by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete SubscriptionProvision : {}", id);
        subscriptionProvisionRepository.deleteById(id);
    }
}
