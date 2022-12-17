package com.clinic.privateclinic.domain.patient;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
public class DiseaseStory {
    private long patientId;
    private String description;
    private LocalDateTime date;
}
