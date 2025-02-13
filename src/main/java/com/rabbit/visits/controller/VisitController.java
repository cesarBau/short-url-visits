package com.rabbit.visits.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rabbit.visits.entity.Visit;
import com.rabbit.visits.model.VisitDto;
import com.rabbit.visits.service.IVisit;

import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/visit")
@Log4j2
public class VisitController {

    @Autowired
    private IVisit iVisit;

    public VisitController(IVisit iVisit) {
        this.iVisit = iVisit;
    }

    @GetMapping("/{hash}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<VisitDto> getVisitsByHash(@PathVariable String hash) {
        log.info("Consume controller getVisitsByHash");
        log.info("Search hash: " + hash);
        List<Visit> data = iVisit.getVisitsByHash(hash);
        List<VisitDto> response = new ArrayList<>();
        for (Visit value : data) {
            response.add(new VisitDto(value.getId(), value.getHash(), value.getConsume().toString(),
                    value.getStatusUrl().getStatus()));
        }
        return response;
    }

    @DeleteMapping("/{hash}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void deleteByHash(@PathVariable String hash) {
        log.info("Consume controller deleteByHash");
        log.info("Delete hash: " + hash);
        iVisit.deleteByHash(hash);
    }

}
