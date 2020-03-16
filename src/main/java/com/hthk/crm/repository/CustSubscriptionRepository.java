package com.hthk.crm.repository;

import com.hthk.crm.domain.CustSubscription;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the CustSubscription entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustSubscriptionRepository extends MongoRepository<CustSubscription, String> {

}
