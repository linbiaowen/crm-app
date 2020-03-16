package com.hthk.crm.service;

import com.hthk.crm.domain.ProductData;
import com.hthk.crm.repository.ProductDataRepository;
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
 * Service Implementation for managing {@link ProductData}.
 */
@Service
public class ProductDataService {

    private final Logger log = LoggerFactory.getLogger(ProductDataService.class);

    private final ProductDataRepository productDataRepository;

    public ProductDataService(ProductDataRepository productDataRepository) {
        this.productDataRepository = productDataRepository;
    }

    /**
     * Save a productData.
     *
     * @param productData the entity to save.
     * @return the persisted entity.
     */
    public ProductData save(ProductData productData) {
        log.debug("Request to save ProductData : {}", productData);
        return productDataRepository.save(productData);
    }

    /**
     * Get all the productData.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<ProductData> findAll(Pageable pageable) {
        log.debug("Request to get all ProductData");
        return productDataRepository.findAll(pageable);
    }


    /**
     *  Get all the productData where ProductSpec is {@code null}.
     *  @return the list of entities.
     */
    public List<ProductData> findAllWhereProductSpecIsNull() {
        log.debug("Request to get all productData where ProductSpec is null");
        return StreamSupport
            .stream(productDataRepository.findAll().spliterator(), false)
            .filter(productData -> productData.getProductSpec() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one productData by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<ProductData> findOne(String id) {
        log.debug("Request to get ProductData : {}", id);
        return productDataRepository.findById(id);
    }

    /**
     * Delete the productData by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete ProductData : {}", id);
        productDataRepository.deleteById(id);
    }
}
