package com.hthk.crm.repository;

import com.hthk.crm.domain.SubsPurchaseControl;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the SubsPurchaseControl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubsPurchaseControlRepository extends MongoRepository<SubsPurchaseControl, String> {

}
