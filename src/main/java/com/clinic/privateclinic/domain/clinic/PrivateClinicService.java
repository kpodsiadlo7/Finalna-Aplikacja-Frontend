package com.clinic.privateclinic.domain.clinic;

import java.util.ArrayList;
import java.util.List;

public class PrivateClinicService {
    private static PrivateClinicService privateClinicService;

    public static PrivateClinicService getInstance(){
        if (privateClinicService == null)
            privateClinicService = new PrivateClinicService();

        return privateClinicService;
    }

    private List<PrivateClinic> privateClinicList = new ArrayList<>();

    public void setPrivateClinicList(final List<PrivateClinic> privateClinicList) {
        this.privateClinicList = privateClinicList;
    }
}
