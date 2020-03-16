package com.hthk.crm.web.rest;

import com.hthk.crm.domain.GroupMembers;
import com.hthk.crm.service.GroupMembersService;
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
 * REST controller for managing {@link com.hthk.crm.domain.GroupMembers}.
 */
@RestController
@RequestMapping("/api")
public class GroupMembersResource {

    private final Logger log = LoggerFactory.getLogger(GroupMembersResource.class);

    private static final String ENTITY_NAME = "groupMembers";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GroupMembersService groupMembersService;

    public GroupMembersResource(GroupMembersService groupMembersService) {
        this.groupMembersService = groupMembersService;
    }

    /**
     * {@code POST  /group-members} : Create a new groupMembers.
     *
     * @param groupMembers the groupMembers to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new groupMembers, or with status {@code 400 (Bad Request)} if the groupMembers has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/group-members")
    public ResponseEntity<GroupMembers> createGroupMembers(@Valid @RequestBody GroupMembers groupMembers) throws URISyntaxException {
        log.debug("REST request to save GroupMembers : {}", groupMembers);
        if (groupMembers.getId() != null) {
            throw new BadRequestAlertException("A new groupMembers cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GroupMembers result = groupMembersService.save(groupMembers);
        return ResponseEntity.created(new URI("/api/group-members/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /group-members} : Updates an existing groupMembers.
     *
     * @param groupMembers the groupMembers to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated groupMembers,
     * or with status {@code 400 (Bad Request)} if the groupMembers is not valid,
     * or with status {@code 500 (Internal Server Error)} if the groupMembers couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/group-members")
    public ResponseEntity<GroupMembers> updateGroupMembers(@Valid @RequestBody GroupMembers groupMembers) throws URISyntaxException {
        log.debug("REST request to update GroupMembers : {}", groupMembers);
        if (groupMembers.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GroupMembers result = groupMembersService.save(groupMembers);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, groupMembers.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /group-members} : get all the groupMembers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of groupMembers in body.
     */
    @GetMapping("/group-members")
    public ResponseEntity<List<GroupMembers>> getAllGroupMembers(Pageable pageable) {
        log.debug("REST request to get a page of GroupMembers");
        Page<GroupMembers> page = groupMembersService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /group-members/:id} : get the "id" groupMembers.
     *
     * @param id the id of the groupMembers to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the groupMembers, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/group-members/{id}")
    public ResponseEntity<GroupMembers> getGroupMembers(@PathVariable String id) {
        log.debug("REST request to get GroupMembers : {}", id);
        Optional<GroupMembers> groupMembers = groupMembersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(groupMembers);
    }

    /**
     * {@code DELETE  /group-members/:id} : delete the "id" groupMembers.
     *
     * @param id the id of the groupMembers to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/group-members/{id}")
    public ResponseEntity<Void> deleteGroupMembers(@PathVariable String id) {
        log.debug("REST request to delete GroupMembers : {}", id);
        groupMembersService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
