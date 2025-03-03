package com.rabbit.visits.repository;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.rabbit.visits.entity.StatusUrl;
import com.rabbit.visits.entity.Visit;
import org.springframework.beans.factory.annotation.Autowired;

@ActiveProfiles("test")
@DataJpaTest
public class VisitRepositoryTest {

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Visit visit;

    @BeforeEach
    public void setUp() {
        visit = new Visit();
        visit.setId(0L);
        visit.setHash("hash");
        visit.setConsume(LocalDateTime.now());
        visit.setStatusUrl(new StatusUrl());
        testEntityManager.persist(visit);
    }

    @Test
    public void testSaveVisit(){
        Visit visitA = new Visit();
        visitA.setId(1L);
        visitA.setHash("hash");
        visitA.setConsume(LocalDateTime.now());
        visitA.setStatusUrl(new StatusUrl());
        Visit response = visitRepository.save(visit);
        assertNotNull(response);
    }

}
