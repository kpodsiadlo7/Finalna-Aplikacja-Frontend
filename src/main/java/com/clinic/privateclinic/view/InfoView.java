package com.clinic.privateclinic.view;

import com.clinic.privateclinic.restapi.client.RestApiClient;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("/info")
public class InfoView {
    @Autowired
    private RestApiClient restApiClient;

    public InfoView(){
        Text welcomeText = new Text("Witaj");
        Text weatherText = new Text("Jeżeli chcesz sprawdzić pogodę wprowadź miejscowość");

    }
}
