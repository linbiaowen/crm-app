package com.hthk.crm.service;

import com.hthk.crm.domain.SubsDeliveryItemDetails;
import com.hthk.crm.repository.SubsDeliveryItemDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SubsDeliveryItemDetails}.
 */
@Service
public class SubsDeliveryItemDetailsService {

    private final Logger log = LoggerFactory.getLogger(SubsDeliveryItemDetailsService.class);

    private final SubsDeliveryItemDetailsRepository subsDeliveryItemDetailsRepository;

    public SubsDeliveryItemDetailsService(SubsDeliveryItemDetailsRepository subsDeliveryItemDetailsRepository) {
        this.subsDeliveryItemDetailsRepository = subsDeliveryItemDetailsRepository;
    }

    /**
     * Save a subsDeliveryItemDetails.
     *
     * @param subsDeliveryItemDetails the entity to save.
     * @return the persisted entity.
     */
    public SubsDeliveryItemDetails save(SubsDeliveryItemDetails subsDeliveryItemDetails) {
        log.debug("Request to save SubsDeliveryItemDetails : {}", subsDeliveryItemDetails);
        return subsDeliveryItemDetailsRepository.save(subsDeliveryItemDetails);
    }

    /**
     * Get all the subsDeliveryItemDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<SubsDeliveryItemDetails> findAll(Pageable pageable) {
        log.debug("Request to get all SubsDeliveryItemDetails");
        return subsDeliveryItemDetailsRepository.findAll(pageable);
    }

    /**
     * Get one subsDeliveryItemDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<SubsDeliveryItemDetails> findOne(String id) {
        log.debug("Request to get SubsDeliveryItemDetails : {}", id);
        return subsDeliveryItemDetailsRepository.findById(id);
    }

    /**
     * Delete the subsDeliveryItemDetails by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete SubsDeliveryItemDetails : {}", id);
        subsDeliveryItemDetailsRepository.deleteById(id);
    }
}
