package com.hthk.crm.repository;

import com.hthk.crm.domain.SubscriptionProvision;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the SubscriptionProvision entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubscriptionProvisionRepository extends MongoRepository<SubscriptionProvision, String> {

}
