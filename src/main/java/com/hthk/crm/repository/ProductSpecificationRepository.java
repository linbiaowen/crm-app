package com.hthk.crm.repository;

import com.hthk.crm.domain.ProductSpecification;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ProductSpecification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductSpecificationRepository extends MongoRepository<ProductSpecification, String> {

    @Query("{'product_spec_id': ?0}")
	ProductSpecification findByProdutSpecId(long parseLong);

}
