package io.github.tahanima.model;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.*;

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
public final class User {

    @JsonProperty(access = WRITE_ONLY)
    private int id;

    @JsonProperty(access = WRITE_ONLY)
    private String email;

    @JsonProperty(value = "first_name", access = WRITE_ONLY)
    private String firstName;

    @JsonProperty(value = "last_name", access = WRITE_ONLY)
    private String lastName;

    @JsonProperty(access = WRITE_ONLY)
    private String avatar;

    @JsonProperty(access = READ_WRITE)
    private String name;

    @JsonProperty(access = READ_WRITE)
    private String job;

    @JsonProperty(access = WRITE_ONLY)
    private String updatedAt;
}
