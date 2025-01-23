package com.rabbit.visits.service;

import java.time.LocalDateTime;

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
    public void createVisit(String visit) {
        log.info("Consume service createVisit");
        log.info("Create visit");
        String[] valuesSplit = visit.split("\\|");
        LocalDateTime consumeDate = LocalDateTime.parse(valuesSplit[0]);
        String hash = valuesSplit[1];
        String status = valuesSplit[2];
        Visit values = new Visit();
        values.setHash(hash);
        values.setConsume(consumeDate);
        values.setStatusUrl(generateStatusUrl(status));
        visitRepository.save(values);
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
