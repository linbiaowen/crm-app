package com.hthk.crm.repository;

import com.hthk.crm.domain.SalesChannelMaster;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the SalesChannelMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesChannelMasterRepository extends MongoRepository<SalesChannelMaster, String> {

}
