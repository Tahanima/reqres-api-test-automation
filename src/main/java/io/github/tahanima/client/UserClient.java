package io.github.tahanima.client;

import static io.restassured.RestAssured.given;

import io.github.tahanima.model.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * @author tahanima
 */
public final class UserClient extends BaseClient {

    private UserClient() {}

    public static Response listUsers(int page, int perPage) {
        return given().queryParam("page", page).queryParam("per_page", perPage).when().get("users");
    }

    private static String constructPath(int id) {
        return "users/" + id;
    }

    public static Response listSingleUser(int id) {
        return given().when().get(constructPath(id));
    }

    public static Response updateUserViaPut(int id, User payload) {
        return given().contentType(ContentType.JSON).body(payload).when().put(constructPath(id));
    }

    public static Response updateUserViaPatch(int id, User payload) {
        return given().contentType(ContentType.JSON).body(payload).when().patch(constructPath(id));
    }

    public static Response deleteUser(int id) {
        return given().when().delete(constructPath(id));
    }
}
