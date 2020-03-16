package com.hthk.crm.service;

import com.hthk.crm.domain.CustAddress;
import com.hthk.crm.repository.CustAddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CustAddress}.
 */
@Service
public class CustAddressService {

    private final Logger log = LoggerFactory.getLogger(CustAddressService.class);

    private final CustAddressRepository custAddressRepository;

    public CustAddressService(CustAddressRepository custAddressRepository) {
        this.custAddressRepository = custAddressRepository;
    }

    /**
     * Save a custAddress.
     *
     * @param custAddress the entity to save.
     * @return the persisted entity.
     */
    public CustAddress save(CustAddress custAddress) {
        log.debug("Request to save CustAddress : {}", custAddress);
        return custAddressRepository.save(custAddress);
    }

    /**
     * Get all the custAddresses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<CustAddress> findAll(Pageable pageable) {
        log.debug("Request to get all CustAddresses");
        return custAddressRepository.findAll(pageable);
    }

    /**
     * Get one custAddress by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<CustAddress> findOne(String id) {
        log.debug("Request to get CustAddress : {}", id);
        return custAddressRepository.findById(id);
    }

    /**
     * Delete the custAddress by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete CustAddress : {}", id);
        custAddressRepository.deleteById(id);
    }
}
