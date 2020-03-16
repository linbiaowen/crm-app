package com.hthk.crm.repository;

import com.hthk.crm.domain.CommMediaType;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the CommMediaType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommMediaTypeRepository extends MongoRepository<CommMediaType, String> {

}
