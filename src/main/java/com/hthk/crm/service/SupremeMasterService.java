package com.hthk.crm.service;

import com.hthk.crm.domain.SupremeMaster;
import com.hthk.crm.repository.SupremeMasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SupremeMaster}.
 */
@Service
public class SupremeMasterService {

    private final Logger log = LoggerFactory.getLogger(SupremeMasterService.class);

    private final SupremeMasterRepository supremeMasterRepository;

    public SupremeMasterService(SupremeMasterRepository supremeMasterRepository) {
        this.supremeMasterRepository = supremeMasterRepository;
    }

    /**
     * Save a supremeMaster.
     *
     * @param supremeMaster the entity to save.
     * @return the persisted entity.
     */
    public SupremeMaster save(SupremeMaster supremeMaster) {
        log.debug("Request to save SupremeMaster : {}", supremeMaster);
        return supremeMasterRepository.save(supremeMaster);
    }

    /**
     * Get all the supremeMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<SupremeMaster> findAll(Pageable pageable) {
        log.debug("Request to get all SupremeMasters");
        return supremeMasterRepository.findAll(pageable);
    }

    /**
     * Get one supremeMaster by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<SupremeMaster> findOne(String id) {
        log.debug("Request to get SupremeMaster : {}", id);
        return supremeMasterRepository.findById(id);
    }

    /**
     * Delete the supremeMaster by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete SupremeMaster : {}", id);
        supremeMasterRepository.deleteById(id);
    }
}
