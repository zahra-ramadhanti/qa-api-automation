package com.zahra.qa.test;

import com.zahra.qa.utils.BaseTest;
import com.zahra.qa.model.UserRequest;
import com.zahra.qa.model.UserResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest extends BaseTest {

    // @Test
    // void testCreateUser() {
    //     String name = "Zahra";
    //     String job = "QA Engineer";
    //     UserRequest newUser = new UserRequest(name, job);

    //     Response response = given()
    //             .log().all() // log request
    //             .header("Content-Type", "application/json")
    //             .header("x-api-key", "reqres-free-v1")
    //             .body(newUser)
    //             .when()
    //             .post("/users")
    //             .then()
    //             .log().all() // log response
    //             .statusCode(anyOf(equalTo(200), equalTo(201), equalTo(204)))
    //             .extract().response();

    //     UserResponse userResponse = response.as(UserResponse.class);

    //     assertEquals(name, userResponse.getName());
    //     assertEquals(job, userResponse.getJob());
    //     assertNotNull(userResponse.getId());
    //     assertNotNull(userResponse.getCreatedAt());
    // }

    @Test
    void testGetUserList() {
        Response response = given()
                .log().all() // log request
                .when()
                .get("/users?page=1")
                .then()
                .log().all() // log response
                .statusCode(200)
                .body("page", equalTo(1))
                .body("data", not(empty()))
                .extract().response();

        int page = response.path("page");
        assertEquals(1, page);

        int dataSize = response.path("data.size()");
        assertTrue(dataSize > 0);
    }
}
