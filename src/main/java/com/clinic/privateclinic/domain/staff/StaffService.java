package com.clinic.privateclinic.domain.staff;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StaffService {
    private List<Staff> staffList = new ArrayList<>();

    public void setStaffList(final List<Staff> staffList) {
        this.staffList = staffList;
    }

    public Set<Staff> findByName(final String name){
        return staffList.stream().filter(staff -> staff.getName().contains(name)).collect(Collectors.toSet());
    }
}
