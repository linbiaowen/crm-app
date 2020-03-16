package com.hthk.crm.repository;

import com.hthk.crm.domain.BlackListMaster;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the BlackListMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BlackListMasterRepository extends MongoRepository<BlackListMaster, String> {

}
