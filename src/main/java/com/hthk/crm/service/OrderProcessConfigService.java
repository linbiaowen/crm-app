package com.hthk.crm.service;

import com.hthk.crm.domain.OrderProcessConfig;
import com.hthk.crm.repository.OrderProcessConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OrderProcessConfig}.
 */
@Service
public class OrderProcessConfigService {

    private final Logger log = LoggerFactory.getLogger(OrderProcessConfigService.class);

    private final OrderProcessConfigRepository orderProcessConfigRepository;

    public OrderProcessConfigService(OrderProcessConfigRepository orderProcessConfigRepository) {
        this.orderProcessConfigRepository = orderProcessConfigRepository;
    }

    /**
     * Save a orderProcessConfig.
     *
     * @param orderProcessConfig the entity to save.
     * @return the persisted entity.
     */
    public OrderProcessConfig save(OrderProcessConfig orderProcessConfig) {
        log.debug("Request to save OrderProcessConfig : {}", orderProcessConfig);
        return orderProcessConfigRepository.save(orderProcessConfig);
    }

    /**
     * Get all the orderProcessConfigs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<OrderProcessConfig> findAll(Pageable pageable) {
        log.debug("Request to get all OrderProcessConfigs");
        return orderProcessConfigRepository.findAll(pageable);
    }

    /**
     * Get one orderProcessConfig by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<OrderProcessConfig> findOne(String id) {
        log.debug("Request to get OrderProcessConfig : {}", id);
        return orderProcessConfigRepository.findById(id);
    }

    /**
     * Delete the orderProcessConfig by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete OrderProcessConfig : {}", id);
        orderProcessConfigRepository.deleteById(id);
    }
}
