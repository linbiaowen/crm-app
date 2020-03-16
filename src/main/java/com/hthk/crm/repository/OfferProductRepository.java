package com.hthk.crm.repository;

import com.hthk.crm.domain.OfferProduct;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the OfferProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OfferProductRepository extends MongoRepository<OfferProduct, String> {

}
