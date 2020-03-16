package com.hthk.crm.repository;

import com.hthk.crm.domain.ResourceSpecification;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ResourceSpecification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResourceSpecificationRepository extends MongoRepository<ResourceSpecification, String> {

}
