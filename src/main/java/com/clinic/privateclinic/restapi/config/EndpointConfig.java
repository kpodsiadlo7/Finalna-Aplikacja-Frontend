package com.clinic.privateclinic.restapi.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class EndpointConfig {

    @Value("${api.endpoint.baseurl}")
    private String baseUrl;
    @Value("${api.endpoint.patients}")
    private String allPatientEndpoint;
    @Value("${api.endpoint.staff}")
    private String allStaffEndpoint;
    @Value("${api.endpoint.reservation}")
    private String reservation;
}