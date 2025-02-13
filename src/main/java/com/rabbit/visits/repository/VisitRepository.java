package com.rabbit.visits.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rabbit.visits.entity.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    List<Visit> findByHash(String hash);

    void deleteByHash(String hash);

}
