package com.hthk.crm.repository;

import com.hthk.crm.domain.CfsServiceSpec;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the CfsServiceSpec entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CfsServiceSpecRepository extends MongoRepository<CfsServiceSpec, String> {

}
