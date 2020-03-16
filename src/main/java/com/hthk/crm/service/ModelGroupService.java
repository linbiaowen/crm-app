package com.hthk.crm.service;

import com.hthk.crm.domain.ModelGroup;
import com.hthk.crm.repository.ModelGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ModelGroup}.
 */
@Service
public class ModelGroupService {

    private final Logger log = LoggerFactory.getLogger(ModelGroupService.class);

    private final ModelGroupRepository modelGroupRepository;

    public ModelGroupService(ModelGroupRepository modelGroupRepository) {
        this.modelGroupRepository = modelGroupRepository;
    }

    /**
     * Save a modelGroup.
     *
     * @param modelGroup the entity to save.
     * @return the persisted entity.
     */
    public ModelGroup save(ModelGroup modelGroup) {
        log.debug("Request to save ModelGroup : {}", modelGroup);
        return modelGroupRepository.save(modelGroup);
    }

    /**
     * Get all the modelGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<ModelGroup> findAll(Pageable pageable) {
        log.debug("Request to get all ModelGroups");
        return modelGroupRepository.findAll(pageable);
    }

    /**
     * Get one modelGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<ModelGroup> findOne(String id) {
        log.debug("Request to get ModelGroup : {}", id);
        return modelGroupRepository.findById(id);
    }

    /**
     * Delete the modelGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete ModelGroup : {}", id);
        modelGroupRepository.deleteById(id);
    }
}
