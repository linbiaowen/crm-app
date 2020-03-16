package com.hthk.crm.service;

import com.hthk.crm.domain.CustAcctBlackList;
import com.hthk.crm.repository.CustAcctBlackListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CustAcctBlackList}.
 */
@Service
public class CustAcctBlackListService {

    private final Logger log = LoggerFactory.getLogger(CustAcctBlackListService.class);

    private final CustAcctBlackListRepository custAcctBlackListRepository;

    public CustAcctBlackListService(CustAcctBlackListRepository custAcctBlackListRepository) {
        this.custAcctBlackListRepository = custAcctBlackListRepository;
    }

    /**
     * Save a custAcctBlackList.
     *
     * @param custAcctBlackList the entity to save.
     * @return the persisted entity.
     */
    public CustAcctBlackList save(CustAcctBlackList custAcctBlackList) {
        log.debug("Request to save CustAcctBlackList : {}", custAcctBlackList);
        return custAcctBlackListRepository.save(custAcctBlackList);
    }

    /**
     * Get all the custAcctBlackLists.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<CustAcctBlackList> findAll(Pageable pageable) {
        log.debug("Request to get all CustAcctBlackLists");
        return custAcctBlackListRepository.findAll(pageable);
    }

    /**
     * Get one custAcctBlackList by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<CustAcctBlackList> findOne(String id) {
        log.debug("Request to get CustAcctBlackList : {}", id);
        return custAcctBlackListRepository.findById(id);
    }

    /**
     * Delete the custAcctBlackList by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete CustAcctBlackList : {}", id);
        custAcctBlackListRepository.deleteById(id);
    }
}
