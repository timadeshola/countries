package com.countries.model.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CountryResponse {

    private String name;
    private String continent;
    private Timestamp dateCreated;
}
