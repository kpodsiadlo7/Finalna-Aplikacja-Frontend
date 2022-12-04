package com.clinic.privateclinic.domain.patient;

import com.clinic.privateclinic.domain.enums.Sex;
import com.clinic.privateclinic.domain.enums.Vocation;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Patient {
    private long id;
    private String reasonComingToClinic;
    private String name;
    private String surname;
    private Sex sex;
    private Vocation vocation;
    private int age;
}
