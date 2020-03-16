package com.hthk.crm.repository;

import com.hthk.crm.domain.ProductBoxType;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ProductBoxType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductBoxTypeRepository extends MongoRepository<ProductBoxType, String> {

}
