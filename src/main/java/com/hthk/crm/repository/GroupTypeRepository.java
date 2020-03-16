package com.hthk.crm.repository;

import com.hthk.crm.domain.GroupType;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the GroupType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroupTypeRepository extends MongoRepository<GroupType, String> {

}
