package com.hthk.crm.repository;

import com.hthk.crm.domain.ProductAttributes;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ProductAttributes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductAttributesRepository extends MongoRepository<ProductAttributes, String> {

}
