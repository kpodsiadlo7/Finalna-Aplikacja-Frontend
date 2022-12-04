package com.clinic.privateclinic.domain.clinic;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class PrivateClinic {
    private int id;
    private String clinicName;
    private String city;
    private String street;
    private int staffQuantity;
    private int hospitalizedQuantity;
    private double grade;
    private List<ClinicGrade> gradesList = new ArrayList<>();
}
