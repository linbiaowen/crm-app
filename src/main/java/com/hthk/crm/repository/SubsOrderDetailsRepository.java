package com.hthk.crm.repository;

import com.hthk.crm.domain.SubsOrderDetails;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the SubsOrderDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubsOrderDetailsRepository extends MongoRepository<SubsOrderDetails, String> {

}
