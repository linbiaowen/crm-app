package com.hthk.crm.repository;

import com.hthk.crm.domain.SubsItemDelivery;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the SubsItemDelivery entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubsItemDeliveryRepository extends MongoRepository<SubsItemDelivery, String> {

}
