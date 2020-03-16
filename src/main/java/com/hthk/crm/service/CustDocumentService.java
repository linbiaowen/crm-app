package com.hthk.crm.service;

import com.hthk.crm.domain.CustDocument;
import com.hthk.crm.repository.CustDocumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CustDocument}.
 */
@Service
public class CustDocumentService {

    private final Logger log = LoggerFactory.getLogger(CustDocumentService.class);

    private final CustDocumentRepository custDocumentRepository;

    public CustDocumentService(CustDocumentRepository custDocumentRepository) {
        this.custDocumentRepository = custDocumentRepository;
    }

    /**
     * Save a custDocument.
     *
     * @param custDocument the entity to save.
     * @return the persisted entity.
     */
    public CustDocument save(CustDocument custDocument) {
        log.debug("Request to save CustDocument : {}", custDocument);
        return custDocumentRepository.save(custDocument);
    }

    /**
     * Get all the custDocuments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<CustDocument> findAll(Pageable pageable) {
        log.debug("Request to get all CustDocuments");
        return custDocumentRepository.findAll(pageable);
    }

    /**
     * Get one custDocument by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<CustDocument> findOne(String id) {
        log.debug("Request to get CustDocument : {}", id);
        return custDocumentRepository.findById(id);
    }

    /**
     * Delete the custDocument by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete CustDocument : {}", id);
        custDocumentRepository.deleteById(id);
    }
}
