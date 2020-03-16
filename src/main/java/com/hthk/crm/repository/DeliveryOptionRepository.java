package com.hthk.crm.repository;

import com.hthk.crm.domain.DeliveryOption;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the DeliveryOption entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliveryOptionRepository extends MongoRepository<DeliveryOption, String> {

}
