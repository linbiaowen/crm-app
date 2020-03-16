package com.hthk.crm.service;

import com.hthk.crm.domain.ProductVoice;
import com.hthk.crm.repository.ProductVoiceRepository;
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
 * Service Implementation for managing {@link ProductVoice}.
 */
@Service
public class ProductVoiceService {

    private final Logger log = LoggerFactory.getLogger(ProductVoiceService.class);

    private final ProductVoiceRepository productVoiceRepository;

    public ProductVoiceService(ProductVoiceRepository productVoiceRepository) {
        this.productVoiceRepository = productVoiceRepository;
    }

    /**
     * Save a productVoice.
     *
     * @param productVoice the entity to save.
     * @return the persisted entity.
     */
    public ProductVoice save(ProductVoice productVoice) {
        log.debug("Request to save ProductVoice : {}", productVoice);
        return productVoiceRepository.save(productVoice);
    }

    /**
     * Get all the productVoices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<ProductVoice> findAll(Pageable pageable) {
        log.debug("Request to get all ProductVoices");
        return productVoiceRepository.findAll(pageable);
    }


    /**
     *  Get all the productVoices where ProductSpec is {@code null}.
     *  @return the list of entities.
     */
    public List<ProductVoice> findAllWhereProductSpecIsNull() {
        log.debug("Request to get all productVoices where ProductSpec is null");
        return StreamSupport
            .stream(productVoiceRepository.findAll().spliterator(), false)
            .filter(productVoice -> productVoice.getProductSpec() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one productVoice by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<ProductVoice> findOne(String id) {
        log.debug("Request to get ProductVoice : {}", id);
        return productVoiceRepository.findById(id);
    }

    /**
     * Delete the productVoice by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete ProductVoice : {}", id);
        productVoiceRepository.deleteById(id);
    }
}
