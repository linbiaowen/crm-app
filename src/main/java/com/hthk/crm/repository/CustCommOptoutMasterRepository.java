package com.hthk.crm.repository;

import com.hthk.crm.domain.CustCommOptoutMaster;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the CustCommOptoutMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustCommOptoutMasterRepository extends MongoRepository<CustCommOptoutMaster, String> {

}
