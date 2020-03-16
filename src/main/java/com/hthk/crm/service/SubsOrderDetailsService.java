package com.hthk.crm.service;

import com.hthk.crm.domain.SubsOrderDetails;
import com.hthk.crm.repository.SubsOrderDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SubsOrderDetails}.
 */
@Service
public class SubsOrderDetailsService {

    private final Logger log = LoggerFactory.getLogger(SubsOrderDetailsService.class);

    private final SubsOrderDetailsRepository subsOrderDetailsRepository;

    public SubsOrderDetailsService(SubsOrderDetailsRepository subsOrderDetailsRepository) {
        this.subsOrderDetailsRepository = subsOrderDetailsRepository;
    }

    /**
     * Save a subsOrderDetails.
     *
     * @param subsOrderDetails the entity to save.
     * @return the persisted entity.
     */
    public SubsOrderDetails save(SubsOrderDetails subsOrderDetails) {
        log.debug("Request to save SubsOrderDetails : {}", subsOrderDetails);
        return subsOrderDetailsRepository.save(subsOrderDetails);
    }

    /**
     * Get all the subsOrderDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<SubsOrderDetails> findAll(Pageable pageable) {
        log.debug("Request to get all SubsOrderDetails");
        return subsOrderDetailsRepository.findAll(pageable);
    }

    /**
     * Get one subsOrderDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<SubsOrderDetails> findOne(String id) {
        log.debug("Request to get SubsOrderDetails : {}", id);
        return subsOrderDetailsRepository.findById(id);
    }

    /**
     * Delete the subsOrderDetails by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete SubsOrderDetails : {}", id);
        subsOrderDetailsRepository.deleteById(id);
    }
}
