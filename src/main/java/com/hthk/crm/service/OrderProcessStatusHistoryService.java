package com.hthk.crm.service;

import com.hthk.crm.domain.OrderProcessStatusHistory;
import com.hthk.crm.repository.OrderProcessStatusHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OrderProcessStatusHistory}.
 */
@Service
public class OrderProcessStatusHistoryService {

    private final Logger log = LoggerFactory.getLogger(OrderProcessStatusHistoryService.class);

    private final OrderProcessStatusHistoryRepository orderProcessStatusHistoryRepository;

    public OrderProcessStatusHistoryService(OrderProcessStatusHistoryRepository orderProcessStatusHistoryRepository) {
        this.orderProcessStatusHistoryRepository = orderProcessStatusHistoryRepository;
    }

    /**
     * Save a orderProcessStatusHistory.
     *
     * @param orderProcessStatusHistory the entity to save.
     * @return the persisted entity.
     */
    public OrderProcessStatusHistory save(OrderProcessStatusHistory orderProcessStatusHistory) {
        log.debug("Request to save OrderProcessStatusHistory : {}", orderProcessStatusHistory);
        return orderProcessStatusHistoryRepository.save(orderProcessStatusHistory);
    }

    /**
     * Get all the orderProcessStatusHistories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<OrderProcessStatusHistory> findAll(Pageable pageable) {
        log.debug("Request to get all OrderProcessStatusHistories");
        return orderProcessStatusHistoryRepository.findAll(pageable);
    }

    /**
     * Get one orderProcessStatusHistory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<OrderProcessStatusHistory> findOne(String id) {
        log.debug("Request to get OrderProcessStatusHistory : {}", id);
        return orderProcessStatusHistoryRepository.findById(id);
    }

    /**
     * Delete the orderProcessStatusHistory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete OrderProcessStatusHistory : {}", id);
        orderProcessStatusHistoryRepository.deleteById(id);
    }
}
