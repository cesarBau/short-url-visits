package com.rabbit.visits.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.rabbit.visits.entity.StatusUrl;
import com.rabbit.visits.entity.Visit;
import com.rabbit.visits.repository.VisitRepository;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional
public class VisitService implements IVisit {

    private VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public void createVisit(Visit visit) {
        log.info("Consume service createVisit");
        log.info("Create visit");
        visitRepository.save(visit);
    }

    public StatusUrl generateStatusUrl(String status) {
        StatusUrl response;
        if (status.equals("ACTIVE")) {
            response = new StatusUrl((long) 1, "ACTIVE", "The url is active");
        } else {
            response = new StatusUrl();
        }
        return response;
    }

    @Override
    public List<Visit> getVisitsByHash(String hash) {
        log.info("Consume service getVisitsByHash");
        List<Visit> response = visitRepository.findByHash(hash);
        if (response.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "the hash does not exist", null);
        }
        log.info("Total count: " + response.size());
        return response;
    }

    @Override
    public void deleteByHash(String hash) {
        log.info("Consume service deleteByHash");
        Integer count = visitRepository.findByHash(hash).size();
        if (count == 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "the hash does not exist", null);
        }
        log.info("Count: " + count);
        visitRepository.deleteByHash(hash);
    }

}
