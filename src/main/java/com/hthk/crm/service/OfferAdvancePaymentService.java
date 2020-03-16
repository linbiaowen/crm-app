package com.hthk.crm.service;

import com.hthk.crm.domain.OfferAdvancePayment;
import com.hthk.crm.repository.OfferAdvancePaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OfferAdvancePayment}.
 */
@Service
public class OfferAdvancePaymentService {

    private final Logger log = LoggerFactory.getLogger(OfferAdvancePaymentService.class);

    private final OfferAdvancePaymentRepository offerAdvancePaymentRepository;

    public OfferAdvancePaymentService(OfferAdvancePaymentRepository offerAdvancePaymentRepository) {
        this.offerAdvancePaymentRepository = offerAdvancePaymentRepository;
    }

    /**
     * Save a offerAdvancePayment.
     *
     * @param offerAdvancePayment the entity to save.
     * @return the persisted entity.
     */
    public OfferAdvancePayment save(OfferAdvancePayment offerAdvancePayment) {
        log.debug("Request to save OfferAdvancePayment : {}", offerAdvancePayment);
        return offerAdvancePaymentRepository.save(offerAdvancePayment);
    }

    /**
     * Get all the offerAdvancePayments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<OfferAdvancePayment> findAll(Pageable pageable) {
        log.debug("Request to get all OfferAdvancePayments");
        return offerAdvancePaymentRepository.findAll(pageable);
    }

    /**
     * Get one offerAdvancePayment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<OfferAdvancePayment> findOne(String id) {
        log.debug("Request to get OfferAdvancePayment : {}", id);
        return offerAdvancePaymentRepository.findById(id);
    }

    /**
     * Delete the offerAdvancePayment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete OfferAdvancePayment : {}", id);
        offerAdvancePaymentRepository.deleteById(id);
    }
}
