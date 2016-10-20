package com.bskyb.ovp.graphitemock.samples.mainapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartoonCard {
    @JsonProperty
    private String name;
    @JsonProperty
    private String organisation;
}
