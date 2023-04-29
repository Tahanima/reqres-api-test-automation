package io.github.tahanima.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

/**
 * @author tahanima
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class List {

    @JsonProperty private int page;

    @JsonProperty(value = "per_page")
    private int perPage;

    @JsonProperty private int total;

    @JsonProperty(value = "total_pages")
    private int totalPages;

    @JsonProperty private Support support;
}
