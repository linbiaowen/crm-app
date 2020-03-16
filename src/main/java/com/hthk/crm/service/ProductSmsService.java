package com.hthk.crm.service;

import com.hthk.crm.domain.ProductSms;
import com.hthk.crm.repository.ProductSmsRepository;
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
 * Service Implementation for managing {@link ProductSms}.
 */
@Service
public class ProductSmsService {

    private final Logger log = LoggerFactory.getLogger(ProductSmsService.class);

    private final ProductSmsRepository productSmsRepository;

    public ProductSmsService(ProductSmsRepository productSmsRepository) {
        this.productSmsRepository = productSmsRepository;
    }

    /**
     * Save a productSms.
     *
     * @param productSms the entity to save.
     * @return the persisted entity.
     */
    public ProductSms save(ProductSms productSms) {
        log.debug("Request to save ProductSms : {}", productSms);
        return productSmsRepository.save(productSms);
    }

    /**
     * Get all the productSms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<ProductSms> findAll(Pageable pageable) {
        log.debug("Request to get all ProductSms");
        return productSmsRepository.findAll(pageable);
    }


    /**
     *  Get all the productSms where ProductSpec is {@code null}.
     *  @return the list of entities.
     */
    public List<ProductSms> findAllWhereProductSpecIsNull() {
        log.debug("Request to get all productSms where ProductSpec is null");
        return StreamSupport
            .stream(productSmsRepository.findAll().spliterator(), false)
            .filter(productSms -> productSms.getProductSpec() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one productSms by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<ProductSms> findOne(String id) {
        log.debug("Request to get ProductSms : {}", id);
        return productSmsRepository.findById(id);
    }

    /**
     * Delete the productSms by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete ProductSms : {}", id);
        productSmsRepository.deleteById(id);
    }
}
