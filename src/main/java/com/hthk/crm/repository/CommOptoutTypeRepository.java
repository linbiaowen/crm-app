package com.hthk.crm.repository;

import com.hthk.crm.domain.CommOptoutType;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the CommOptoutType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommOptoutTypeRepository extends MongoRepository<CommOptoutType, String> {

}
