package com.hthk.crm.service;

import com.hthk.crm.domain.BillCycle;
import com.hthk.crm.repository.BillCycleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BillCycle}.
 */
@Service
public class BillCycleService {

    private final Logger log = LoggerFactory.getLogger(BillCycleService.class);

    private final BillCycleRepository billCycleRepository;

    public BillCycleService(BillCycleRepository billCycleRepository) {
        this.billCycleRepository = billCycleRepository;
    }

    /**
     * Save a billCycle.
     *
     * @param billCycle the entity to save.
     * @return the persisted entity.
     */
    public BillCycle save(BillCycle billCycle) {
        log.debug("Request to save BillCycle : {}", billCycle);
        return billCycleRepository.save(billCycle);
    }

    /**
     * Get all the billCycles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<BillCycle> findAll(Pageable pageable) {
        log.debug("Request to get all BillCycles");
        return billCycleRepository.findAll(pageable);
    }

    /**
     * Get one billCycle by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<BillCycle> findOne(String id) {
        log.debug("Request to get BillCycle : {}", id);
        return billCycleRepository.findById(id);
    }

    /**
     * Delete the billCycle by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete BillCycle : {}", id);
        billCycleRepository.deleteById(id);
    }
}
