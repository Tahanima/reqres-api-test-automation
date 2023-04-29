package io.github.tahanima.client;

import io.restassured.RestAssured;

/**
 * @author tahanima
 */
public class BaseClient {

    static {
        RestAssured.baseURI = "https://reqres.in/api/";
    }
}
