package com.hthk.crm.service;

import com.hthk.crm.domain.OrderProcessStatus;
import com.hthk.crm.repository.OrderProcessStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OrderProcessStatus}.
 */
@Service
public class OrderProcessStatusService {

    private final Logger log = LoggerFactory.getLogger(OrderProcessStatusService.class);

    private final OrderProcessStatusRepository orderProcessStatusRepository;

    public OrderProcessStatusService(OrderProcessStatusRepository orderProcessStatusRepository) {
        this.orderProcessStatusRepository = orderProcessStatusRepository;
    }

    /**
     * Save a orderProcessStatus.
     *
     * @param orderProcessStatus the entity to save.
     * @return the persisted entity.
     */
    public OrderProcessStatus save(OrderProcessStatus orderProcessStatus) {
        log.debug("Request to save OrderProcessStatus : {}", orderProcessStatus);
        return orderProcessStatusRepository.save(orderProcessStatus);
    }

    /**
     * Get all the orderProcessStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<OrderProcessStatus> findAll(Pageable pageable) {
        log.debug("Request to get all OrderProcessStatuses");
        return orderProcessStatusRepository.findAll(pageable);
    }

    /**
     * Get one orderProcessStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<OrderProcessStatus> findOne(String id) {
        log.debug("Request to get OrderProcessStatus : {}", id);
        return orderProcessStatusRepository.findById(id);
    }

    /**
     * Delete the orderProcessStatus by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete OrderProcessStatus : {}", id);
        orderProcessStatusRepository.deleteById(id);
    }
}
