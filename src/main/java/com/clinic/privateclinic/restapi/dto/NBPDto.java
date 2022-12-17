package com.clinic.privateclinic.restapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NBPDto {
    private List<RatesDto> rates;
}
