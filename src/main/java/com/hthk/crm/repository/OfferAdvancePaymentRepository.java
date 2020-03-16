package com.hthk.crm.repository;

import com.hthk.crm.domain.OfferAdvancePayment;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the OfferAdvancePayment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OfferAdvancePaymentRepository extends MongoRepository<OfferAdvancePayment, String> {

    @Query("{ advance_payment_id : ?0 }")
	OfferAdvancePayment findByOfferAdvancePaymentId(Long i);

}
