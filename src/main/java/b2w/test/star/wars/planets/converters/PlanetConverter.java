package b2w.test.star.wars.planets.converters;

import b2w.test.star.wars.planets.api.models.PlanetRequest;
import b2w.test.star.wars.planets.api.models.PlanetResponse;
import b2w.test.star.wars.planets.entities.Planet;
import org.springframework.stereotype.Component;

@Component
public class PlanetConverter {

    public PlanetResponse from(Planet planet) {
        var response = new PlanetResponse();

        response.setId(planet.getId());
        response.setName(planet.getName());
        response.setClimate(planet.getClimate());
        response.setTerrain(planet.getTerrain());
        response.setMovieAppearances(planet.getMovieAppearances());

        return response;
    }

    public Planet from(PlanetRequest request) {
        var planet = new Planet();

        planet.setName(request.getName());
        planet.setClimate(request.getClimate());
        planet.setTerrain(request.getTerrain());

        return planet;
    }

}
