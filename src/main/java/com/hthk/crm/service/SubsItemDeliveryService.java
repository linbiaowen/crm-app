package com.hthk.crm.service;

import com.hthk.crm.domain.SubsItemDelivery;
import com.hthk.crm.repository.SubsItemDeliveryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SubsItemDelivery}.
 */
@Service
public class SubsItemDeliveryService {

    private final Logger log = LoggerFactory.getLogger(SubsItemDeliveryService.class);

    private final SubsItemDeliveryRepository subsItemDeliveryRepository;

    public SubsItemDeliveryService(SubsItemDeliveryRepository subsItemDeliveryRepository) {
        this.subsItemDeliveryRepository = subsItemDeliveryRepository;
    }

    /**
     * Save a subsItemDelivery.
     *
     * @param subsItemDelivery the entity to save.
     * @return the persisted entity.
     */
    public SubsItemDelivery save(SubsItemDelivery subsItemDelivery) {
        log.debug("Request to save SubsItemDelivery : {}", subsItemDelivery);
        return subsItemDeliveryRepository.save(subsItemDelivery);
    }

    /**
     * Get all the subsItemDeliveries.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<SubsItemDelivery> findAll(Pageable pageable) {
        log.debug("Request to get all SubsItemDeliveries");
        return subsItemDeliveryRepository.findAll(pageable);
    }

    /**
     * Get one subsItemDelivery by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<SubsItemDelivery> findOne(String id) {
        log.debug("Request to get SubsItemDelivery : {}", id);
        return subsItemDeliveryRepository.findById(id);
    }

    /**
     * Delete the subsItemDelivery by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete SubsItemDelivery : {}", id);
        subsItemDeliveryRepository.deleteById(id);
    }
}
