package com.rabbit.visits.service;

import java.util.List;

import com.rabbit.visits.entity.Visit;

public interface IVisit {

    void createVisit(Visit visit);

    List<Visit> getVisitsByHash(String hash);

    void deleteByHash(String hash);

}
