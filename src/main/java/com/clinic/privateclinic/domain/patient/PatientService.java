package com.clinic.privateclinic.domain.patient;

import java.util.ArrayList;
import java.util.List;

public class PatientService {

    private static PatientService patientService;
    private List<Patient> patientList = new ArrayList<>();

    public static PatientService getInstance(){
        if (patientService == null)
            patientService = new PatientService();

        return patientService;
    }

    public void setPatientList(final List<Patient> patientList) {
        this.patientList = patientList;
    }
}
