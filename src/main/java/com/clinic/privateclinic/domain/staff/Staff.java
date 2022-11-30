package com.clinic.privateclinic.domain.staff;

import com.clinic.privateclinic.domain.enums.BaseProfession;
import com.clinic.privateclinic.domain.enums.Sex;
import com.clinic.privateclinic.domain.enums.Vocation;
import lombok.*;

@Data
public class Staff {
    private BaseProfession baseProfession;
    private int quantityPatientToHelp;
    private double grade;
    private int patientQuantity;
    private String name;
    private String surname;
    private Sex sex;
    private Vocation vocation;
    private int age;
}
