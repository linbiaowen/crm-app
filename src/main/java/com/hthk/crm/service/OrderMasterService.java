package com.hthk.crm.service;

import com.hthk.crm.domain.OrderMaster;
import com.hthk.crm.repository.OrderMasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OrderMaster}.
 */
@Service
public class OrderMasterService {

    private final Logger log = LoggerFactory.getLogger(OrderMasterService.class);

    private final OrderMasterRepository orderMasterRepository;

    public OrderMasterService(OrderMasterRepository orderMasterRepository) {
        this.orderMasterRepository = orderMasterRepository;
    }

    /**
     * Save a orderMaster.
     *
     * @param orderMaster the entity to save.
     * @return the persisted entity.
     */
    public OrderMaster save(OrderMaster orderMaster) {
        log.debug("Request to save OrderMaster : {}", orderMaster);
        return orderMasterRepository.save(orderMaster);
    }

    /**
     * Get all the orderMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<OrderMaster> findAll(Pageable pageable) {
        log.debug("Request to get all OrderMasters");
        return orderMasterRepository.findAll(pageable);
    }

    /**
     * Get one orderMaster by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<OrderMaster> findOne(String id) {
        log.debug("Request to get OrderMaster : {}", id);
        return orderMasterRepository.findById(id);
    }

    /**
     * Delete the orderMaster by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete OrderMaster : {}", id);
        orderMasterRepository.deleteById(id);
    }
}
