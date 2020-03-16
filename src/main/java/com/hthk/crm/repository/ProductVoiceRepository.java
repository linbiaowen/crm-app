package com.hthk.crm.repository;

import com.hthk.crm.domain.ProductVoice;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ProductVoice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductVoiceRepository extends MongoRepository<ProductVoice, String> {

}
