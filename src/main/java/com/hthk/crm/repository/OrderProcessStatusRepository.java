package com.hthk.crm.repository;

import com.hthk.crm.domain.OrderProcessStatus;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the OrderProcessStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderProcessStatusRepository extends MongoRepository<OrderProcessStatus, String> {

}
