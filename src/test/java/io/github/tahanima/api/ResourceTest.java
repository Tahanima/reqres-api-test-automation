package io.github.tahanima.api;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.tahanima.client.ResourceClient;
import io.github.tahanima.model.ResourceData;
import io.github.tahanima.model.ResourceList;
import io.restassured.response.Response;

import org.apache.http.HttpStatus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author tahanima
 */
class ResourceTest {

    final int DEFAULT_PAGE = 1;
    final int DEFAULT_PER_PAGE = 6;
    final String RESOURCE_TYPE = "unknown";

    @ParameterizedTest
    @CsvSource({"2, 5", "5, 0", "0, 9", "-1, -7"})
    void testSuccessfulFetchingOfUnknownResourceListShouldReturn200(int page, int perPage) {
        Response response = ResourceClient.listResources(RESOURCE_TYPE, page, perPage);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    }

    @ParameterizedTest
    @CsvSource({"0, 0", "1, 6", "3, 3"})
    void testValuesOfPageAndPerPageShouldBeCorrect(int page, int perPage) {
        ResourceList response =
                ResourceClient.listResources(RESOURCE_TYPE, page, perPage).as(ResourceList.class);

        assertAll(
                () -> assertEquals(page == 0 ? DEFAULT_PAGE : page, response.getPage()),
                () ->
                        assertEquals(
                                perPage == 0 ? DEFAULT_PER_PAGE : perPage, response.getPerPage()));
    }

    @ParameterizedTest
    @CsvSource({"0, 0", "1, 5", "99, 99", "-99, -99"})
    void testValueOfTotalPagesShouldBeCorrect(int page, int perPage) {
        ResourceList response =
                ResourceClient.listResources(RESOURCE_TYPE, page, perPage).as(ResourceList.class);

        assertEquals(
                (int)
                        Math.ceil(
                                1.0
                                        * response.getTotal()
                                        / (perPage == 0 ? DEFAULT_PER_PAGE : perPage)),
                response.getTotalPages());
    }

    @ParameterizedTest
    @CsvSource({"0, 0, 6", "3, 5, 2", "99, 99, 0", "-99, -99, 0"})
    void testCountOfDataShouldBeCorrect(int page, int perPage, int count) {
        ResourceList response =
                ResourceClient.listResources(RESOURCE_TYPE, page, perPage).as(ResourceList.class);

        assertEquals(count, response.getData().length);
    }

    @ParameterizedTest
    @CsvSource({"1", "12"})
    void testSuccessfulFetchingOfUnknownSingleResourceShouldReturn200(int id) {
        Response response = ResourceClient.listSingleResource(RESOURCE_TYPE, id);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    }

    @ParameterizedTest
    @CsvSource({"0", "13"})
    void testFetchingOfNonExistentUnknownSingleResourceShouldReturn404(int id) {
        Response response = ResourceClient.listSingleResource(RESOURCE_TYPE, id);

        assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
    }

    @ParameterizedTest
    @CsvSource({"3, true red, 2002, #BF1932, 19-1664", "10, mimosa, 2009, #F0C05A, 14-0848"})
    void testUnknownSingleResourceDataShouldBeCorrect(
            int id, String name, int year, String color, String pantoneValue) {
        ResourceData response =
                ResourceClient.listSingleResource(RESOURCE_TYPE, id).as(ResourceData.class);

        assertAll(
                () -> assertEquals(id, response.getData().getId()),
                () -> assertEquals(name, response.getData().getName()),
                () -> assertEquals(year, response.getData().getYear()),
                () -> assertEquals(color, response.getData().getColor()),
                () -> assertEquals(pantoneValue, response.getData().getPantoneValue()));
    }

    @ParameterizedTest
    @CsvSource({"2"})
    void testSuccessfulDeletionOfUnknownSingleResourceShouldReturn204(int id) {
        Response response = ResourceClient.deleteResource(RESOURCE_TYPE, id);

        assertEquals(HttpStatus.SC_NO_CONTENT, response.getStatusCode());
    }
}
