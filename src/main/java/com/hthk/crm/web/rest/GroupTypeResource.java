package com.hthk.crm.web.rest;

import com.hthk.crm.domain.GroupType;
import com.hthk.crm.service.GroupTypeService;
import com.hthk.crm.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.hthk.crm.domain.GroupType}.
 */
@RestController
@RequestMapping("/api")
public class GroupTypeResource {

    private final Logger log = LoggerFactory.getLogger(GroupTypeResource.class);

    private static final String ENTITY_NAME = "groupType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GroupTypeService groupTypeService;

    public GroupTypeResource(GroupTypeService groupTypeService) {
        this.groupTypeService = groupTypeService;
    }

    /**
     * {@code POST  /group-types} : Create a new groupType.
     *
     * @param groupType the groupType to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new groupType, or with status {@code 400 (Bad Request)} if the groupType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/group-types")
    public ResponseEntity<GroupType> createGroupType(@Valid @RequestBody GroupType groupType) throws URISyntaxException {
        log.debug("REST request to save GroupType : {}", groupType);
        if (groupType.getId() != null) {
            throw new BadRequestAlertException("A new groupType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GroupType result = groupTypeService.save(groupType);
        return ResponseEntity.created(new URI("/api/group-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /group-types} : Updates an existing groupType.
     *
     * @param groupType the groupType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated groupType,
     * or with status {@code 400 (Bad Request)} if the groupType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the groupType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/group-types")
    public ResponseEntity<GroupType> updateGroupType(@Valid @RequestBody GroupType groupType) throws URISyntaxException {
        log.debug("REST request to update GroupType : {}", groupType);
        if (groupType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GroupType result = groupTypeService.save(groupType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, groupType.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /group-types} : get all the groupTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of groupTypes in body.
     */
    @GetMapping("/group-types")
    public ResponseEntity<List<GroupType>> getAllGroupTypes(Pageable pageable) {
        log.debug("REST request to get a page of GroupTypes");
        Page<GroupType> page = groupTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /group-types/:id} : get the "id" groupType.
     *
     * @param id the id of the groupType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the groupType, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/group-types/{id}")
    public ResponseEntity<GroupType> getGroupType(@PathVariable String id) {
        log.debug("REST request to get GroupType : {}", id);
        Optional<GroupType> groupType = groupTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(groupType);
    }

    /**
     * {@code DELETE  /group-types/:id} : delete the "id" groupType.
     *
     * @param id the id of the groupType to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/group-types/{id}")
    public ResponseEntity<Void> deleteGroupType(@PathVariable String id) {
        log.debug("REST request to delete GroupType : {}", id);
        groupTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
