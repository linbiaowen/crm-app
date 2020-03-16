package com.hthk.crm.service;

import com.hthk.crm.domain.CfsServices;
import com.hthk.crm.repository.CfsServicesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CfsServices}.
 */
@Service
public class CfsServicesService {

    private final Logger log = LoggerFactory.getLogger(CfsServicesService.class);

    private final CfsServicesRepository cfsServicesRepository;

    public CfsServicesService(CfsServicesRepository cfsServicesRepository) {
        this.cfsServicesRepository = cfsServicesRepository;
    }

    /**
     * Save a cfsServices.
     *
     * @param cfsServices the entity to save.
     * @return the persisted entity.
     */
    public CfsServices save(CfsServices cfsServices) {
        log.debug("Request to save CfsServices : {}", cfsServices);
        return cfsServicesRepository.save(cfsServices);
    }

    /**
     * Get all the cfsServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<CfsServices> findAll(Pageable pageable) {
        log.debug("Request to get all CfsServices");
        return cfsServicesRepository.findAll(pageable);
    }

    /**
     * Get one cfsServices by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<CfsServices> findOne(String id) {
        log.debug("Request to get CfsServices : {}", id);
        return cfsServicesRepository.findById(id);
    }

    /**
     * Delete the cfsServices by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete CfsServices : {}", id);
        cfsServicesRepository.deleteById(id);
    }
}
