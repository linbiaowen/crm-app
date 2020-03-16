package com.hthk.crm.service;

import com.hthk.crm.domain.CustContact;
import com.hthk.crm.repository.CustContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CustContact}.
 */
@Service
public class CustContactService {

    private final Logger log = LoggerFactory.getLogger(CustContactService.class);

    private final CustContactRepository custContactRepository;

    public CustContactService(CustContactRepository custContactRepository) {
        this.custContactRepository = custContactRepository;
    }

    /**
     * Save a custContact.
     *
     * @param custContact the entity to save.
     * @return the persisted entity.
     */
    public CustContact save(CustContact custContact) {
        log.debug("Request to save CustContact : {}", custContact);
        return custContactRepository.save(custContact);
    }

    /**
     * Get all the custContacts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<CustContact> findAll(Pageable pageable) {
        log.debug("Request to get all CustContacts");
        return custContactRepository.findAll(pageable);
    }

    /**
     * Get one custContact by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<CustContact> findOne(String id) {
        log.debug("Request to get CustContact : {}", id);
        return custContactRepository.findById(id);
    }

    /**
     * Delete the custContact by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete CustContact : {}", id);
        custContactRepository.deleteById(id);
    }
}
