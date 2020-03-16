package com.hthk.crm.repository;

import com.hthk.crm.domain.SimInventory;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the SimInventory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SimInventoryRepository extends MongoRepository<SimInventory, String> {

}
