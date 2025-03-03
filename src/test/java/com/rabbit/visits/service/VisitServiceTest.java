package com.rabbit.visits.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.http.HttpStatus.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import com.rabbit.visits.entity.StatusUrl;
import com.rabbit.visits.entity.Visit;
import com.rabbit.visits.repository.VisitRepository;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VisitServiceTest {

    @Mock
    private VisitRepository visitRepository;

    @InjectMocks
    private VisitService visitService;

    private Visit visit;

    private String hash = "hash";

    @BeforeEach
    public void setUp() {
        visit = new Visit();
        visit.setId(0L);
        visit.setHash("hash");
        visit.setConsume(LocalDateTime.now());
        visit.setStatusUrl(new StatusUrl());
    }

    @DisplayName("Testo to create Visit")
    @Order(1)
    @Test
    void testSaveVisit() {
        given(visitRepository.save(visit)).willReturn(visit);
        visitService.createVisit(visit);
        verify(visitRepository, times(1)).save(visit);
    }

    @DisplayName("Test to get Visit")
    @Order(2)
    @Test
    void testGetVisitsByHash() {
        List<Visit> visits = new ArrayList<>();
        visits.add(visit);
        given(visitRepository.findByHash(hash)).willReturn(visits);
        List<Visit> result = visitService.getVisitsByHash(hash);
        assertNotNull(result);
    }

    @DisplayName("Test to get Visit is empy")
    @Order(3)
    @Test
    void testGetVisitsByHashNotFound() {
        List<Visit> visits = new ArrayList<>();
        given(visitRepository.findByHash(hash)).willReturn(visits);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> visitService.getVisitsByHash(hash));
        assertEquals(NOT_FOUND, exception.getStatusCode());
    }

    @DisplayName("Test to delete Visit")
    @Order(4)
    @Test
    void testDeleteVisit() {
        List<Visit> visits = new ArrayList<>();
        visits.add(visit);
        given(visitRepository.findByHash(hash)).willReturn(visits);
        visitService.deleteByHash(hash);
        verify(visitRepository, times(1)).deleteByHash(hash);
    }

    @DisplayName("Test to delete Visit not found")
    @Order(5)
    @Test
    void testDeleteVisitNotFound() {
        List<Visit> visits = new ArrayList<>();
        given(visitRepository.findByHash(hash)).willReturn(visits);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> visitService.deleteByHash(hash));
        assertEquals(NOT_FOUND, exception.getStatusCode());
    }

    @DisplayName("Test change status Url")
    @Order(6)
    @Test
    void testChangeStatusUrlActive() {
        StatusUrl statusUrl = visitService.generateStatusUrl("ACTIVE");
        assertEquals("ACTIVE", statusUrl.getStatus());
    }

    @DisplayName("Test change status Url")
    @Order(7)
    @Test
    void testChangeStatusUrlInactive() {
        StatusUrl statusUrl = visitService.generateStatusUrl("INACTIVE");
        assertEquals("INACTIVE", statusUrl.getStatus());
    }

}
