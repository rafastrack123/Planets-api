package b2w.test.star.wars.planets.integrationtest.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.text.IsEmptyString.emptyOrNullString;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import b2w.test.star.wars.planets.entities.Planet;
import b2w.test.star.wars.planets.repositories.PlanetRepository;
import io.restassured.RestAssured;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class PlanetControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private PlanetRepository planetRepository;

    @BeforeEach
    void beforeEach() {
        RestAssured.basePath = "/api/v1";
        RestAssured.port = port;
    }

    @Test
    void listAll() {
        var input = new Planet();

        input.setName("jupiter");
        input.setTerrain("jupiter-terrain");
        input.setClimate("jupiter-climate");
        input.setMovieAppearances(1);

        planetRepository.save(input);

        given()
                .queryParam("name", "jupiter")
                .when()
                .get("/planet")
                .then()
                .statusCode(200)
                .body("content.id", not(emptyOrNullString()))
                .body("content.name", not(emptyOrNullString()))
                .body("content.terrain", not(emptyOrNullString()))
                .body("content.climate", not(emptyOrNullString()))
                .body("content.movieAppearances", not(empty()));
    }

    @Test
    void searchByName() {
        var input = new Planet();

        input.setName("jupiter");
        input.setTerrain("jupiter-terrain");
        input.setClimate("jupiter-climate");
        input.setMovieAppearances(1);

        var planet = planetRepository.save(input);

        given()
                .queryParam("name", "jupiter")
                .when()
                .get("/planet")
                .then()
                .statusCode(200)
                .body("content.id", hasItem(planet.getId()));
    }

    @Test
    void getById() {
        var input = new Planet();

        input.setName("planet-name");
        input.setTerrain("planet-terrain");
        input.setClimate("planet-climate");
        input.setMovieAppearances(1);

        var planet = planetRepository.save(input);

        given()
                .pathParam("planetId", planet.getId())
                .when()
                .get("/planet/{planetId}")
                .then()
                .statusCode(200)
                .body("id", equalTo(planet.getId()))
                .body("name", equalTo("planet-name"))
                .body("terrain", equalTo("planet-terrain"))
                .body("climate", equalTo("planet-climate"))
                .body("movieAppearances", equalTo(1));
    }

    @Test
    void getByIdNotFound() {
        given()
                .pathParam("planetId", "not-found-id")
                .when()
                .get("/planet/{planetId}")
                .then()
                .statusCode(404);
    }

    @Test
    void createPlanet() throws JSONException {
        var request = new JSONObject()
                .put("name", "earth")
                .put("terrain", "earth-terrain")
                .put("climate", "earth-climate");

        given()
                .body(request.toString())
                .contentType("application/json")
                .when()
                .post("/planet")
                .then()
                .statusCode(201)
                .body("id", not(emptyOrNullString()))
                .body("name", equalTo("earth"))
                .body("terrain", equalTo("earth-terrain"))
                .body("climate", equalTo("earth-climate"))
                .body("movieAppearances", equalTo(0));
    }

    @Test
    void deleteById() {
        var planet = planetRepository.save(new Planet());

        given()
                .pathParam("planetId", planet.getId())
                .when()
                .delete("/planet/{planetId}")
                .then()
                .statusCode(200);
    }
}



