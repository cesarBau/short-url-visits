package com.rabbit.visits.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rabbit.visits.entity.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long> {

}
