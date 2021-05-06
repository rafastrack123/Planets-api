package b2w.test.star.wars.planets.services;

import b2w.test.star.wars.planets.entities.Planet;
import b2w.test.star.wars.planets.thirdparty.swapi.SwapiApi;
import b2w.test.star.wars.planets.thirdparty.swapi.domain.PlanetSwapiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieAppearancesService {

    private final SwapiApi swapiApi;

    public int getMovieAppearances(Planet planet) {
        var planetName = planet.getName();
        log.info("Searching for movie appearances of planet: {}", planetName);

        return swapiApi.getFilms(planetName)
                .getResults()
                .stream()
                .filter(swapiPlanet -> isSamePlanet(planetName, swapiPlanet))
                .findFirst()
                .map(this::getMovieCount)
                .orElse(0);
    }

    private boolean isSamePlanet(String planetName, PlanetSwapiResponse swapiPlanet) {
        return swapiPlanet.getName().equalsIgnoreCase(planetName);
    }

    private int getMovieCount(PlanetSwapiResponse swapiPlanet) {
        return swapiPlanet.getFilms().size();
    }
}
