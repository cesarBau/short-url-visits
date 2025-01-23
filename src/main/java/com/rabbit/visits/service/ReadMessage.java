package com.rabbit.visits.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class ReadMessage {

    @Autowired
    private IVisit iVisit;

    @RabbitListener(queues = "visits")
    public void processMessage(String content) {
        log.info("Consume service processMessage");
        log.info(content);
        iVisit.createVisit(content);
    }

}
