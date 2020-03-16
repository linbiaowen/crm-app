package com.hthk.crm.repository;

import com.hthk.crm.domain.ModelCategory;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ModelCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ModelCategoryRepository extends MongoRepository<ModelCategory, String> {

}
