package com.rabbit.visits.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.rabbit.visits.entity.StatusUrl;
import com.rabbit.visits.entity.Visit;
import com.rabbit.visits.service.IVisit;

@WebMvcTest(VisitController.class)
class VisitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IVisit iVisit;

    private String hash = "010101010101";

    @Test
    void testGetVisitsByHash() {
        ResultActions result;
        StatusUrl statusUrl = new StatusUrl();
        List<Visit> response = new ArrayList<>();
        response.add(new Visit(1L, hash, LocalDateTime.now(), statusUrl));
        when(iVisit.getVisitsByHash("10101010")).thenReturn(response);
        try {
            result = mockMvc.perform(get("/visit/{hash}", hash));
            result.andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteByHash() {
        doNothing().when(iVisit).deleteByHash("10101010");
        ResultActions result;
        try {
            result = mockMvc.perform(delete("/visit/{hash}", hash));
            result.andDo(print())
                    .andExpect(status().isNoContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
