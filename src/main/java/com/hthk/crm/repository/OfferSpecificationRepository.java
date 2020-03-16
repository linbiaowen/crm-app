package com.hthk.crm.repository;

import com.hthk.crm.domain.OfferSpecification;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the OfferSpecification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OfferSpecificationRepository extends MongoRepository<OfferSpecification, String> {

}
