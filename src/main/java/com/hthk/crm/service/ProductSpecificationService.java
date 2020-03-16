package com.hthk.crm.service;

import com.hthk.crm.domain.ProductSpecification;
import com.hthk.crm.repository.ProductSpecificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link ProductSpecification}.
 */
@Service
public class ProductSpecificationService {

    private final Logger log = LoggerFactory.getLogger(ProductSpecificationService.class);

    private final ProductSpecificationRepository productSpecificationRepository;

    public ProductSpecificationService(ProductSpecificationRepository productSpecificationRepository) {
        this.productSpecificationRepository = productSpecificationRepository;
    }

    /**
     * Save a productSpecification.
     *
     * @param productSpecification the entity to save.
     * @return the persisted entity.
     */
    public ProductSpecification save(ProductSpecification productSpecification) {
        log.debug("Request to save ProductSpecification : {}", productSpecification);
        return productSpecificationRepository.save(productSpecification);
    }

    /**
     * Get all the productSpecifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<ProductSpecification> findAll(Pageable pageable) {
        log.debug("Request to get all ProductSpecifications");
        return productSpecificationRepository.findAll(pageable);
    }


    /**
     *  Get all the productSpecifications where Product is {@code null}.
     *  @return the list of entities.
     */
    public List<ProductSpecification> findAllWhereProductIsNull() {
        log.debug("Request to get all productSpecifications where Product is null");
        return StreamSupport
            .stream(productSpecificationRepository.findAll().spliterator(), false)
            .filter(productSpecification -> productSpecification.getProduct() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one productSpecification by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<ProductSpecification> findOne(String id) {
        log.debug("Request to get ProductSpecification : {}", id);
        return productSpecificationRepository.findById(id);
    }

    /**
     * Delete the productSpecification by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete ProductSpecification : {}", id);
        productSpecificationRepository.deleteById(id);
    }
}
