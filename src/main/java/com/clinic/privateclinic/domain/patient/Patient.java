package com.clinic.privateclinic.domain.patient;

import com.clinic.privateclinic.domain.enums.Sex;
import com.clinic.privateclinic.domain.enums.Vocation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Patient {
    @JsonProperty("reasonComingToClinic")
    private String reasonComingToClinic;
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("sex")
    private Sex sex;
    @JsonProperty("vocation")
    private Vocation vocation;
    @JsonProperty("age")
    private int age;

    public Patient(final String name, final String surname, final Sex sex, final Vocation vocation, final int age, final String reasonComingToClinic) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.vocation = vocation;
        this.age = age;
        this.reasonComingToClinic = reasonComingToClinic;
    }

    public Patient() {
    }
}
