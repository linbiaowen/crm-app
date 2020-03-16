package com.hthk.crm.service;

import com.hthk.crm.domain.VoiceServiceSpec;
import com.hthk.crm.repository.VoiceServiceSpecRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link VoiceServiceSpec}.
 */
@Service
public class VoiceServiceSpecService {

    private final Logger log = LoggerFactory.getLogger(VoiceServiceSpecService.class);

    private final VoiceServiceSpecRepository voiceServiceSpecRepository;

    public VoiceServiceSpecService(VoiceServiceSpecRepository voiceServiceSpecRepository) {
        this.voiceServiceSpecRepository = voiceServiceSpecRepository;
    }

    /**
     * Save a voiceServiceSpec.
     *
     * @param voiceServiceSpec the entity to save.
     * @return the persisted entity.
     */
    public VoiceServiceSpec save(VoiceServiceSpec voiceServiceSpec) {
        log.debug("Request to save VoiceServiceSpec : {}", voiceServiceSpec);
        return voiceServiceSpecRepository.save(voiceServiceSpec);
    }

    /**
     * Get all the voiceServiceSpecs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<VoiceServiceSpec> findAll(Pageable pageable) {
        log.debug("Request to get all VoiceServiceSpecs");
        return voiceServiceSpecRepository.findAll(pageable);
    }


    /**
     *  Get all the voiceServiceSpecs where CfsServices is {@code null}.
     *  @return the list of entities.
     */
    public List<VoiceServiceSpec> findAllWhereCfsServicesIsNull() {
        log.debug("Request to get all voiceServiceSpecs where CfsServices is null");
        return StreamSupport
            .stream(voiceServiceSpecRepository.findAll().spliterator(), false)
            .filter(voiceServiceSpec -> voiceServiceSpec.getCfsServices() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one voiceServiceSpec by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<VoiceServiceSpec> findOne(String id) {
        log.debug("Request to get VoiceServiceSpec : {}", id);
        return voiceServiceSpecRepository.findById(id);
    }

    /**
     * Delete the voiceServiceSpec by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete VoiceServiceSpec : {}", id);
        voiceServiceSpecRepository.deleteById(id);
    }
}
