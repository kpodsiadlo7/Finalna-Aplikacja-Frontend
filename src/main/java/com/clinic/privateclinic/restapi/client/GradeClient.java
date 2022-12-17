package com.clinic.privateclinic.restapi.client;

import com.clinic.privateclinic.domain.grade.Grade;
import com.clinic.privateclinic.view.RegisterView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class GradeClient extends FormLayout {

    private final TextField description = new TextField("Description");
    private final ComboBox<Integer> grade = new ComboBox<>("Grade");
    private RegisterView registerView;
    private Button rateButton = new Button("Rate");
    private Binder<Grade> gradeBinder = new Binder<>(Grade.class);

    public GradeClient(RegisterView registerView){
        this.registerView = registerView;
        HorizontalLayout button = new HorizontalLayout(rateButton);
        grade.setItems(1,2,3,4,5,6,7,8,9,10);
        add(grade,description,button);
        rateButton.addClickListener(event -> {
            rate(
                    description.getValue(),
                    grade);
        });
    }
    private void rate(final String description, final ComboBox<Integer> grade){
        registerView.rate(description,grade.getValue());
        registerView.refreshClinic();
    }
}
