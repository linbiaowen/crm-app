package com.hthk.crm.service;

import com.hthk.crm.domain.DeliveryMethod;
import com.hthk.crm.repository.DeliveryMethodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DeliveryMethod}.
 */
@Service
public class DeliveryMethodService {

    private final Logger log = LoggerFactory.getLogger(DeliveryMethodService.class);

    private final DeliveryMethodRepository deliveryMethodRepository;

    public DeliveryMethodService(DeliveryMethodRepository deliveryMethodRepository) {
        this.deliveryMethodRepository = deliveryMethodRepository;
    }

    /**
     * Save a deliveryMethod.
     *
     * @param deliveryMethod the entity to save.
     * @return the persisted entity.
     */
    public DeliveryMethod save(DeliveryMethod deliveryMethod) {
        log.debug("Request to save DeliveryMethod : {}", deliveryMethod);
        return deliveryMethodRepository.save(deliveryMethod);
    }

    /**
     * Get all the deliveryMethods.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<DeliveryMethod> findAll(Pageable pageable) {
        log.debug("Request to get all DeliveryMethods");
        return deliveryMethodRepository.findAll(pageable);
    }

    /**
     * Get one deliveryMethod by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<DeliveryMethod> findOne(String id) {
        log.debug("Request to get DeliveryMethod : {}", id);
        return deliveryMethodRepository.findById(id);
    }

    /**
     * Delete the deliveryMethod by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete DeliveryMethod : {}", id);
        deliveryMethodRepository.deleteById(id);
    }
}
