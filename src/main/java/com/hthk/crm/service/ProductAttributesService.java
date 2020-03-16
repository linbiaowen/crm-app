package com.hthk.crm.service;

import com.hthk.crm.domain.DeliveryOption;
import com.hthk.crm.domain.ProductAttributes;
import com.hthk.crm.repository.ProductAttributesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProductAttributes}.
 */
@Service
public class ProductAttributesService {

    private final Logger log = LoggerFactory.getLogger(ProductAttributesService.class);

    private final ProductAttributesRepository productAttributesRepository;

    public ProductAttributesService(ProductAttributesRepository productAttributesRepository) {
        this.productAttributesRepository = productAttributesRepository;
    }

    /**
     * Save a productAttributes.
     *
     * @param productAttributes the entity to save.
     * @return the persisted entity.
     */
    public ProductAttributes save(ProductAttributes productAttributes) {
        log.debug("Request to save ProductAttributes : {}", productAttributes);

        DeliveryOption deliveryOption = new DeliveryOption();
        deliveryOption.setDeliveryOption(com.hthk.crm.domain.enumeration.DeliverOptions.EFLOCKER);
        productAttributes.getDeliveryOptions().add(deliveryOption);

        DeliveryOption deliveryOption1 = new DeliveryOption();
        deliveryOption1.setDeliveryOption(com.hthk.crm.domain.enumeration.DeliverOptions.POST);
        productAttributes.getDeliveryOptions().add(deliveryOption1);
        

        return productAttributesRepository.save(productAttributes);
    }

    /**
     * Get all the productAttributes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<ProductAttributes> findAll(Pageable pageable) {
        log.debug("Request to get all ProductAttributes");
        return productAttributesRepository.findAll(pageable);
    }

    /**
     * Get one productAttributes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<ProductAttributes> findOne(String id) {
        log.debug("Request to get ProductAttributes : {}", id);
        return productAttributesRepository.findById(id);
    }

    /**
     * Delete the productAttributes by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete ProductAttributes : {}", id);
        productAttributesRepository.deleteById(id);
    }
}
