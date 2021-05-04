package b2w.test.star.wars.planets.converters;

import b2w.test.star.wars.planets.api.models.PlanetResponse;
import b2w.test.star.wars.planets.entities.Planet;
import org.springframework.stereotype.Component;

@Component
public class PlanetConverter {

    public PlanetResponse from(Planet planet) {
        var response = new PlanetResponse();

        response.setName(planet.getName());
        response.setClimate(planet.getClimate());
        response.setTerrain(planet.getTerrain());
        response.setMovieAppearances(planet.getMovieAppearances());

        return response;
    }

}
