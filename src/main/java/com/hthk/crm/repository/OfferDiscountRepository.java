package com.hthk.crm.repository;

import com.hthk.crm.domain.OfferDiscount;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the OfferDiscount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OfferDiscountRepository extends MongoRepository<OfferDiscount, String> {

}
