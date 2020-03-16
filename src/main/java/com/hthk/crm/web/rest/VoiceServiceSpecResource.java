package com.hthk.crm.web.rest;

import com.hthk.crm.domain.VoiceServiceSpec;
import com.hthk.crm.service.VoiceServiceSpecService;
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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.hthk.crm.domain.VoiceServiceSpec}.
 */
@RestController
@RequestMapping("/api")
public class VoiceServiceSpecResource {

    private final Logger log = LoggerFactory.getLogger(VoiceServiceSpecResource.class);

    private static final String ENTITY_NAME = "voiceServiceSpec";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VoiceServiceSpecService voiceServiceSpecService;

    public VoiceServiceSpecResource(VoiceServiceSpecService voiceServiceSpecService) {
        this.voiceServiceSpecService = voiceServiceSpecService;
    }

    /**
     * {@code POST  /voice-service-specs} : Create a new voiceServiceSpec.
     *
     * @param voiceServiceSpec the voiceServiceSpec to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new voiceServiceSpec, or with status {@code 400 (Bad Request)} if the voiceServiceSpec has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/voice-service-specs")
    public ResponseEntity<VoiceServiceSpec> createVoiceServiceSpec(@Valid @RequestBody VoiceServiceSpec voiceServiceSpec) throws URISyntaxException {
        log.debug("REST request to save VoiceServiceSpec : {}", voiceServiceSpec);
        if (voiceServiceSpec.getId() != null) {
            throw new BadRequestAlertException("A new voiceServiceSpec cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VoiceServiceSpec result = voiceServiceSpecService.save(voiceServiceSpec);
        return ResponseEntity.created(new URI("/api/voice-service-specs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /voice-service-specs} : Updates an existing voiceServiceSpec.
     *
     * @param voiceServiceSpec the voiceServiceSpec to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated voiceServiceSpec,
     * or with status {@code 400 (Bad Request)} if the voiceServiceSpec is not valid,
     * or with status {@code 500 (Internal Server Error)} if the voiceServiceSpec couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/voice-service-specs")
    public ResponseEntity<VoiceServiceSpec> updateVoiceServiceSpec(@Valid @RequestBody VoiceServiceSpec voiceServiceSpec) throws URISyntaxException {
        log.debug("REST request to update VoiceServiceSpec : {}", voiceServiceSpec);
        if (voiceServiceSpec.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VoiceServiceSpec result = voiceServiceSpecService.save(voiceServiceSpec);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, voiceServiceSpec.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /voice-service-specs} : get all the voiceServiceSpecs.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of voiceServiceSpecs in body.
     */
    @GetMapping("/voice-service-specs")
    public ResponseEntity<List<VoiceServiceSpec>> getAllVoiceServiceSpecs(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("cfsservices-is-null".equals(filter)) {
            log.debug("REST request to get all VoiceServiceSpecs where cfsServices is null");
            return new ResponseEntity<>(voiceServiceSpecService.findAllWhereCfsServicesIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of VoiceServiceSpecs");
        Page<VoiceServiceSpec> page = voiceServiceSpecService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /voice-service-specs/:id} : get the "id" voiceServiceSpec.
     *
     * @param id the id of the voiceServiceSpec to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the voiceServiceSpec, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/voice-service-specs/{id}")
    public ResponseEntity<VoiceServiceSpec> getVoiceServiceSpec(@PathVariable String id) {
        log.debug("REST request to get VoiceServiceSpec : {}", id);
        Optional<VoiceServiceSpec> voiceServiceSpec = voiceServiceSpecService.findOne(id);
        return ResponseUtil.wrapOrNotFound(voiceServiceSpec);
    }

    /**
     * {@code DELETE  /voice-service-specs/:id} : delete the "id" voiceServiceSpec.
     *
     * @param id the id of the voiceServiceSpec to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/voice-service-specs/{id}")
    public ResponseEntity<Void> deleteVoiceServiceSpec(@PathVariable String id) {
        log.debug("REST request to delete VoiceServiceSpec : {}", id);
        voiceServiceSpecService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
