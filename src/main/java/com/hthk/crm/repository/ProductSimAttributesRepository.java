package com.hthk.crm.repository;

import com.hthk.crm.domain.ProductSimAttributes;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ProductSimAttributes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductSimAttributesRepository extends MongoRepository<ProductSimAttributes, String> {

}
