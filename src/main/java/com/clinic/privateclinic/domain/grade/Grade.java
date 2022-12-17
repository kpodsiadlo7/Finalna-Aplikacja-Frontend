package com.clinic.privateclinic.domain.grade;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Grade {
    private long id;
    private String nickname;
    private String description;
    private double grade;
}
