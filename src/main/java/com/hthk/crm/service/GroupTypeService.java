package com.hthk.crm.service;

import com.hthk.crm.domain.GroupType;
import com.hthk.crm.repository.GroupTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GroupType}.
 */
@Service
public class GroupTypeService {

    private final Logger log = LoggerFactory.getLogger(GroupTypeService.class);

    private final GroupTypeRepository groupTypeRepository;

    public GroupTypeService(GroupTypeRepository groupTypeRepository) {
        this.groupTypeRepository = groupTypeRepository;
    }

    /**
     * Save a groupType.
     *
     * @param groupType the entity to save.
     * @return the persisted entity.
     */
    public GroupType save(GroupType groupType) {
        log.debug("Request to save GroupType : {}", groupType);
        return groupTypeRepository.save(groupType);
    }

    /**
     * Get all the groupTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<GroupType> findAll(Pageable pageable) {
        log.debug("Request to get all GroupTypes");
        return groupTypeRepository.findAll(pageable);
    }

    /**
     * Get one groupType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<GroupType> findOne(String id) {
        log.debug("Request to get GroupType : {}", id);
        return groupTypeRepository.findById(id);
    }

    /**
     * Delete the groupType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete GroupType : {}", id);
        groupTypeRepository.deleteById(id);
    }
}
