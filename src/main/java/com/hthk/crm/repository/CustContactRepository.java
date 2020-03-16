package com.hthk.crm.repository;

import com.hthk.crm.domain.CustContact;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the CustContact entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustContactRepository extends MongoRepository<CustContact, String> {

}
