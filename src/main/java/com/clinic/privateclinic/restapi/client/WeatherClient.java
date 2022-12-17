package com.clinic.privateclinic.restapi.client;

import com.clinic.privateclinic.view.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;


public class WeatherClient extends FormLayout {
    private Text info = new Text("Weather from your location");
    private final TextField locationText = new TextField();
    private Text degreesText = new Text("Degrees: 0°C");
    private double degrees = 0;
    private Button submitButton = new Button("Submit");
    private MainView mainView;

    public WeatherClient(MainView mainView){
        this.mainView = mainView;
        HorizontalLayout button = new HorizontalLayout(submitButton);
        locationText.setSizeFull();
        locationText.setPlaceholder("Enter location");
        add(info,locationText,button,degreesText);
        submitButton.addClickListener(event -> getDegrease(locationText.getValue()));
    }

    private void getDegrease(final String location) {
        degreesText.setText("Degrees: "+mainView.getTemperature(location)+"°C");
    }
}
