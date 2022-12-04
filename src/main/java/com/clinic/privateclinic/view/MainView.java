package com.clinic.privateclinic.view;

import com.clinic.privateclinic.domain.clinic.PrivateClinic;
import com.clinic.privateclinic.domain.clinic.PrivateClinicService;
import com.clinic.privateclinic.domain.enums.Currency;
import com.clinic.privateclinic.domain.enums.Sex;
import com.clinic.privateclinic.domain.patient.Patient;
import com.clinic.privateclinic.domain.patient.PatientService;
import com.clinic.privateclinic.restapi.client.RegisterPatient;
import com.clinic.privateclinic.restapi.client.RestApiClient;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route
public class MainView extends VerticalLayout {
    @Autowired
    private RestApiClient restApiClient;
    private final PatientService patientService = PatientService.getInstance();
    private final PrivateClinicService privateClinicService = PrivateClinicService.getInstance();
    private final Grid<Patient> patientGrid = new Grid<>(Patient.class);
    private final Grid<PrivateClinic> clinicGrid = new Grid<>(PrivateClinic.class);
    private RegisterPatient registerPatient = new RegisterPatient(this);
    private TextField filter = new TextField();

    private final Button buttonPatientRefresh = new Button("Refresh");
    private final Button buttonClinicRefresh = new Button("Refresh");


    public MainView() {
        filter.setPlaceholder("SEARCH BY NAME");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(update -> findByName());
        patientGrid.setColumns("id","name","surname","sex","vocation","age","reasonComingToClinic");
        add(filter,patientGrid,buttonPatientRefresh);
        buttonPatientRefresh.addClickListener(update -> refreshPatient());

        HorizontalLayout createPatient = new HorizontalLayout(patientGrid,registerPatient);
        createPatient.setSizeFull();
        patientGrid.setSizeFull();
        add(createPatient);

        clinicGrid.setColumns("id","clinicName","city","street","staffQuantity","hospitalizedQuantity","grade");
        add(clinicGrid,buttonClinicRefresh);
        buttonClinicRefresh.addClickListener(update -> refreshClinic());
        try {
        clinicGrid.asSingleSelect().addValueChangeListener(event -> setClinicId(event.getValue().getId()));
        }catch (Exception e){
            refreshClinic();
        }

        setSizeFull();
    }

    void setClinicId(final int clinicId) {
        restApiClient.setClinicId(clinicId);
    }

    public void refreshClinic() {
        List<PrivateClinic> privateClinics = restApiClient.getAllClinics();
        privateClinicService.setPrivateClinicList(privateClinics);
        try {
        clinicGrid.setItems(privateClinics);
        } catch (Exception e){
            setClinicId(0);
            refreshClinic();
        }
    }

    public void findByName(){
        patientGrid.setItems(patientService.findByName(filter.getValue()));
    }

    public void refreshPatient() {
        List<Patient> patients = restApiClient.getAllPatients();
        patientService.setPatientList(patients);
        patientGrid.setItems(patients);
    }

    public void register(final String name, final String surname, final String age, final Sex sex,
                         final String reasonComingToClinic, final String date, final Currency currency) {
        restApiClient.register(name, surname, age, sex, reasonComingToClinic,date,currency);
    }
}
