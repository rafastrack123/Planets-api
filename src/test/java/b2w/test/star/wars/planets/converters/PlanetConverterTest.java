package b2w.test.star.wars.planets.converters;

import static org.assertj.core.api.BDDAssertions.then;

import b2w.test.star.wars.planets.api.models.PlanetRequest;
import b2w.test.star.wars.planets.entities.Planet;
import org.junit.jupiter.api.Test;

public class PlanetConverterTest {

    private final PlanetConverter converter = new PlanetConverter();

    @Test
    void fromEntityToResponse() {
        var planet = new Planet();

        planet.setId("id");
        planet.setName("name");
        planet.setClimate("climate");
        planet.setTerrain("terrain");
        planet.setMovieAppearances(5);

        var response = converter.from(planet);

        then(response.getId()).isEqualTo("id");
        then(response.getName()).isEqualTo("name");
        then(response.getClimate()).isEqualTo("climate");
        then(response.getTerrain()).isEqualTo("terrain");
        then(response.getMovieAppearances()).isEqualTo(5);
    }

    @Test
    void from() {
        var request = new PlanetRequest();

        request.setName("name");
        request.setClimate("climate");
        request.setTerrain("terrain");

        var planet = converter.from(request);

        then(planet.getName()).isEqualTo("name");
        then(planet.getClimate()).isEqualTo("climate");
        then(planet.getTerrain()).isEqualTo("terrain");
        then(planet.getId()).isNull();
        then(planet.getMovieAppearances()).isZero();
    }
}
