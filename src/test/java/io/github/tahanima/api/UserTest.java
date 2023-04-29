package io.github.tahanima.api;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.tahanima.client.UserClient;
import io.github.tahanima.model.User;
import io.github.tahanima.model.UserData;
import io.github.tahanima.model.UserList;
import io.restassured.response.Response;

import org.apache.http.HttpStatus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author tahanima
 */
class UserTest {

    final int DEFAULT_PAGE = 1;
    final int DEFAULT_PER_PAGE = 6;

    @ParameterizedTest
    @CsvSource({"2, 5", "5, 0", "0, 9", "-1, -7"})
    void testSuccessfulFetchingOfUserListShouldReturn200(int page, int perPage) {
        Response response = UserClient.listUsers(page, perPage);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    }

    @ParameterizedTest
    @CsvSource({"0, 0", "1, 6", "3, 3"})
    void testValuesOfPageAndPerPageShouldBeCorrect(int page, int perPage) {
        UserList response = UserClient.listUsers(page, perPage).as(UserList.class);

        assertAll(
                () -> assertEquals(page == 0 ? DEFAULT_PAGE : page, response.getPage()),
                () ->
                        assertEquals(
                                perPage == 0 ? DEFAULT_PER_PAGE : perPage, response.getPerPage()));
    }

    @ParameterizedTest
    @CsvSource({"0, 0", "1, 5", "99, 99", "-99, -99"})
    void testValueOfTotalPagesShouldBeCorrect(int page, int perPage) {
        UserList response = UserClient.listUsers(page, perPage).as(UserList.class);

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
        UserList response = UserClient.listUsers(page, perPage).as(UserList.class);

        assertEquals(count, response.getData().length);
    }

    @ParameterizedTest
    @CsvSource({"1", "12"})
    void testSuccessfulFetchingOfSingleUserShouldReturn200(int id) {
        Response response = UserClient.listSingleUser(id);

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    }

    @ParameterizedTest
    @CsvSource({"0", "13"})
    void testFetchingOfNonExistentSingleUserShouldReturn404(int id) {
        Response response = UserClient.listSingleUser(id);

        assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
    }

    @ParameterizedTest
    @CsvSource({
        "1, george.bluth@reqres.in, George, Bluth, https://reqres.in/img/faces/1-image.jpg",
        "12, rachel.howell@reqres.in, Rachel, Howell, https://reqres.in/img/faces/12-image.jpg"
    })
    void testSingleUserDataShouldBeCorrect(
            int id, String email, String firstName, String lastName, String avatar) {
        UserData response = UserClient.listSingleUser(id).as(UserData.class);

        assertAll(
                () -> assertEquals(id, response.getData().getId()),
                () -> assertEquals(email, response.getData().getEmail()),
                () -> assertEquals(firstName, response.getData().getFirstName()),
                () -> assertEquals(lastName, response.getData().getLastName()),
                () -> assertEquals(avatar, response.getData().getAvatar()));
    }

    @ParameterizedTest
    @CsvSource({"2, , ", "2, person, job"})
    void testSuccessfulUpdatingOfSingleUserViaPutShouldReturn200(int id, String name, String job) {
        Response response =
                UserClient.updateUserViaPut(id, User.builder().name(name).job(job).build());

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    }

    @ParameterizedTest
    @CsvSource({"2, , ", "2, person, job"})
    void testNameAndJobUpdatesOfSingleUserViaPutShouldBeCorrect(int id, String name, String job) {
        User response =
                UserClient.updateUserViaPut(id, User.builder().name(name).job(job).build())
                        .as(User.class);

        assertAll(
                () -> assertEquals(name, response.getName()),
                () -> assertEquals(job, response.getJob()));
    }

    @ParameterizedTest
    @CsvSource({"2, , ", "2, person, job"})
    void testSuccessfulUpdatingOfSingleUserViaPatchShouldReturn200(
            int id, String name, String job) {
        Response response =
                UserClient.updateUserViaPatch(id, User.builder().name(name).job(job).build());

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    }

    @ParameterizedTest
    @CsvSource({"2, , ", "2, person, job"})
    void testNameAndJobUpdatesOfSingleUserViaPatchShouldBeCorrect(int id, String name, String job) {
        User response =
                UserClient.updateUserViaPatch(id, User.builder().name(name).job(job).build())
                        .as(User.class);

        assertAll(
                () -> assertEquals(name, response.getName()),
                () -> assertEquals(job, response.getJob()));
    }

    @ParameterizedTest
    @CsvSource({"2"})
    void testSuccessfulDeletionOfSingleUserShouldReturn204(int id) {
        Response response = UserClient.deleteUser(id);

        assertEquals(HttpStatus.SC_NO_CONTENT, response.getStatusCode());
    }
}
