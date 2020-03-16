package com.hthk.crm.repository;

import com.hthk.crm.domain.CustDocument;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the CustDocument entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustDocumentRepository extends MongoRepository<CustDocument, String> {

}
