package com.rjuric.vhs_lab;

import com.rjuric.vhs_lab.util.exception_handlers.GlobalExceptionHandler;
import com.rjuric.vhs_lab.util.exception_handlers.VhsControllerExceptionHandler;
import com.rjuric.vhs_lab.util.responses.AuthenticationResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@Import({GlobalExceptionHandler.class, VhsControllerExceptionHandler.class})
class VhsLabApplicationTests {

    @LocalServerPort
    private Integer port;

    private String getUserToken() {
        return RestAssured.given()
                .baseUri("http://localhost")
                .basePath("/api")
                .contentType(ContentType.JSON)
                .body("""
                          {
                            "email": "user1@example.com",
                            "password": "strongpassword"
                          }
                        """
                )
                .when()
                .post("/auth/login")
                .getBody()
                .as(AuthenticationResponse.class).getToken();
    }

    private String getAdminToken() {
        return RestAssured.given()
                .baseUri("http://localhost")
                .basePath("/api")
                .contentType(ContentType.JSON)
                .body("""
                          {
                            "email": "user3@example.com",
                            "password": "truenorth1950"
                          }
                        """
                )
                .when()
                .post("/auth/login")
                .getBody()
                .as(AuthenticationResponse.class).getToken();
    }

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void it_context_loads() {
        RestAssured.given()
            .baseUri("http://localhost")
            .basePath("/api")
            .contentType(ContentType.TEXT)
            .when()
            .get("/")
            .then()
            .statusCode(200);
    }

    // GET /api/vhs

    @Test
    public void it_get_all_returns_seeded_results() {
        RestAssured.given()
            .baseUri("http://localhost")
            .basePath("/api")
            .contentType(ContentType.JSON)
            .when()
            .get("/vhs")
            .then()
            .statusCode(200)
            .body(".", hasSize(is(3)));
    }

    // GET /api/vhs/{id}

    @Test
    public void get_by_id_returns_when_found_404_otherwise() {
        final int foundId = 1;
        final int notFoundId = 4;

        RestAssured.given()
            .baseUri("http://localhost")
            .basePath("/api")
            .contentType(ContentType.JSON)
            .when()
            .get("/vhs/" + foundId)
            .then()
            .statusCode(200)
            .body(".", Matchers.is(Matchers.notNullValue()))
            .body("id", Matchers.is(foundId));

        RestAssured.given()
            .baseUri("http://localhost")
            .basePath("/api")
            .contentType(ContentType.JSON)
            .when()
            .get("/vhs/" + notFoundId)
            .then()
            .statusCode(404)
            .body(".", Matchers.is(Matchers.notNullValue()))
            .body("status", Matchers.is(404));
    }

    // POST /api/vhs/{id}

    @Test
    public void create_returns_403_on_no_token() {
        RestAssured.given()
            .baseUri("http://localhost")
            .basePath("/api")
            .contentType(ContentType.JSON)
            .when()
            .post("/vhs/")
            .then()
            .statusCode(403)
            .body(".", Matchers.is(Matchers.notNullValue()))
            .body("status", Matchers.is(403));
    }

    @Test
    public void create_returns_401_on_invalid_token() {
        RestAssured.given()
            .baseUri("http://localhost")
            .basePath("/api")
            .header("Authorization", "Bearer invalid_token")
            .contentType(ContentType.JSON)
            .when()
            .post("/vhs/")
            .then()
            .statusCode(401)
            .body(".", Matchers.is(Matchers.notNullValue()))
            .body("status", Matchers.is(401));
    }

    @Test
    public void create_returns_403_on_user_not_ADMIN() {
        final String token = getUserToken();

        RestAssured.given()
            .baseUri("http://localhost")
            .basePath("/api")
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .when()
            .post("/vhs/")
            .then()
            .statusCode(403)
            .body(".", Matchers.is(Matchers.notNullValue()));
    }

    @Test
    public void create_returns_400_on_invalid_DTO() {
        final String token = getAdminToken();

        RestAssured.given()
            .baseUri("http://localhost")
            .basePath("/api")
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .when()
            .post("/vhs/")
            .then()
            .statusCode(400)
            .body(".", Matchers.is(Matchers.notNullValue()))
            .body("status", Matchers.is(400));
    }

    @Test
    public void create_returns_201_on_success_and_can_fetch_from_db() {
        final String token = getAdminToken();

        RestAssured.given()
            .baseUri("http://localhost")
            .basePath("/api")
            .header("Authorization", "Bearer " + token)
            .body("""
                    {
                    "name": "Godzilla",
                    "description": "A giant lizard wreaks havoc on New York City."
                    }
                    """)
            .contentType(ContentType.JSON)
            .when()
            .post("/vhs/")
            .then()
            .statusCode(201)
            .body(".", Matchers.is(Matchers.notNullValue()))
            .body("name", Matchers.is("Godzilla"))
            .body("description", Matchers.is("A giant lizard wreaks havoc on New York City."))
            .body("id", Matchers.is(4));

        RestAssured.given()
            .baseUri("http://localhost")
            .basePath("/api")
            .contentType(ContentType.JSON)
            .when()
            .get("/vhs/" + 4)
            .then()
            .statusCode(200)
            .body(".", Matchers.is(Matchers.notNullValue()))
            .body("name", Matchers.is("Godzilla"))
            .body("description", Matchers.is("A giant lizard wreaks havoc on New York City."))
            .body("id", Matchers.is(4));
    }

    // PUT /api/vhs

    @Test
    public void put_returns_403_on_no_token() {
        RestAssured.given()
            .baseUri("http://localhost")
            .basePath("/api")
            .contentType(ContentType.JSON)
            .when()
            .put("/vhs/")
            .then()
            .statusCode(403)
            .body(".", Matchers.is(Matchers.notNullValue()))
            .body("status", Matchers.is(403));
    }

    @Test
    public void put_returns_401_on_invalid_token() {
        RestAssured.given()
            .baseUri("http://localhost")
            .basePath("/api")
            .header("Authorization", "Bearer invalid_token")
            .contentType(ContentType.JSON)
            .when()
            .put("/vhs/")
            .then()
            .statusCode(401)
            .body(".", Matchers.is(Matchers.notNullValue()))
            .body("status", Matchers.is(401));
    }

    @Test
    public void put_returns_403_on_user_not_ADMIN() {
        final String token = getUserToken();

        RestAssured.given()
            .baseUri("http://localhost")
            .basePath("/api")
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .when()
            .put("/vhs/")
            .then()
            .statusCode(403)
            .body(".", Matchers.notNullValue());
    }

    @Test
    public void put_returns_400_on_invalid_DTO() {
        final String token = getAdminToken();

        RestAssured.given()
            .baseUri("http://localhost")
            .basePath("/api")
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .when()
            .put("/vhs/")
            .then()
            .statusCode(400)
            .body(".", Matchers.is(Matchers.notNullValue()))
            .body("status", Matchers.is(400));
    }

    @Test
    public void put_returns_200_on_success_and_can_fetch_from_db() {
        final String token = getAdminToken();

        RestAssured.given()
            .baseUri("http://localhost")
            .basePath("/api")
            .header("Authorization", "Bearer " + token)
            .body("""
                {
                "id": 1,
                "name": "Godzilla",
                "description": "A giant lizard wreaks havoc on New York City."
                }
                """)
            .contentType(ContentType.JSON)
            .when()
            .put("/vhs/")
            .then()
            .statusCode(200)
            .body(".", Matchers.is(Matchers.notNullValue()))
            .body("name", Matchers.is("Godzilla"))
            .body("description", Matchers.is("A giant lizard wreaks havoc on New York City."))
            .body("id", Matchers.is(1));

        RestAssured.given()
            .baseUri("http://localhost")
            .basePath("/api")
            .contentType(ContentType.JSON)
            .when()
            .get("/vhs/" + 1)
            .then()
            .statusCode(200)
            .body(".", Matchers.is(Matchers.notNullValue()))
            .body("name", Matchers.is("Godzilla"))
            .body("description", Matchers.is("A giant lizard wreaks havoc on New York City."))
            .body("id", Matchers.is(1));
    }

    // DELETE /api/vhs/{id}

    @Test
    public void delete_returns_403_on_no_token() {
        RestAssured.given()
            .baseUri("http://localhost")
            .basePath("/api")
            .contentType(ContentType.JSON)
            .when()
            .delete("/vhs/" + 1)
            .then()
            .statusCode(403)
            .body(".", Matchers.is(Matchers.notNullValue()))
            .body("status", Matchers.is(403));
    }

    @Test
    public void delete_returns_401_on_invalid_token() {
        RestAssured.given()
            .baseUri("http://localhost")
            .basePath("/api")
            .header("Authorization", "Bearer invalid_token")
            .contentType(ContentType.JSON)
            .when()
            .delete("/vhs/" + 1)
            .then()
            .statusCode(401)
            .body(".", Matchers.is(Matchers.notNullValue()))
            .body("status", Matchers.is(401));
    }

    @Test
    public void delete_returns_403_on_user_not_ADMIN() {
        final String token = getUserToken();

        RestAssured.given()
            .baseUri("http://localhost")
            .basePath("/api")
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .when()
            .delete("/vhs/" + 1)
            .then()
            .statusCode(403)
            .body(".", Matchers.notNullValue());
    }

    @Test
    public void delete_returns_204_on_success_and_cant_fetch_from_db() {
        final String token = getAdminToken();

        RestAssured.given()
            .baseUri("http://localhost")
            .basePath("/api")
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .when()
            .delete("/vhs/" + 1)
            .then()
            .statusCode(204)
            .noRootPath();

        RestAssured.given()
            .baseUri("http://localhost")
            .basePath("/api")
            .contentType(ContentType.JSON)
            .when()
            .get("/vhs/" + 1)
            .then()
            .statusCode(404)
            .body(".", Matchers.notNullValue());
    }
}
