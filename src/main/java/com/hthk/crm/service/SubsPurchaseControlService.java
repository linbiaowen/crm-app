package com.hthk.crm.service;

import com.hthk.crm.domain.SubsPurchaseControl;
import com.hthk.crm.repository.SubsPurchaseControlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SubsPurchaseControl}.
 */
@Service
public class SubsPurchaseControlService {

    private final Logger log = LoggerFactory.getLogger(SubsPurchaseControlService.class);

    private final SubsPurchaseControlRepository subsPurchaseControlRepository;

    public SubsPurchaseControlService(SubsPurchaseControlRepository subsPurchaseControlRepository) {
        this.subsPurchaseControlRepository = subsPurchaseControlRepository;
    }

    /**
     * Save a subsPurchaseControl.
     *
     * @param subsPurchaseControl the entity to save.
     * @return the persisted entity.
     */
    public SubsPurchaseControl save(SubsPurchaseControl subsPurchaseControl) {
        log.debug("Request to save SubsPurchaseControl : {}", subsPurchaseControl);
        return subsPurchaseControlRepository.save(subsPurchaseControl);
    }

    /**
     * Get all the subsPurchaseControls.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<SubsPurchaseControl> findAll(Pageable pageable) {
        log.debug("Request to get all SubsPurchaseControls");
        return subsPurchaseControlRepository.findAll(pageable);
    }

    /**
     * Get one subsPurchaseControl by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<SubsPurchaseControl> findOne(String id) {
        log.debug("Request to get SubsPurchaseControl : {}", id);
        return subsPurchaseControlRepository.findById(id);
    }

    /**
     * Delete the subsPurchaseControl by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete SubsPurchaseControl : {}", id);
        subsPurchaseControlRepository.deleteById(id);
    }
}
