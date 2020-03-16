package com.hthk.crm.repository;

import com.hthk.crm.domain.Offer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the Offer entity.
 */
@Repository
public interface OfferRepository extends MongoRepository<Offer, String> {

    @Query("{}")
    Page<Offer> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<Offer> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<Offer> findOneWithEagerRelationships(String id);

}
