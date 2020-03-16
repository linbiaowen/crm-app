package com.hthk.crm.service;

import com.hthk.crm.domain.CfsServiceSpec;
import com.hthk.crm.repository.CfsServiceSpecRepository;
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
 * Service Implementation for managing {@link CfsServiceSpec}.
 */
@Service
public class CfsServiceSpecService {

    private final Logger log = LoggerFactory.getLogger(CfsServiceSpecService.class);

    private final CfsServiceSpecRepository cfsServiceSpecRepository;

    public CfsServiceSpecService(CfsServiceSpecRepository cfsServiceSpecRepository) {
        this.cfsServiceSpecRepository = cfsServiceSpecRepository;
    }

    /**
     * Save a cfsServiceSpec.
     *
     * @param cfsServiceSpec the entity to save.
     * @return the persisted entity.
     */
    public CfsServiceSpec save(CfsServiceSpec cfsServiceSpec) {
        log.debug("Request to save CfsServiceSpec : {}", cfsServiceSpec);
        return cfsServiceSpecRepository.save(cfsServiceSpec);
    }

    /**
     * Get all the cfsServiceSpecs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<CfsServiceSpec> findAll(Pageable pageable) {
        log.debug("Request to get all CfsServiceSpecs");
        return cfsServiceSpecRepository.findAll(pageable);
    }


    /**
     *  Get all the cfsServiceSpecs where CfsServices is {@code null}.
     *  @return the list of entities.
     */
    public List<CfsServiceSpec> findAllWhereCfsServicesIsNull() {
        log.debug("Request to get all cfsServiceSpecs where CfsServices is null");
        return StreamSupport
            .stream(cfsServiceSpecRepository.findAll().spliterator(), false)
            .filter(cfsServiceSpec -> cfsServiceSpec.getCfsServices() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one cfsServiceSpec by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<CfsServiceSpec> findOne(String id) {
        log.debug("Request to get CfsServiceSpec : {}", id);
        return cfsServiceSpecRepository.findById(id);
    }

    /**
     * Delete the cfsServiceSpec by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete CfsServiceSpec : {}", id);
        cfsServiceSpecRepository.deleteById(id);
    }
}
