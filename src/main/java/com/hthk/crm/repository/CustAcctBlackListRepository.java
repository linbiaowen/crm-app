package com.hthk.crm.repository;

import com.hthk.crm.domain.CustAcctBlackList;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the CustAcctBlackList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustAcctBlackListRepository extends MongoRepository<CustAcctBlackList, String> {

}
