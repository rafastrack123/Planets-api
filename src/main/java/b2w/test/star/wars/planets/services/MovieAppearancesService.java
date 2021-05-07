package b2w.test.star.wars.planets.services;

import b2w.test.star.wars.planets.entities.Planet;
import b2w.test.star.wars.planets.thirdparty.swapi.SwapiApi;
import b2w.test.star.wars.planets.thirdparty.swapi.domain.PlanetSwapiResponse;
import b2w.test.star.wars.planets.thirdparty.swapi.domain.PlanetSearchSwapiResponse;
import java.util.Optional;
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

        var planetsSearch = swapiApi.getPlanetsByName(planetName);

        return getMatchingPlanetMovieAppearances(planetName, planetsSearch);
    }

    private Integer getMatchingPlanetMovieAppearances(String planetName, PlanetSearchSwapiResponse planetsSearch) {
        var matchingPlanet = findMatchingPlanet(planetName, planetsSearch);

        if (matchingPlanet.isPresent() || planetsSearch.doesNotHaveNext()) {
            return matchingPlanet
                    .map(this::getMovieCount)
                    .orElse(0);
        }

        var nextPagePlanetSearch = swapiApi.getPlanetsByResource(planetsSearch.getNext());
        log.info("Searching for matching planet on next page: {}", planetsSearch.getNext());
        return getMatchingPlanetMovieAppearances(planetName, nextPagePlanetSearch);
    }

    private Optional<PlanetSwapiResponse> findMatchingPlanet(String planetName, PlanetSearchSwapiResponse planetsSearch) {
        return planetsSearch
                .getResults()
                .stream()
                .filter(swapiPlanet -> isSamePlanet(planetName, swapiPlanet))
                .findFirst();
    }

    private boolean isSamePlanet(String planetName, PlanetSwapiResponse swapiPlanet) {
        return swapiPlanet.getName().equalsIgnoreCase(planetName);
    }

    private int getMovieCount(PlanetSwapiResponse swapiPlanet) {
        return swapiPlanet.getFilms().size();
    }

}
