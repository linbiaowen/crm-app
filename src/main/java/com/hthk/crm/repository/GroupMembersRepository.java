package com.hthk.crm.repository;

import com.hthk.crm.domain.GroupMembers;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the GroupMembers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroupMembersRepository extends MongoRepository<GroupMembers, String> {

}
