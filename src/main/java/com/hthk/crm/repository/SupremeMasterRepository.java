package com.hthk.crm.repository;

import com.hthk.crm.domain.SupremeMaster;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the SupremeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SupremeMasterRepository extends MongoRepository<SupremeMaster, String> {

}
