package com.hthk.crm.service;

import com.hthk.crm.domain.GroupEndReason;
import com.hthk.crm.repository.GroupEndReasonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GroupEndReason}.
 */
@Service
public class GroupEndReasonService {

    private final Logger log = LoggerFactory.getLogger(GroupEndReasonService.class);

    private final GroupEndReasonRepository groupEndReasonRepository;

    public GroupEndReasonService(GroupEndReasonRepository groupEndReasonRepository) {
        this.groupEndReasonRepository = groupEndReasonRepository;
    }

    /**
     * Save a groupEndReason.
     *
     * @param groupEndReason the entity to save.
     * @return the persisted entity.
     */
    public GroupEndReason save(GroupEndReason groupEndReason) {
        log.debug("Request to save GroupEndReason : {}", groupEndReason);
        return groupEndReasonRepository.save(groupEndReason);
    }

    /**
     * Get all the groupEndReasons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<GroupEndReason> findAll(Pageable pageable) {
        log.debug("Request to get all GroupEndReasons");
        return groupEndReasonRepository.findAll(pageable);
    }

    /**
     * Get one groupEndReason by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<GroupEndReason> findOne(String id) {
        log.debug("Request to get GroupEndReason : {}", id);
        return groupEndReasonRepository.findById(id);
    }

    /**
     * Delete the groupEndReason by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete GroupEndReason : {}", id);
        groupEndReasonRepository.deleteById(id);
    }
}
