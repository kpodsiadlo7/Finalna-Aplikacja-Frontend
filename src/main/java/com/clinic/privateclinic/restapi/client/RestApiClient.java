package com.clinic.privateclinic.restapi.client;

import com.clinic.privateclinic.domain.clinic.PrivateClinic;
import com.clinic.privateclinic.domain.enums.Currency;
import com.clinic.privateclinic.domain.enums.Sex;
import com.clinic.privateclinic.domain.enums.Vocation;
import com.clinic.privateclinic.domain.patient.Patient;
import com.clinic.privateclinic.domain.patient.PatientService;
import com.clinic.privateclinic.domain.staff.Staff;
import com.clinic.privateclinic.restapi.config.EndpointConfig;
import com.clinic.privateclinic.view.MainView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.*;

@Component
@RequiredArgsConstructor
public class RestApiClient {
    private final EndpointConfig config;
    private final RestTemplate restTemplate;
    private int clinicId = 0;

    public List<Patient> getAllPatients() {
        URI url = UriComponentsBuilder.fromHttpUrl(config.getBaseUrl() + config.getAllPatientEndpoint()).build().encode().toUri();
        Patient[] patientsResponse = restTemplate.getForObject(url, Patient[].class);
        return Optional.ofNullable(patientsResponse).map(Arrays::asList).orElse(Collections.emptyList());
    }

    public List<PrivateClinic> getAllClinics(){
        URI url = UriComponentsBuilder.fromHttpUrl(config.getBaseUrl() + config.getClinics()).build().encode().toUri();
        PrivateClinic[] patientsResponse = restTemplate.getForObject(url, PrivateClinic[].class);
        return Optional.ofNullable(patientsResponse).map(Arrays::asList).orElse(Collections.emptyList());
    }

    public void setClinicId(final int clinicId) {
        this.clinicId = clinicId;
    }

    public void register(final String name, final String surname, final String age, final Sex sex,
                         final String reasonComingToClinic, final String date, final Currency currency){

        String url = UriComponentsBuilder.fromHttpUrl(config.getBaseUrl() + config.getReservation())
                .queryParam("clinicId",clinicId)
                .queryParam("sex",returnSex(sex))
                .queryParam("reasonComingToClinic", reasonComingToClinic)
                .queryParam("visitDate",date)
                .queryParam("currency",returnCurrency(currency))
                .encode().toUriString();
        var data = new JSONObject() {{
            put("name",name);
            put("surname",surname);
            put("age",age);
        }};

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost request = new HttpPost(URI.create(url));
            StringEntity params = new StringEntity(data.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            httpClient.execute(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private int returnSex(Sex sex){
        int s = 0;
        if (sex == Sex.FEMALE)
            s = 1;
        return s;
    }
    private int returnCurrency(Currency currency){
        int cur = 0;
        switch (currency){
            case EUR:
                cur = 1;
                break;
            case USD:
                cur = 2;
                break;
            case CASH:
                cur = 3;
                break;
        }
        return cur;
    }
}
