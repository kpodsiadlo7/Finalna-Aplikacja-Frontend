package com.clinic.privateclinic.domain.staff;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StaffService {
    private List<Staff> staffList;
    private static StaffService service;

    public static StaffService getInstance(){
        if (service == null){
            service = new StaffService();
        }
        return service;
    }
}
