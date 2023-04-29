package io.github.tahanima.api;

import static org.junit.jupiter.api.Assertions.*;

import io.github.tahanima.client.AuthClient;
import io.github.tahanima.model.Auth;
import io.github.tahanima.model.Error;
import io.restassured.response.Response;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author tahanima
 */
class AuthTest {

    @ParameterizedTest
    @CsvSource({
        "eve.holt@reqres.in, , cityslicka",
        ", eve.holt@reqres.in, cityslicka",
        "eve.holt@reqres.in, eve.holt@reqres.in, cityslicka"
    })
    void testSuccessfulLoginAttemptShouldReturn200(String username, String email, String password) {
        Response response =
                AuthClient.login(
                        Auth.builder().username(username).email(email).password(password).build());

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    }

    @ParameterizedTest
    @CsvSource({
        "eve.holt@reqres.in, , cityslicka",
        ", eve.holt@reqres.in, cityslicka",
        "eve.holt@reqres.in, eve.holt@reqres.in, cityslicka"
    })
    void testSuccessfulLoginAttemptShouldReturnNonEmptyStringToken(
            String username, String email, String password) {
        Auth response =
                AuthClient.login(
                                Auth.builder()
                                        .username(username)
                                        .email(email)
                                        .password(password)
                                        .build())
                        .as(Auth.class);

        assertFalse(response.getToken().isBlank());
    }

    @ParameterizedTest
    @CsvSource({
        ", , ,",
        ", , cityslicka",
        "eve.holt@reqres.in, , ",
        "not.found, eve.holt@reqres.in, cityslicka",
        "not.found, not.found@gmail.com, Not Found"
    })
    void testUnsuccessfulLoginAttemptShouldReturn400(
            String username, String email, String password) {
        Response response =
                AuthClient.login(
                        Auth.builder().username(username).email(email).password(password).build());

        assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode());
    }

    @ParameterizedTest
    @CsvSource({
        ", , , Missing email or username",
        ", , cityslicka, Missing email or username",
        "eve.holt@reqres.in, , , Missing password",
        "not.found, eve.holt@reqres.in, cityslicka, user not found",
        "not.found, not.found@gmail.com, Not Found, user not found"
    })
    void testUnsuccessfulLoginAttemptShouldReturnCorrectErrorMessage(
            String username, String email, String password, String error) {
        Error response =
                AuthClient.login(
                                Auth.builder()
                                        .username(username)
                                        .email(email)
                                        .password(password)
                                        .build())
                        .as(Error.class);

        assertEquals(error, response.getError());
    }

    @Test
    void testSuccessfulLogoutAttemptShouldReturn200() {
        Response response = AuthClient.logout();

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    }

    @ParameterizedTest
    @CsvSource({
        "eve.holt@reqres.in, , pistol",
        ", eve.holt@reqres.in, pistol",
        "eve.holt@reqres.in, eve.holt@reqres.in, pistol"
    })
    void testSuccessfulRegistrationAttemptShouldReturn200(
            String username, String email, String password) {
        Response response =
                AuthClient.register(
                        Auth.builder().username(username).email(email).password(password).build());

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    }

    @ParameterizedTest
    @CsvSource({
        "eve.holt@reqres.in, , pistol",
        ", eve.holt@reqres.in, pistol",
        "eve.holt@reqres.in, eve.holt@reqres.in, pistol"
    })
    void testSuccessfulRegistrationAttemptShouldHavePositiveIntegerId(
            String username, String email, String password) {
        Auth response =
                AuthClient.register(
                                Auth.builder()
                                        .username(username)
                                        .email(email)
                                        .password(password)
                                        .build())
                        .as(Auth.class);

        assertTrue(response.getId() > 0);
    }

    @ParameterizedTest
    @CsvSource({
        "eve.holt@reqres.in, , pistol",
        ", eve.holt@reqres.in, pistol",
        "eve.holt@reqres.in, eve.holt@reqres.in, not.so.strong"
    })
    void testSuccessfulRegistrationAttemptShouldHaveNonEmptyStringToken(
            String username, String email, String password) {
        Auth response =
                AuthClient.register(
                                Auth.builder()
                                        .username(username)
                                        .email(email)
                                        .password(password)
                                        .build())
                        .as(Auth.class);

        assertFalse(response.getToken().isBlank());
    }

    @ParameterizedTest
    @CsvSource({
        ", , ,",
        "not.found, , pistol",
        ", not.found@reqres.in, pistol",
        "eve.holt@reqres.in, , ",
        "not.found@reqres.in, eve.holt@reqres.in, pistol"
    })
    void testUnsuccessfulRegistrationAttemptShouldReturn400(
            String username, String email, String password) {
        Response response =
                AuthClient.register(
                        Auth.builder().username(username).email(email).password(password).build());

        assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode());
    }

    @ParameterizedTest
    @CsvSource({
        ", , , Missing email or username",
        "not.found, , pistol, Note: Only defined users succeed registration",
        ", not.found@reqres.in, pistol, Note: Only defined users succeed registration",
        "eve.holt@reqres.in, , , Missing password",
        "not.found@reqres.in, eve.holt@reqres.in, pistol, Note: Only defined users succeed registration"
    })
    void testUnsuccessfulRegistrationAttemptShouldReturnCorrectErrorMessage(
            String username, String email, String password, String error) {
        Error response =
                AuthClient.register(
                                Auth.builder()
                                        .username(username)
                                        .email(email)
                                        .password(password)
                                        .build())
                        .as(Error.class);

        assertEquals(error, response.getError());
    }
}
