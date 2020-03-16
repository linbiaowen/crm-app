package com.hthk.crm.service;

import com.hthk.crm.domain.SalesChannelMaster;
import com.hthk.crm.repository.SalesChannelMasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SalesChannelMaster}.
 */
@Service
public class SalesChannelMasterService {

    private final Logger log = LoggerFactory.getLogger(SalesChannelMasterService.class);

    private final SalesChannelMasterRepository salesChannelMasterRepository;

    public SalesChannelMasterService(SalesChannelMasterRepository salesChannelMasterRepository) {
        this.salesChannelMasterRepository = salesChannelMasterRepository;
    }

    /**
     * Save a salesChannelMaster.
     *
     * @param salesChannelMaster the entity to save.
     * @return the persisted entity.
     */
    public SalesChannelMaster save(SalesChannelMaster salesChannelMaster) {
        log.debug("Request to save SalesChannelMaster : {}", salesChannelMaster);
        return salesChannelMasterRepository.save(salesChannelMaster);
    }

    /**
     * Get all the salesChannelMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<SalesChannelMaster> findAll(Pageable pageable) {
        log.debug("Request to get all SalesChannelMasters");
        return salesChannelMasterRepository.findAll(pageable);
    }

    /**
     * Get one salesChannelMaster by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<SalesChannelMaster> findOne(String id) {
        log.debug("Request to get SalesChannelMaster : {}", id);
        return salesChannelMasterRepository.findById(id);
    }

    /**
     * Delete the salesChannelMaster by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete SalesChannelMaster : {}", id);
        salesChannelMasterRepository.deleteById(id);
    }
}
