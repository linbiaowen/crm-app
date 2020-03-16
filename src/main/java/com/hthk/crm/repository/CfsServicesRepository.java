package com.hthk.crm.repository;

import com.hthk.crm.domain.CfsServices;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the CfsServices entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CfsServicesRepository extends MongoRepository<CfsServices, String> {

}
