package com.hthk.crm.service;

import com.hthk.crm.domain.BlackListMaster;
import com.hthk.crm.repository.BlackListMasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BlackListMaster}.
 */
@Service
public class BlackListMasterService {

    private final Logger log = LoggerFactory.getLogger(BlackListMasterService.class);

    private final BlackListMasterRepository blackListMasterRepository;

    public BlackListMasterService(BlackListMasterRepository blackListMasterRepository) {
        this.blackListMasterRepository = blackListMasterRepository;
    }

    /**
     * Save a blackListMaster.
     *
     * @param blackListMaster the entity to save.
     * @return the persisted entity.
     */
    public BlackListMaster save(BlackListMaster blackListMaster) {
        log.debug("Request to save BlackListMaster : {}", blackListMaster);
        return blackListMasterRepository.save(blackListMaster);
    }

    /**
     * Get all the blackListMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<BlackListMaster> findAll(Pageable pageable) {
        log.debug("Request to get all BlackListMasters");
        return blackListMasterRepository.findAll(pageable);
    }

    /**
     * Get one blackListMaster by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<BlackListMaster> findOne(String id) {
        log.debug("Request to get BlackListMaster : {}", id);
        return blackListMasterRepository.findById(id);
    }

    /**
     * Delete the blackListMaster by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete BlackListMaster : {}", id);
        blackListMasterRepository.deleteById(id);
    }
}
