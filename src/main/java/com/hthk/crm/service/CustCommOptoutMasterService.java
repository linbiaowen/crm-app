package com.hthk.crm.service;

import com.hthk.crm.domain.CustCommOptoutMaster;
import com.hthk.crm.repository.CustCommOptoutMasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CustCommOptoutMaster}.
 */
@Service
public class CustCommOptoutMasterService {

    private final Logger log = LoggerFactory.getLogger(CustCommOptoutMasterService.class);

    private final CustCommOptoutMasterRepository custCommOptoutMasterRepository;

    public CustCommOptoutMasterService(CustCommOptoutMasterRepository custCommOptoutMasterRepository) {
        this.custCommOptoutMasterRepository = custCommOptoutMasterRepository;
    }

    /**
     * Save a custCommOptoutMaster.
     *
     * @param custCommOptoutMaster the entity to save.
     * @return the persisted entity.
     */
    public CustCommOptoutMaster save(CustCommOptoutMaster custCommOptoutMaster) {
        log.debug("Request to save CustCommOptoutMaster : {}", custCommOptoutMaster);
        return custCommOptoutMasterRepository.save(custCommOptoutMaster);
    }

    /**
     * Get all the custCommOptoutMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<CustCommOptoutMaster> findAll(Pageable pageable) {
        log.debug("Request to get all CustCommOptoutMasters");
        return custCommOptoutMasterRepository.findAll(pageable);
    }

    /**
     * Get one custCommOptoutMaster by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<CustCommOptoutMaster> findOne(String id) {
        log.debug("Request to get CustCommOptoutMaster : {}", id);
        return custCommOptoutMasterRepository.findById(id);
    }

    /**
     * Delete the custCommOptoutMaster by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete CustCommOptoutMaster : {}", id);
        custCommOptoutMasterRepository.deleteById(id);
    }
}
