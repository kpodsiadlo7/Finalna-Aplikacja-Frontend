package com.clinic.privateclinic.domain.patient;

import com.clinic.privateclinic.domain.staff.Staff;
import com.clinic.privateclinic.restapi.client.RestApiClient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Component
public class PatientService {
    private List<Patient> patientList;
    private RestApiClient restApiClient;
    private static PatientService service;

    public static PatientService getInstance(){
        if (service == null){
            service = new PatientService();
        }
        return service;
    }

    public List<Patient> getPatientList() {
        return restApiClient.getAllPatients();
    }

    public void save(final Patient patient) {
        this.patientList.add(patient);
    }
    public void delete(final Patient patient) {
        this.patientList.remove(patient);
    }
}
