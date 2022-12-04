package com.clinic.privateclinic.restapi.client;

import com.clinic.privateclinic.domain.enums.Currency;
import com.clinic.privateclinic.domain.enums.Sex;
import com.clinic.privateclinic.domain.enums.Vocation;
import com.clinic.privateclinic.domain.patient.Patient;
import com.clinic.privateclinic.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.io.IOException;

public class RegisterPatient extends FormLayout {
    private final TextField name = new TextField("Name");
    private final TextField surname = new TextField("Surname");
    private final TextField age = new TextField("Age");
    private final TextField reasonComingToClinic = new TextField("ReasonComingToClinic");
    private final TextField date = new TextField("rrrr-mm-dd");
    private final ComboBox<Sex> sexComboBox = new ComboBox<>("Sex");
    private final ComboBox<Currency> currencyComboBox = new ComboBox<>("Currency");
    private final ComboBox<Vocation> vocation = new ComboBox<>("Vocation");
    private MainView mainView;
    private Button registerButton = new Button("Register");
    private Binder<Patient> patientBinder = new Binder<>(Patient.class);

    public RegisterPatient(MainView mainView) {
        this.mainView = mainView;
        sexComboBox.setItems(Sex.values());
        vocation.setItems(Vocation.PATIENT);
        currencyComboBox.setItems(Currency.values());
        patientBinder.bindInstanceFields(this);
        HorizontalLayout buttons = new HorizontalLayout(registerButton);
        add(name,surname,age,sexComboBox,reasonComingToClinic,date,currencyComboBox,buttons);
        registerButton.addClickListener(event -> {
            try {
                register(
                        name.getValue(),
                        surname.getValue(),
                        age.getValue(),
                        sexComboBox.getValue(),
                        reasonComingToClinic.getValue(),
                        date.getValue(),
                        currencyComboBox.getValue());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void register(final String name, final String surname, final String age, final Sex sex,
                         final String reasonComingToClinic, final String date, final Currency currency) throws IOException, InterruptedException {
        mainView.register(name, surname, age, sex, reasonComingToClinic,date,currency);
        mainView.refreshPatient();
        mainView.refreshClinic();
    }
}
