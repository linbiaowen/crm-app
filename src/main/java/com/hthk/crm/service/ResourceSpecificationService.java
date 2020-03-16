package com.hthk.crm.service;

import com.hthk.crm.domain.ResourceSpecification;
import com.hthk.crm.repository.ResourceSpecificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ResourceSpecification}.
 */
@Service
public class ResourceSpecificationService {

    private final Logger log = LoggerFactory.getLogger(ResourceSpecificationService.class);

    private final ResourceSpecificationRepository resourceSpecificationRepository;

    public ResourceSpecificationService(ResourceSpecificationRepository resourceSpecificationRepository) {
        this.resourceSpecificationRepository = resourceSpecificationRepository;
    }

    /**
     * Save a resourceSpecification.
     *
     * @param resourceSpecification the entity to save.
     * @return the persisted entity.
     */
    public ResourceSpecification save(ResourceSpecification resourceSpecification) {
        log.debug("Request to save ResourceSpecification : {}", resourceSpecification);
        return resourceSpecificationRepository.save(resourceSpecification);
    }

    /**
     * Get all the resourceSpecifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<ResourceSpecification> findAll(Pageable pageable) {
        log.debug("Request to get all ResourceSpecifications");
        return resourceSpecificationRepository.findAll(pageable);
    }

    /**
     * Get one resourceSpecification by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<ResourceSpecification> findOne(String id) {
        log.debug("Request to get ResourceSpecification : {}", id);
        return resourceSpecificationRepository.findById(id);
    }

    /**
     * Delete the resourceSpecification by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete ResourceSpecification : {}", id);
        resourceSpecificationRepository.deleteById(id);
    }
}
