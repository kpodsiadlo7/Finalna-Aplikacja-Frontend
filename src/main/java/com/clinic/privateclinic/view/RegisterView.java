package com.clinic.privateclinic.view;

import com.clinic.privateclinic.domain.clinic.PrivateClinic;
import com.clinic.privateclinic.domain.clinic.PrivateClinicService;
import com.clinic.privateclinic.domain.enums.Currency;
import com.clinic.privateclinic.domain.enums.Sex;
import com.clinic.privateclinic.domain.grade.Grade;
import com.clinic.privateclinic.domain.patient.Patient;
import com.clinic.privateclinic.domain.patient.PatientService;
import com.clinic.privateclinic.restapi.client.*;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route("/register")
public class RegisterView extends VerticalLayout {
    @Autowired
    private RestApiClient restApiClient;
    private final PatientService patientService = PatientService.getInstance();
    private final PrivateClinicService privateClinicService = PrivateClinicService.getInstance();
    private final Grid<Patient> patientGrid = new Grid<>(Patient.class);
    private final Grid<PrivateClinic> clinicGrid = new Grid<>(PrivateClinic.class);
    private final Grid<Grade> gradeGrid = new Grid<>(Grade.class);
    private RegisterPatientClient registerPatientClient = new RegisterPatientClient(this);
    private GradeClient gradeClient = new GradeClient(this);

    private final Button buttonPatientRefresh = new Button("Refresh");
    private final Button buttonClinicRefresh = new Button("Refresh");


    public RegisterView() {
        Text patientsText = new Text("PATIENTS LIST");
        patientGrid.setColumns("id","name","surname","sex","vocation","age","reasonComingToClinic");
        add(patientGrid,patientsText,buttonPatientRefresh);
        buttonPatientRefresh.addClickListener(update -> refreshPatient());
        HorizontalLayout createPatient = new HorizontalLayout(patientGrid, registerPatientClient);
        createPatient.setSizeFull();
        patientGrid.setSizeFull();
        add(createPatient);


        Text clinicText = new Text("CLINIC INFO");
        clinicGrid.setColumns("id","clinicName","city","street","staffQuantity","hospitalizedQuantity","grade");
        gradeGrid.setColumns("nickname","description","grade");
        add(clinicGrid,gradeGrid,clinicText,buttonClinicRefresh);
        HorizontalLayout clinicGrade = new HorizontalLayout(clinicGrid,gradeGrid);
        HorizontalLayout all = new HorizontalLayout(clinicGrade,gradeClient);
        clinicGrade.setSizeFull();
        clinicGrid.setSizeFull();
        gradeGrid.setSizeFull();
        all.setSizeFull();
        add(all);
        buttonClinicRefresh.addClickListener(update -> refreshClinic());
        try {
        clinicGrid.asSingleSelect().addValueChangeListener(event -> setClinicId(event.getValue().getId()));
        patientGrid.asSingleSelect().addValueChangeListener(event -> setPatientName(event.getValue().getId()));
        }catch (Exception e){
            refreshClinic();
        }

        setSizeFull();
    }

    void setClinicId(final int clinicId) {
        restApiClient.setClinicId(clinicId);
    }
    void setPatientName(final long patientId){
        restApiClient.setPatientName(patientId);
    }

    public void refreshClinic() {
        List<PrivateClinic> privateClinics = restApiClient.getAllClinics();
        privateClinicService.setPrivateClinicList(privateClinics);
        gradeGrid.setItems(privateClinicService.getGradesList());
        try {
            clinicGrid.setItems(privateClinics);
        } catch (Exception e){
            setClinicId(0);
            refreshClinic();
        }
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

    public void rate(final String description, final int grade) {
        restApiClient.rateClinic(description,grade);
    }

    public String setEurRate() {
        Optional<Rates> rate = Optional.ofNullable(restApiClient.getEurFromNBP().get().getRates().get(0));
        Double eurRate = rate.stream().map(Rates::getMid).findFirst().get();
        return Double.toString(eurRate);
    }

    public String setUsdRate() {
        Optional<Rates> rate = Optional.ofNullable(restApiClient.getUsdFromNBP().get().getRates().get(0));
        Double usdRate = rate.stream().map(Rates::getMid).findFirst().get();
        return Double.toString(usdRate);
    }
}
