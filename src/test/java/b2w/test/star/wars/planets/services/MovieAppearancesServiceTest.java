package b2w.test.star.wars.planets.services;


import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

import b2w.test.star.wars.planets.entities.Planet;
import b2w.test.star.wars.planets.thirdparty.swapi.SwapiApi;
import b2w.test.star.wars.planets.thirdparty.swapi.domain.PlanetSwapiResponse;
import b2w.test.star.wars.planets.thirdparty.swapi.domain.SwapiPage;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MovieAppearancesServiceTest {

    @Mock
    private SwapiApi swapiApi;

    @InjectMocks
    private MovieAppearancesService movieAppearancesService;

    @Test
    void getMovieAppearances() {
        var planet = new Planet();
        var samePlanetResponse = new PlanetSwapiResponse();
        var anotherPlanetResponse = new PlanetSwapiResponse();
        var response = new SwapiPage<PlanetSwapiResponse>();

        planet.setName("name");

        samePlanetResponse.setName("name");
        samePlanetResponse.setFilms(List.of("movie-uri1", "movie-uri2", "movie-uri3"));
        anotherPlanetResponse.setName("another-name");

        response.setResults(List.of(samePlanetResponse, anotherPlanetResponse));

        given(swapiApi.getFilms("name")).willReturn(response);

        var result = movieAppearancesService.getMovieAppearances(planet);

        then(result).isEqualTo(3);
    }

    @Test
    void shouldReturnZeroWhenPlanetNameDoesNotMatch() {
        var planet = new Planet();
        var anotherPlanetResponse = new PlanetSwapiResponse();
        var response = new SwapiPage<PlanetSwapiResponse>();

        planet.setName("name");

        anotherPlanetResponse.setName("not-same-planet");

        response.setResults(List.of(anotherPlanetResponse));

        given(swapiApi.getFilms("name")).willReturn(response);

        var result = movieAppearancesService.getMovieAppearances(planet);

        then(result).isEqualTo(0);
    }
}
