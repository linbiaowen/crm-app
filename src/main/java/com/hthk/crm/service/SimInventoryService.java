package com.hthk.crm.service;

import com.hthk.crm.domain.SimInventory;
import com.hthk.crm.repository.SimInventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SimInventory}.
 */
@Service
public class SimInventoryService {

    private final Logger log = LoggerFactory.getLogger(SimInventoryService.class);

    private final SimInventoryRepository simInventoryRepository;

    public SimInventoryService(SimInventoryRepository simInventoryRepository) {
        this.simInventoryRepository = simInventoryRepository;
    }

    /**
     * Save a simInventory.
     *
     * @param simInventory the entity to save.
     * @return the persisted entity.
     */
    public SimInventory save(SimInventory simInventory) {
        log.debug("Request to save SimInventory : {}", simInventory);
        return simInventoryRepository.save(simInventory);
    }

    /**
     * Get all the simInventories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<SimInventory> findAll(Pageable pageable) {
        log.debug("Request to get all SimInventories");
        return simInventoryRepository.findAll(pageable);
    }

    /**
     * Get one simInventory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<SimInventory> findOne(String id) {
        log.debug("Request to get SimInventory : {}", id);
        return simInventoryRepository.findById(id);
    }

    /**
     * Delete the simInventory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete SimInventory : {}", id);
        simInventoryRepository.deleteById(id);
    }
}
