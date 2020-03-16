package com.hthk.crm.service;

import com.hthk.crm.domain.OfferSpecification;
import com.hthk.crm.repository.OfferSpecificationRepository;
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
 * Service Implementation for managing {@link OfferSpecification}.
 */
@Service
public class OfferSpecificationService {

    private final Logger log = LoggerFactory.getLogger(OfferSpecificationService.class);

    private final OfferSpecificationRepository offerSpecificationRepository;

    public OfferSpecificationService(OfferSpecificationRepository offerSpecificationRepository) {
        this.offerSpecificationRepository = offerSpecificationRepository;
    }

    /**
     * Save a offerSpecification.
     *
     * @param offerSpecification the entity to save.
     * @return the persisted entity.
     */
    public OfferSpecification save(OfferSpecification offerSpecification) {
        log.debug("Request to save OfferSpecification : {}", offerSpecification);
        return offerSpecificationRepository.save(offerSpecification);
    }

    /**
     * Get all the offerSpecifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<OfferSpecification> findAll(Pageable pageable) {
        log.debug("Request to get all OfferSpecifications");
        return offerSpecificationRepository.findAll(pageable);
    }


    /**
     *  Get all the offerSpecifications where Offer is {@code null}.
     *  @return the list of entities.
     */
    public List<OfferSpecification> findAllWhereOfferIsNull() {
        log.debug("Request to get all offerSpecifications where Offer is null");
        return StreamSupport
            .stream(offerSpecificationRepository.findAll().spliterator(), false)
            .filter(offerSpecification -> offerSpecification.getOffer() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one offerSpecification by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<OfferSpecification> findOne(String id) {
        log.debug("Request to get OfferSpecification : {}", id);
        return offerSpecificationRepository.findById(id);
    }

    /**
     * Delete the offerSpecification by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete OfferSpecification : {}", id);
        offerSpecificationRepository.deleteById(id);
    }
}
