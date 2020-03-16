package com.hthk.crm.repository;

import com.hthk.crm.domain.SubsDeliveryItemDetails;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the SubsDeliveryItemDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubsDeliveryItemDetailsRepository extends MongoRepository<SubsDeliveryItemDetails, String> {

}
