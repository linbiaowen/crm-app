package com.hthk.crm.repository;

import com.hthk.crm.domain.BillCycle;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the BillCycle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BillCycleRepository extends MongoRepository<BillCycle, String> {

}
