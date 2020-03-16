package com.hthk.crm.repository;

import com.hthk.crm.domain.OfferPromotion;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the OfferPromotion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OfferPromotionRepository extends MongoRepository<OfferPromotion, String> {

}
