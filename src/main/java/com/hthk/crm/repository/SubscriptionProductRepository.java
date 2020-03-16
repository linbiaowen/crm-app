package com.hthk.crm.repository;

import com.hthk.crm.domain.SubscriptionProduct;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the SubscriptionProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubscriptionProductRepository extends MongoRepository<SubscriptionProduct, String> {

}
