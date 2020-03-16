package com.hthk.crm.repository;

import com.hthk.crm.domain.ProductData;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ProductData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductDataRepository extends MongoRepository<ProductData, String> {

}
