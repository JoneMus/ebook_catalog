package com.jonemus.ebook_catalog;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

@SpringBootTest(
    webEnvironment = WebEnvironment.RANDOM_PORT
)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Ebook_catalog_integration_test {

    @LocalServerPort
    private Integer port;

    @Test
    @Order(1)
    void test_post_ebook() {
        RestAssured.given()
            .contentType("application/json")
            .body("{\"author\":\"name\", \"title\": \"ebook\", \"format\": \"epub\"}")
        .when()   
            .post("http://localhost:"+ port +"/ebooks")
        .then()
        .assertThat()
            .statusCode(201)
            .body("author", Matchers.equalTo("name"))
            .body("title", Matchers.equalTo("ebook"))
            .body("format", Matchers.equalTo("epub"));            
    }

    @Test
    @Order(2)
    void test_get_ebooks() {
        RestAssured.given()
            .when()
            .get("http://localhost:"+ port +"/ebooks")
        .then()
        .assertThat()
            .statusCode(200)
            .body("size()", Matchers.is(1));
    }   

    @Test
    @Order(3)
    void test_delete_ebook() {
        // Adding new ebook
         String id = given()
            .contentType("application/json")
            .body("{\"author\":\"name4\", \"title\": \"ebook4\", \"format\": \"epub\"}")
        .when()
            .post("http://localhost:"+ port +"/ebooks")
        .then()
        .assertThat()
            .statusCode(201)
            .extract()
            .path("id");
        // Deleting ebook 
        RestAssured.given()
        .when()   
            .delete("http://localhost:"+ port +"/ebooks/" + id)
        .then()
        .assertThat()
            .statusCode(200);
        // Checking number of ebooks
        RestAssured.given()
            .when()
            .get("http://localhost:"+ port +"/ebooks")
        .then()
        .assertThat()
            .statusCode(200)
            .body("size()", Matchers.is(1));
    }

    @Test
    @Order(4)
    void test_get_ebook() {
        String id = given()
            .contentType("application/json")
            .body("{\"author\":\"name2\", \"title\": \"ebook2\", \"format\": \"epub\"}")
        .when()
            .post("http://localhost:"+ port +"/ebooks")
        .then()
        .assertThat()
            .statusCode(201)
            .extract()
            .path("id");
        
        RestAssured.given()
        .when()
            .get("http://localhost:"+ port +"/ebooks/"+ id)
        .then()
        .assertThat()
            .statusCode(200)
            .body("author", Matchers.equalTo("name2"))
            .body("title", Matchers.equalTo("ebook2"))
            .body("format", Matchers.equalTo("epub"));
    }

    @Test
    @Order(5)
    void test_update_ebook() {
        String id = given()
            .contentType("application/json")
            .body("{\"author\":\"name3\", \"title\": \"ebook3\", \"format\": \"epub\"}")
        .when()
            .post("http://localhost:"+ port +"/ebooks")
        .then()
        .assertThat()
            .statusCode(201)
            .extract()
            .path("id");

        RestAssured.given()
            .contentType("application/json")
            .body("{\"author\":\"updated_name\", \"title\": \"updated_ebook\", \"format\": \"epub\"}")
        .when()   
            .put("http://localhost:"+ port +"/ebooks/" + id)
        .then()
        .assertThat()
            .statusCode(200)
            .body("author", Matchers.equalTo("updated_name"))
            .body("title", Matchers.equalTo("updated_ebook"))
            .body("format", Matchers.equalTo("epub"));
    }
}
