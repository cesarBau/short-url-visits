package com.rabbit.visits.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VisitDto {

    private Long id;
    private String hash;
    @JsonProperty("date_consume")
    private String dateConsume;
    @JsonProperty("status_url")
    private String statusUrl;

    public VisitDto() {
    }

    public VisitDto(Long id, String hash, String dateConsume, String statusUrl) {
        this.id = id;
        this.hash = hash;
        this.dateConsume = dateConsume;
        this.statusUrl = statusUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getDateConsume() {
        return dateConsume;
    }

    public void setDateConsume(String dateConsume) {
        this.dateConsume = dateConsume;
    }

    public String getStatusUrl() {
        return statusUrl;
    }

    public void setStatusUrl(String statusUrl) {
        this.statusUrl = statusUrl;
    }

}
