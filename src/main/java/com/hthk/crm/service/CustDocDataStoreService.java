package com.hthk.crm.service;

import com.hthk.crm.domain.CustDocDataStore;
import com.hthk.crm.repository.CustDocDataStoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CustDocDataStore}.
 */
@Service
public class CustDocDataStoreService {

    private final Logger log = LoggerFactory.getLogger(CustDocDataStoreService.class);

    private final CustDocDataStoreRepository custDocDataStoreRepository;

    public CustDocDataStoreService(CustDocDataStoreRepository custDocDataStoreRepository) {
        this.custDocDataStoreRepository = custDocDataStoreRepository;
    }

    /**
     * Save a custDocDataStore.
     *
     * @param custDocDataStore the entity to save.
     * @return the persisted entity.
     */
    public CustDocDataStore save(CustDocDataStore custDocDataStore) {
        log.debug("Request to save CustDocDataStore : {}", custDocDataStore);
        return custDocDataStoreRepository.save(custDocDataStore);
    }

    /**
     * Get all the custDocDataStores.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<CustDocDataStore> findAll(Pageable pageable) {
        log.debug("Request to get all CustDocDataStores");
        return custDocDataStoreRepository.findAll(pageable);
    }

    /**
     * Get one custDocDataStore by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<CustDocDataStore> findOne(String id) {
        log.debug("Request to get CustDocDataStore : {}", id);
        return custDocDataStoreRepository.findById(id);
    }

    /**
     * Delete the custDocDataStore by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete CustDocDataStore : {}", id);
        custDocDataStoreRepository.deleteById(id);
    }
}
