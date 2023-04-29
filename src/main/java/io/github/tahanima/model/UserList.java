package io.github.tahanima.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

/**
 * @author tahanima
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public final class UserList extends List {

    @JsonProperty
    private User[] data;
}
