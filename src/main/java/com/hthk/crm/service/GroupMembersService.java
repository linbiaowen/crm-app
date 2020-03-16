package com.hthk.crm.service;

import com.hthk.crm.domain.GroupMembers;
import com.hthk.crm.repository.GroupMembersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GroupMembers}.
 */
@Service
public class GroupMembersService {

    private final Logger log = LoggerFactory.getLogger(GroupMembersService.class);

    private final GroupMembersRepository groupMembersRepository;

    public GroupMembersService(GroupMembersRepository groupMembersRepository) {
        this.groupMembersRepository = groupMembersRepository;
    }

    /**
     * Save a groupMembers.
     *
     * @param groupMembers the entity to save.
     * @return the persisted entity.
     */
    public GroupMembers save(GroupMembers groupMembers) {
        log.debug("Request to save GroupMembers : {}", groupMembers);
        return groupMembersRepository.save(groupMembers);
    }

    /**
     * Get all the groupMembers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<GroupMembers> findAll(Pageable pageable) {
        log.debug("Request to get all GroupMembers");
        return groupMembersRepository.findAll(pageable);
    }

    /**
     * Get one groupMembers by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<GroupMembers> findOne(String id) {
        log.debug("Request to get GroupMembers : {}", id);
        return groupMembersRepository.findById(id);
    }

    /**
     * Delete the groupMembers by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete GroupMembers : {}", id);
        groupMembersRepository.deleteById(id);
    }
}
