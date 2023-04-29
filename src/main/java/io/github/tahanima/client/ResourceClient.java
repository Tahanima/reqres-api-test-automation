package io.github.tahanima.client;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * @author tahanima
 */
public final class ResourceClient extends BaseClient {

    private ResourceClient() {}

    public static Response listResources(String type, int page, int perPage) {
        return given().queryParam("page", page).queryParam("per_page", perPage).when().get(type);
    }

    private static String constructPath(String type, int id) {
        return String.format("%s/%s", type, id);
    }

    public static Response listSingleResource(String type, int id) {
        return given().when().get(constructPath(type, id));
    }

    public static Response updateResourceUsingPut(String type, int id) {
        return given().contentType(ContentType.JSON).when().put(constructPath(type, id));
    }

    public static Response updateResourceUsingPatch(String type, int id) {
        return given().contentType(ContentType.JSON).when().patch(constructPath(type, id));
    }

    public static Response deleteResource(String type, int id) {
        return given().when().delete(constructPath(type, id));
    }
}
