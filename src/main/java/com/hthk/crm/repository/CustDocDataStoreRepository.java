package com.hthk.crm.repository;

import com.hthk.crm.domain.CustDocDataStore;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the CustDocDataStore entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustDocDataStoreRepository extends MongoRepository<CustDocDataStore, String> {

}
