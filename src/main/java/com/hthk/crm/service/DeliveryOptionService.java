package com.hthk.crm.service;

import com.hthk.crm.domain.DeliveryOption;
import com.hthk.crm.repository.DeliveryOptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DeliveryOption}.
 */
@Service
public class DeliveryOptionService {

    private final Logger log = LoggerFactory.getLogger(DeliveryOptionService.class);

    private final DeliveryOptionRepository deliveryOptionRepository;

    public DeliveryOptionService(DeliveryOptionRepository deliveryOptionRepository) {
        this.deliveryOptionRepository = deliveryOptionRepository;
    }

    /**
     * Save a deliveryOption.
     *
     * @param deliveryOption the entity to save.
     * @return the persisted entity.
     */
    public DeliveryOption save(DeliveryOption deliveryOption) {
        log.debug("Request to save DeliveryOption : {}", deliveryOption);
        return deliveryOptionRepository.save(deliveryOption);
    }

    /**
     * Get all the deliveryOptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<DeliveryOption> findAll(Pageable pageable) {
        log.debug("Request to get all DeliveryOptions");
        return deliveryOptionRepository.findAll(pageable);
    }

    /**
     * Get one deliveryOption by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<DeliveryOption> findOne(String id) {
        log.debug("Request to get DeliveryOption : {}", id);
        return deliveryOptionRepository.findById(id);
    }

    /**
     * Delete the deliveryOption by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete DeliveryOption : {}", id);
        deliveryOptionRepository.deleteById(id);
    }
}
