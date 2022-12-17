package com.clinic.privateclinic.restapi.client;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NBPClient {
    private List<Rates> rates;
}
