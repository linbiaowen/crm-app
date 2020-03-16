package com.hthk.crm.repository;

import com.hthk.crm.domain.SubscriptionDetails;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the SubscriptionDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubscriptionDetailsRepository extends MongoRepository<SubscriptionDetails, String> {

}
