package com.clinic.privateclinic.restapi.client;

import com.clinic.privateclinic.domain.enums.Currency;
import com.clinic.privateclinic.domain.enums.Sex;
import com.clinic.privateclinic.domain.enums.Vocation;
import com.clinic.privateclinic.domain.patient.Patient;
import com.clinic.privateclinic.view.RegisterView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import lombok.Getter;

import java.io.IOException;

@Getter
public class RegisterPatientClient extends FormLayout {
    private final TextField name = new TextField("Name");
    private final TextField surname = new TextField("Surname");
    private final TextField age = new TextField("Age");
    private final TextField reasonComingToClinic = new TextField("ReasonComingToClinic");
    private final TextField date = new TextField("rrrr-mm-dd");
    private Text usdRate = new Text("USD rate");
    private Text eurRate = new Text("EUR rate");
    private double usd = 4.0;
    private double eur = 4.5;

    public void setUsd(final double usd) {
        this.usd = usd;
    }

    public void setEur(final double eur) {
        this.eur = eur;
    }

    private final ComboBox<Sex> sexComboBox = new ComboBox<>("Sex");
    private final ComboBox<Currency> currencyComboBox = new ComboBox<>("Choose Currency");
    private final ComboBox<Vocation> vocation = new ComboBox<>("Vocation");
    private RegisterView registerView;
    private Button registerButton = new Button("Register");
    private Button rateRefreshButton = new Button("Refresh rate");
    private Binder<Patient> patientBinder = new Binder<>(Patient.class);

    public RegisterPatientClient(RegisterView registerView) {
        this.registerView = registerView;
        eurRate.setText("\nEUR rate " +getEur() +",");
        usdRate.setText("\tUSD rate " +getUsd());
        sexComboBox.setItems(Sex.values());
        vocation.setItems(Vocation.PATIENT);
        currencyComboBox.setItems(Currency.values());
        patientBinder.bindInstanceFields(this);
        HorizontalLayout buttons = new HorizontalLayout(rateRefreshButton,registerButton);
        HorizontalLayout rates = new HorizontalLayout(eurRate,usdRate);
        HorizontalLayout currency = new HorizontalLayout(currencyComboBox,rates);
        add(name,surname,age,sexComboBox,reasonComingToClinic,date,currency,buttons);
        rateRefreshButton.addClickListener(event -> setCurrency());
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

    private void setCurrency() {
        eurRate.setText("\nEUR rate " +registerView.setEurRate() +",");
        usdRate.setText("\tUSD rate " +registerView.setUsdRate());
    }

    private void register(final String name, final String surname, final String age, final Sex sex,
                         final String reasonComingToClinic, final String date, final Currency currency) throws IOException, InterruptedException {
        registerView.register(name, surname, age, sex, reasonComingToClinic,date,currency);
        registerView.refreshPatient();
        registerView.refreshClinic();
    }
}
