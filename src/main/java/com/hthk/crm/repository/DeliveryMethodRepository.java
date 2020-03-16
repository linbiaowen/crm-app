package com.hthk.crm.repository;

import com.hthk.crm.domain.DeliveryMethod;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the DeliveryMethod entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliveryMethodRepository extends MongoRepository<DeliveryMethod, String> {

}
