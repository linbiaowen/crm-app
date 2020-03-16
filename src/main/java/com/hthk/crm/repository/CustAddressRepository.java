package com.hthk.crm.repository;

import com.hthk.crm.domain.CustAddress;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the CustAddress entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustAddressRepository extends MongoRepository<CustAddress, String> {

}
