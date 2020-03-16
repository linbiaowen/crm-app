package com.hthk.crm.web.rest;

import com.hthk.crm.domain.GroupEndReason;
import com.hthk.crm.service.GroupEndReasonService;
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
 * REST controller for managing {@link com.hthk.crm.domain.GroupEndReason}.
 */
@RestController
@RequestMapping("/api")
public class GroupEndReasonResource {

    private final Logger log = LoggerFactory.getLogger(GroupEndReasonResource.class);

    private static final String ENTITY_NAME = "groupEndReason";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GroupEndReasonService groupEndReasonService;

    public GroupEndReasonResource(GroupEndReasonService groupEndReasonService) {
        this.groupEndReasonService = groupEndReasonService;
    }

    /**
     * {@code POST  /group-end-reasons} : Create a new groupEndReason.
     *
     * @param groupEndReason the groupEndReason to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new groupEndReason, or with status {@code 400 (Bad Request)} if the groupEndReason has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/group-end-reasons")
    public ResponseEntity<GroupEndReason> createGroupEndReason(@Valid @RequestBody GroupEndReason groupEndReason) throws URISyntaxException {
        log.debug("REST request to save GroupEndReason : {}", groupEndReason);
        if (groupEndReason.getId() != null) {
            throw new BadRequestAlertException("A new groupEndReason cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GroupEndReason result = groupEndReasonService.save(groupEndReason);
        return ResponseEntity.created(new URI("/api/group-end-reasons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /group-end-reasons} : Updates an existing groupEndReason.
     *
     * @param groupEndReason the groupEndReason to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated groupEndReason,
     * or with status {@code 400 (Bad Request)} if the groupEndReason is not valid,
     * or with status {@code 500 (Internal Server Error)} if the groupEndReason couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/group-end-reasons")
    public ResponseEntity<GroupEndReason> updateGroupEndReason(@Valid @RequestBody GroupEndReason groupEndReason) throws URISyntaxException {
        log.debug("REST request to update GroupEndReason : {}", groupEndReason);
        if (groupEndReason.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GroupEndReason result = groupEndReasonService.save(groupEndReason);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, groupEndReason.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /group-end-reasons} : get all the groupEndReasons.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of groupEndReasons in body.
     */
    @GetMapping("/group-end-reasons")
    public ResponseEntity<List<GroupEndReason>> getAllGroupEndReasons(Pageable pageable) {
        log.debug("REST request to get a page of GroupEndReasons");
        Page<GroupEndReason> page = groupEndReasonService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /group-end-reasons/:id} : get the "id" groupEndReason.
     *
     * @param id the id of the groupEndReason to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the groupEndReason, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/group-end-reasons/{id}")
    public ResponseEntity<GroupEndReason> getGroupEndReason(@PathVariable String id) {
        log.debug("REST request to get GroupEndReason : {}", id);
        Optional<GroupEndReason> groupEndReason = groupEndReasonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(groupEndReason);
    }

    /**
     * {@code DELETE  /group-end-reasons/:id} : delete the "id" groupEndReason.
     *
     * @param id the id of the groupEndReason to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/group-end-reasons/{id}")
    public ResponseEntity<Void> deleteGroupEndReason(@PathVariable String id) {
        log.debug("REST request to delete GroupEndReason : {}", id);
        groupEndReasonService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
