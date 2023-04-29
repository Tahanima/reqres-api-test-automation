package io.github.tahanima.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

/**
 * @author tahanima
 */
@Getter
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Resource {

    @JsonProperty private int id;

    @JsonProperty private String name;

    @JsonProperty private int year;

    @JsonProperty private String color;

    @JsonProperty(value = "pantone_value")
    private String pantoneValue;
}
