package b2w.test.star.wars.planets.services;


import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import b2w.test.star.wars.planets.entities.Planet;
import b2w.test.star.wars.planets.thirdparty.swapi.SwapiApi;
import b2w.test.star.wars.planets.thirdparty.swapi.domain.PlanetSwapiResponse;
import b2w.test.star.wars.planets.thirdparty.swapi.domain.PlanetSearchSwapiResponse;
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
    void shouldNotCallNextPageWhenMatchingPlanetIsFound() {
        var planet = new Planet();
        var samePlanetResponse = new PlanetSwapiResponse();
        var anotherPlanetResponse = new PlanetSwapiResponse();
        var response = new PlanetSearchSwapiResponse();

        planet.setName("name");

        samePlanetResponse.setName("name");
        samePlanetResponse.setFilms(List.of("movie-uri1", "movie-uri2", "movie-uri3"));
        anotherPlanetResponse.setName("another-name");

        response.setResults(List.of(samePlanetResponse, anotherPlanetResponse));
        response.setNext("http://next-page-url");

        given(swapiApi.getPlanetsByName("name")).willReturn(response);

        var result = movieAppearancesService.getMovieAppearances(planet);

        verify(swapiApi, never()).getPlanetsByResource(anyString());

        then(result).isEqualTo(3);
    }

    @Test
    void shouldReturnZeroWhenPlanetNameDoesNotMatchAndPageDoesNotHaveNext() {
        var planet = new Planet();
        var anotherPlanetResponse = new PlanetSwapiResponse();
        var response = new PlanetSearchSwapiResponse();

        planet.setName("name");

        anotherPlanetResponse.setName("not-same-planet");

        response.setResults(List.of(anotherPlanetResponse));
        response.setNext(null);

        given(swapiApi.getPlanetsByName("name")).willReturn(response);

        var result = movieAppearancesService.getMovieAppearances(planet);

        verify(swapiApi, never()).getPlanetsByResource(anyString());

        then(result).isEqualTo(0);
    }

    @Test
    void shouldCallNextPageWhenResultIsNotOnFirstPage() {
        var planet = new Planet();
        var anotherPlanetResponse = new PlanetSwapiResponse();
        var samePlanetResponse = new PlanetSwapiResponse();
        var firstPageResponse = new PlanetSearchSwapiResponse();
        var nextPageResponse = new PlanetSearchSwapiResponse();

        planet.setName("name");

        anotherPlanetResponse.setName("another-name");
        samePlanetResponse.setName("name");
        samePlanetResponse.setFilms(List.of("movie-uri1", "movie-uri2"));

        firstPageResponse.setResults(List.of(anotherPlanetResponse));
        firstPageResponse.setNext("http://next-page-url");

        nextPageResponse.setResults(List.of(samePlanetResponse));

        given(swapiApi.getPlanetsByName("name")).willReturn(firstPageResponse);

        given(swapiApi.getPlanetsByResource("http://next-page-url")).willReturn(nextPageResponse);

        var result = movieAppearancesService.getMovieAppearances(planet);

        then(result).isEqualTo(2);
    }


}
