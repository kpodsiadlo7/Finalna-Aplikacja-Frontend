package com.clinic.privateclinic;

import com.clinic.privateclinic.domain.patient.Patient;
import com.clinic.privateclinic.domain.patient.PatientService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {
    private PatientService patientService = PatientService.getInstance();
    private Grid<Patient> grid = new Grid<>(Patient.class);

    public MainView(){
        grid.setColumns("reasonComingToClinic","name","surname","sex","vocation","age");
        add(grid);
        setSizeFull();
        refresh();
    }

    public void refresh(){
        grid.setItems(patientService.getPatientList());
    }
}
