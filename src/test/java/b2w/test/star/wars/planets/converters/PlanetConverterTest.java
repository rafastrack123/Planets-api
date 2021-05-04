package b2w.test.star.wars.planets.converters;

import static org.assertj.core.api.BDDAssertions.then;

import b2w.test.star.wars.planets.entities.Planet;
import org.junit.jupiter.api.Test;

public class PlanetConverterTest {

    private final PlanetConverter converter = new PlanetConverter();

    @Test
    void fromEntityToResponse() {
        var planet = new Planet();

        planet.setName("name");
        planet.setClimate("climate");
        planet.setTerrain("terrain");
        planet.setMovieAppearances(5);

        var response = converter.from(planet);

        then(response.getName()).isEqualTo("name");
        then(response.getClimate()).isEqualTo("climate");
        then(response.getTerrain()).isEqualTo("terrain");
        then(response.getMovieAppearances()).isEqualTo(5);
    }

}
