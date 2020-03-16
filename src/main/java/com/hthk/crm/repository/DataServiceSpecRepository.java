package com.hthk.crm.repository;

import com.hthk.crm.domain.DataServiceSpec;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the DataServiceSpec entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DataServiceSpecRepository extends MongoRepository<DataServiceSpec, String> {

}
