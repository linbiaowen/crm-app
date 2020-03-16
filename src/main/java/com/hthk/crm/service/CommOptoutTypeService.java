package com.hthk.crm.service;

import com.hthk.crm.domain.CommOptoutType;
import com.hthk.crm.repository.CommOptoutTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CommOptoutType}.
 */
@Service
public class CommOptoutTypeService {

    private final Logger log = LoggerFactory.getLogger(CommOptoutTypeService.class);

    private final CommOptoutTypeRepository commOptoutTypeRepository;

    public CommOptoutTypeService(CommOptoutTypeRepository commOptoutTypeRepository) {
        this.commOptoutTypeRepository = commOptoutTypeRepository;
    }

    /**
     * Save a commOptoutType.
     *
     * @param commOptoutType the entity to save.
     * @return the persisted entity.
     */
    public CommOptoutType save(CommOptoutType commOptoutType) {
        log.debug("Request to save CommOptoutType : {}", commOptoutType);
        return commOptoutTypeRepository.save(commOptoutType);
    }

    /**
     * Get all the commOptoutTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<CommOptoutType> findAll(Pageable pageable) {
        log.debug("Request to get all CommOptoutTypes");
        return commOptoutTypeRepository.findAll(pageable);
    }

    /**
     * Get one commOptoutType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<CommOptoutType> findOne(String id) {
        log.debug("Request to get CommOptoutType : {}", id);
        return commOptoutTypeRepository.findById(id);
    }

    /**
     * Delete the commOptoutType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete CommOptoutType : {}", id);
        commOptoutTypeRepository.deleteById(id);
    }
}
