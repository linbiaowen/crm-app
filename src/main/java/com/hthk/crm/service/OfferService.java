package com.hthk.crm.service;

import com.hthk.crm.domain.Offer;
import com.hthk.crm.domain.OfferAdvancePayment;
import com.hthk.crm.domain.Product;
import com.hthk.crm.domain.ProductSpecification;
import com.hthk.crm.domain.embed.AdvancePayment;
import com.hthk.crm.repository.OfferAdvancePaymentRepository;
import com.hthk.crm.repository.OfferRepository;
import com.hthk.crm.repository.ProductRepository;
import com.hthk.crm.repository.ProductSpecificationRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Offer}.
 */
@Service
public class OfferService {

    private final Logger log = LoggerFactory.getLogger(OfferService.class);

    private final OfferRepository offerRepository;

    private final OfferAdvancePaymentRepository offerAdvancePaymentRepository;

    private final ProductSpecificationRepository productSpecificationRepository;

    public OfferService(OfferRepository offerRepository, OfferAdvancePaymentRepository offerAdvancePaymentRepository, ProductSpecificationRepository productSpecificationRepository) {
        this.offerRepository = offerRepository;
        this.offerAdvancePaymentRepository = offerAdvancePaymentRepository;
        this.productSpecificationRepository = productSpecificationRepository;
    }

    /**
     * Save a offer.
     *
     * @param offer the entity to save.
     * @return the persisted entity.
     */
    public Offer save(Offer offer) {
        log.debug("Request to save Offer : {}", offer);

        /*
        OfferAdvancePayment offerAdvancePayment = offerAdvancePaymentRepository.findByOfferAdvancePaymentId(2L);
        log.debug("Request to get OfferAdvancePayment : {}", offerAdvancePayment);
        if (offerAdvancePayment != null) {
            AdvancePayment advancePayment = new AdvancePayment();
            advancePayment.setAdvancePaymentId(offerAdvancePayment.getAdvancePaymentId());
            advancePayment.setAdvancePaymentMonths(offerAdvancePayment.getAdvancePaymentMonths());
            advancePayment.setId(offerAdvancePayment.getId());
            advancePayment.setOfferPrice(offerAdvancePayment.getOfferPrice());
            advancePayment.setOfferDiscount(offerAdvancePayment.getOfferDiscount());
            offer.getAdvancePayments().add(advancePayment);
            offer.addOfferAdvancePayment(offerAdvancePayment);
        }
        */
        /**
         * testing code to setup offer advancePayment
         */
        if (StringUtils.isNotBlank(offer.getAdvancePaymentIds())){
            String[] advancePaymentIds = offer.getAdvancePaymentIds().split(",");
            for (String advancePaymentId : advancePaymentIds){
                OfferAdvancePayment offerAdvancePayment = offerAdvancePaymentRepository.findByOfferAdvancePaymentId(Long.parseLong(advancePaymentId));
                offer.addOfferAdvancePayment(offerAdvancePayment);
            }
        }

        /**
         * testing code to setup offer product specs.
         */
        if (StringUtils.isNotBlank(offer.getProductSpecIds())){
            log.debug("offer.getProductSpecIds()=" + offer.getProductSpecIds());
            String[] productSpecIds = offer.getProductSpecIds().split(",");
            for (String productSpecId : productSpecIds){
                log.debug("productSpecId=" + productSpecId);
                ProductSpecification productSpec = productSpecificationRepository.findByProdutSpecId(Long.parseLong(productSpecId));
                log.debug("productSpec=" + productSpec == null ? "not found" : productSpec.getProductId());
                offer.addProductSpecifications(productSpec);
            }
        }
        

        return offerRepository.save(offer);
    }

    /**
     * Get all the offers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<Offer> findAll(Pageable pageable) {
        log.debug("Request to get all Offers");
        return offerRepository.findAll(pageable);
    }

    /**
     * Get all the offers with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Offer> findAllWithEagerRelationships(Pageable pageable) {
        return offerRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one offer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<Offer> findOne(String id) {
        log.debug("Request to get Offer : {}", id);
        return offerRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the offer by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Offer : {}", id);
        offerRepository.deleteById(id);
    }
}
