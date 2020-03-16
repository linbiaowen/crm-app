package com.hthk.crm.service;

import com.hthk.crm.domain.CommMediaType;
import com.hthk.crm.repository.CommMediaTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CommMediaType}.
 */
@Service
public class CommMediaTypeService {

    private final Logger log = LoggerFactory.getLogger(CommMediaTypeService.class);

    private final CommMediaTypeRepository commMediaTypeRepository;

    public CommMediaTypeService(CommMediaTypeRepository commMediaTypeRepository) {
        this.commMediaTypeRepository = commMediaTypeRepository;
    }

    /**
     * Save a commMediaType.
     *
     * @param commMediaType the entity to save.
     * @return the persisted entity.
     */
    public CommMediaType save(CommMediaType commMediaType) {
        log.debug("Request to save CommMediaType : {}", commMediaType);
        return commMediaTypeRepository.save(commMediaType);
    }

    /**
     * Get all the commMediaTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<CommMediaType> findAll(Pageable pageable) {
        log.debug("Request to get all CommMediaTypes");
        return commMediaTypeRepository.findAll(pageable);
    }

    /**
     * Get one commMediaType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<CommMediaType> findOne(String id) {
        log.debug("Request to get CommMediaType : {}", id);
        return commMediaTypeRepository.findById(id);
    }

    /**
     * Delete the commMediaType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete CommMediaType : {}", id);
        commMediaTypeRepository.deleteById(id);
    }
}
