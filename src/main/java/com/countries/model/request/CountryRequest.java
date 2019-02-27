package com.countries.model.request;

import lombok.Data;

@Data
public class CountryRequest {

    private Long id;
    private String name;
    private String continent;
}
