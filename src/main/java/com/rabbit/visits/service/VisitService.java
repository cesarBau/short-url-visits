package com.rabbit.visits.service;

import org.springframework.stereotype.Service;

import com.rabbit.visits.entity.StatusUrl;
import com.rabbit.visits.entity.Visit;
import com.rabbit.visits.repository.VisitRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
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

}
