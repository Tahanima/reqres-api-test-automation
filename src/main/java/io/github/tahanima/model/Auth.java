package io.github.tahanima.model;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

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
public final class Auth {

    @JsonProperty(access = READ_ONLY)
    private String username;

    @JsonProperty(access = READ_ONLY)
    private String email;

    @JsonProperty(access = READ_ONLY)
    private String password;

    @JsonProperty(access = WRITE_ONLY)
    private int id;

    @JsonProperty(access = WRITE_ONLY)
    private String token;
}
