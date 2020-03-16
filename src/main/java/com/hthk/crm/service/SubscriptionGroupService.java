package com.hthk.crm.service;

import com.hthk.crm.domain.SubscriptionGroup;
import com.hthk.crm.repository.SubscriptionGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SubscriptionGroup}.
 */
@Service
public class SubscriptionGroupService {

    private final Logger log = LoggerFactory.getLogger(SubscriptionGroupService.class);

    private final SubscriptionGroupRepository subscriptionGroupRepository;

    public SubscriptionGroupService(SubscriptionGroupRepository subscriptionGroupRepository) {
        this.subscriptionGroupRepository = subscriptionGroupRepository;
    }

    /**
     * Save a subscriptionGroup.
     *
     * @param subscriptionGroup the entity to save.
     * @return the persisted entity.
     */
    public SubscriptionGroup save(SubscriptionGroup subscriptionGroup) {
        log.debug("Request to save SubscriptionGroup : {}", subscriptionGroup);
        return subscriptionGroupRepository.save(subscriptionGroup);
    }

    /**
     * Get all the subscriptionGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<SubscriptionGroup> findAll(Pageable pageable) {
        log.debug("Request to get all SubscriptionGroups");
        return subscriptionGroupRepository.findAll(pageable);
    }

    /**
     * Get one subscriptionGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<SubscriptionGroup> findOne(String id) {
        log.debug("Request to get SubscriptionGroup : {}", id);
        return subscriptionGroupRepository.findById(id);
    }

    /**
     * Delete the subscriptionGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete SubscriptionGroup : {}", id);
        subscriptionGroupRepository.deleteById(id);
    }
}
