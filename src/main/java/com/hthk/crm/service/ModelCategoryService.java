package com.hthk.crm.service;

import com.hthk.crm.domain.ModelCategory;
import com.hthk.crm.repository.ModelCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ModelCategory}.
 */
@Service
public class ModelCategoryService {

    private final Logger log = LoggerFactory.getLogger(ModelCategoryService.class);

    private final ModelCategoryRepository modelCategoryRepository;

    public ModelCategoryService(ModelCategoryRepository modelCategoryRepository) {
        this.modelCategoryRepository = modelCategoryRepository;
    }

    /**
     * Save a modelCategory.
     *
     * @param modelCategory the entity to save.
     * @return the persisted entity.
     */
    public ModelCategory save(ModelCategory modelCategory) {
        log.debug("Request to save ModelCategory : {}", modelCategory);
        return modelCategoryRepository.save(modelCategory);
    }

    /**
     * Get all the modelCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<ModelCategory> findAll(Pageable pageable) {
        log.debug("Request to get all ModelCategories");
        return modelCategoryRepository.findAll(pageable);
    }

    /**
     * Get one modelCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<ModelCategory> findOne(String id) {
        log.debug("Request to get ModelCategory : {}", id);
        return modelCategoryRepository.findById(id);
    }

    /**
     * Delete the modelCategory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete ModelCategory : {}", id);
        modelCategoryRepository.deleteById(id);
    }
}
