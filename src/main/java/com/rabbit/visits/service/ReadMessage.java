package com.rabbit.visits.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbit.visits.entity.Visit;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class ReadMessage {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IVisit iVisit;

    @RabbitListener(queues = "visits")
    public void processMessage(String content) throws JsonProcessingException {
        log.info("Consume service processMessage");
        log.info(content);
        Visit message = objectMapper.readValue(content, Visit.class);
        iVisit.createVisit(message);
    }

}
