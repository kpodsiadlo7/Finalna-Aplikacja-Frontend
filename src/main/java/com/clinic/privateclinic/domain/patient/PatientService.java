package com.clinic.privateclinic.domain.patient;

import com.clinic.privateclinic.domain.staff.Staff;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PatientService {
    private List<Patient> patientList;
    private static PatientService service;

    public static PatientService getInstance(){
        if (service == null){
            service = new PatientService();
        }
        return service;
    }

    public void save(final Patient patient) {
        this.patientList.add(patient);
    }
    public void delete(final Patient patient) {
        this.patientList.remove(patient);
    }
}
