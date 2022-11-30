package com.clinic.privateclinic.restapi.client;

import com.clinic.privateclinic.domain.patient.Patient;
import com.clinic.privateclinic.restapi.config.EndpointConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class RestApiClient {
    private final String baseUrl;
    private final RestTemplate restTemplate;
    private final EndpointConfig config;

    RestApiClient(final RestTemplate restTemplate, final EndpointConfig config) {
        this.baseUrl = config.getBaseUrl();
        this.restTemplate = restTemplate;
        this.config = config;
    }

    public List<Patient> getAllPatients(){
        URI url = UriComponentsBuilder.fromHttpUrl(baseUrl+config.getAllPatientEndpoint()).build().encode().toUri();
        try {
            Patient[] response = restTemplate.getForObject(url,Patient[].class);
            Patient[] answer = Optional.ofNullable(response).orElse(new Patient[0]);
            return Arrays.asList(answer);
        } catch (NullPointerException e){
            return new ArrayList<>();
        }
    }
}
