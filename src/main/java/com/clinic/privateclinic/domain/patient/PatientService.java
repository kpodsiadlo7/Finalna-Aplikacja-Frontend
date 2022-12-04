package com.clinic.privateclinic.domain.patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Set<Patient> findByName(final String name) {
        return patientList.stream().filter(patient -> patient.getName().contains(name)).collect(Collectors.toSet());
    }
}
