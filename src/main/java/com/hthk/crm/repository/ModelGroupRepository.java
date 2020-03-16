package com.hthk.crm.repository;

import com.hthk.crm.domain.ModelGroup;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ModelGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ModelGroupRepository extends MongoRepository<ModelGroup, String> {

}
