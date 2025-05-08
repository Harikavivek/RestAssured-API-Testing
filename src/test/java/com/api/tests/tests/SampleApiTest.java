package com.api.tests.tests;

import com.api.tests.base.BaseTest;
import com.api.tests.utils.JsonUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.qameta.allure.*;


public class SampleApiTest extends BaseTest {

    @Test()
    @Epic("User API")
    @Feature("Get Users")
    @Story("GET /api/users")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test retrieves a list of users from ReqRes API")
    public void testGetPost() {
        given().
        when().
            get("/posts/1").
        then().
            statusCode(200).
            contentType(ContentType.JSON).
            body("id", equalTo(1)).
            body("userId", notNullValue());
    }

    @Test
    public void testCreatePost() {
        String requestBody = """ 
          {
          "title": "foo",
          "body": "bar",
          "userId": 1
        }""";
        given().
            contentType("application/json").
            body(requestBody).
        when().
            post("/posts").
        then().
            statusCode(201).
            body("title", equalTo("foo"));
    }

    @Test
    public void testUpdatePost() {
        String updatedBody = """
          {
          "id": 1,
          "title": "updated title",
          "body": "updated content",
          "userId": 1
        }
        """;
        given().
            contentType("application/json").
            body(updatedBody).
        when().
            put("/posts/1").
        then().
            statusCode(200).
            body("title", equalTo("updated title"));
    }

    @Test
    public void testDeletePost() {
        when().
            delete("/posts/1").
        then().
            statusCode(200);
    }

    @Test
    public void testCreatePostFromFile() throws IOException {
        String jsonBody = JsonUtil.readJson("src/test/resources/testdata/createPost.json");

        given().
            contentType("application/json").
            body(jsonBody).
        when().
            post("/posts").
        then().
            statusCode(201).
            body("userId", equalTo(10)).
            body("title", equalTo("external post"));
    }
}
