package com.hthk.crm.service;

import com.hthk.crm.domain.ProductSimAttributes;
import com.hthk.crm.repository.ProductSimAttributesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProductSimAttributes}.
 */
@Service
public class ProductSimAttributesService {

    private final Logger log = LoggerFactory.getLogger(ProductSimAttributesService.class);

    private final ProductSimAttributesRepository productSimAttributesRepository;

    public ProductSimAttributesService(ProductSimAttributesRepository productSimAttributesRepository) {
        this.productSimAttributesRepository = productSimAttributesRepository;
    }

    /**
     * Save a productSimAttributes.
     *
     * @param productSimAttributes the entity to save.
     * @return the persisted entity.
     */
    public ProductSimAttributes save(ProductSimAttributes productSimAttributes) {
        log.debug("Request to save ProductSimAttributes : {}", productSimAttributes);
        return productSimAttributesRepository.save(productSimAttributes);
    }

    /**
     * Get all the productSimAttributes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<ProductSimAttributes> findAll(Pageable pageable) {
        log.debug("Request to get all ProductSimAttributes");
        return productSimAttributesRepository.findAll(pageable);
    }

    /**
     * Get one productSimAttributes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<ProductSimAttributes> findOne(String id) {
        log.debug("Request to get ProductSimAttributes : {}", id);
        return productSimAttributesRepository.findById(id);
    }

    /**
     * Delete the productSimAttributes by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete ProductSimAttributes : {}", id);
        productSimAttributesRepository.deleteById(id);
    }
}
