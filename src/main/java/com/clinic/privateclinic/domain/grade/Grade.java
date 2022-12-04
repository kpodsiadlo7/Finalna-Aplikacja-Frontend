package com.clinic.privateclinic.domain.grade;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Grade {
    private long id;
    private String nickname;
    private String description;
    private double grade;
}
