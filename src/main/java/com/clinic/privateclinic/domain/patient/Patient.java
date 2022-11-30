package com.clinic.privateclinic.domain.patient;

import com.clinic.privateclinic.domain.enums.Sex;
import com.clinic.privateclinic.domain.enums.Vocation;
import lombok.Data;

@Data
public class Patient {
    private String reasonComingToClinic;
    private String name;
    private String surname;
    private Sex sex;
    private Vocation vocation;
    private int age;
}
