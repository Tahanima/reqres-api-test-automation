package io.github.tahanima.client;

import static io.restassured.RestAssured.given;

import io.github.tahanima.model.Auth;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * @author tahanima
 */
public final class AuthClient extends BaseClient {

    private AuthClient() {}

    public static Response login(Auth payload) {
        return given().contentType(ContentType.JSON).body(payload).when().post("login");
    }

    public static Response register(Auth payload) {
        return given().contentType(ContentType.JSON).body(payload).when().post("register");
    }

    public static Response logout() {
        return given().contentType(ContentType.JSON).when().post("logout");
    }
}
