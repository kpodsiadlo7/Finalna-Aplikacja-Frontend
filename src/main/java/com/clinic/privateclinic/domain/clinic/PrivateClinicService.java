package com.clinic.privateclinic.domain.clinic;

import com.clinic.privateclinic.domain.grade.Grade;

import java.util.ArrayList;
import java.util.List;

public class PrivateClinicService {
    private static PrivateClinicService privateClinicService;

    public static PrivateClinicService getInstance(){
        if (privateClinicService == null)
            privateClinicService = new PrivateClinicService();

        return privateClinicService;
    }

    private List<PrivateClinic> privateClinicList = new ArrayList<>();

    public void setPrivateClinicList(final List<PrivateClinic> privateClinicList) {
        this.privateClinicList = privateClinicList;
    }

    public List<Grade> getGradesList() {
        List<Grade> grades = new ArrayList<>();
        for (PrivateClinic grade: privateClinicList){
            grades.addAll(grade.getGradeList());
        }
        return grades;
    }
}
