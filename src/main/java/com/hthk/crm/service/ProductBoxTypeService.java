package com.hthk.crm.service;

import com.hthk.crm.domain.ProductBoxType;
import com.hthk.crm.repository.ProductBoxTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProductBoxType}.
 */
@Service
public class ProductBoxTypeService {

    private final Logger log = LoggerFactory.getLogger(ProductBoxTypeService.class);

    private final ProductBoxTypeRepository productBoxTypeRepository;

    public ProductBoxTypeService(ProductBoxTypeRepository productBoxTypeRepository) {
        this.productBoxTypeRepository = productBoxTypeRepository;
    }

    /**
     * Save a productBoxType.
     *
     * @param productBoxType the entity to save.
     * @return the persisted entity.
     */
    public ProductBoxType save(ProductBoxType productBoxType) {
        log.debug("Request to save ProductBoxType : {}", productBoxType);
        return productBoxTypeRepository.save(productBoxType);
    }

    /**
     * Get all the productBoxTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<ProductBoxType> findAll(Pageable pageable) {
        log.debug("Request to get all ProductBoxTypes");
        return productBoxTypeRepository.findAll(pageable);
    }

    /**
     * Get one productBoxType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<ProductBoxType> findOne(String id) {
        log.debug("Request to get ProductBoxType : {}", id);
        return productBoxTypeRepository.findById(id);
    }

    /**
     * Delete the productBoxType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete ProductBoxType : {}", id);
        productBoxTypeRepository.deleteById(id);
    }
}
