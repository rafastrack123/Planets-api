package b2w.test.star.wars.planets.thirdparty.swapi;


import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import b2w.test.star.wars.planets.thirdparty.swapi.domain.PlanetSearchSwapiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class SwapiApiTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private SwapiApi swapiApi;

    @Mock
    private ResponseEntity<PlanetSearchSwapiResponse> swapiResponse;

    @BeforeEach
    void setUp() {
        swapiApi.swapiUrl = "http://swapi.api";
    }

    @Test
    void getFilmsByNameSuccess() {
        var name = "planet-name";
        var planetsSearch = mock(PlanetSearchSwapiResponse.class);

        given(swapiResponse.getStatusCode()).willReturn(OK);
        given(swapiResponse.getBody()).willReturn(planetsSearch);

        given(restTemplate.getForEntity("http://swapi.api/planets?search={planetName}", PlanetSearchSwapiResponse.class, name)).willReturn(swapiResponse);

        var result = swapiApi.getFilmsByName(name);

        then(result).isEqualTo(planetsSearch);
    }

    @Test
    void getFilmsByNameFailedRequest() {
        var name = "planet-name";

        given(swapiResponse.getStatusCode()).willReturn(NOT_FOUND);

        given(restTemplate.getForEntity("http://swapi.api/planets?search={planetName}", PlanetSearchSwapiResponse.class, name)).willReturn(swapiResponse);

        var result = swapiApi.getFilmsByName(name);

        verify(swapiResponse, never()).getBody();

        then(result).isNotNull();
        then(result.getResults()).isEmpty();
    }

    @Test
    void getFilmsByResourceSuccess() {
        var url = "http://full.swapi.url";
        var planetsSearch = mock(PlanetSearchSwapiResponse.class);

        given(swapiResponse.getStatusCode()).willReturn(OK);
        given(swapiResponse.getBody()).willReturn(planetsSearch);

        given(restTemplate.getForEntity(url, PlanetSearchSwapiResponse.class)).willReturn(swapiResponse);

        var result = swapiApi.getFilmsByResource(url);

        then(result).isEqualTo(planetsSearch);
    }

    @Test
    void getFilmsByResourceFailedRequest() {
        var url = "http://full.swapi.url";

        given(swapiResponse.getStatusCode()).willReturn(NOT_FOUND);

        given(restTemplate.getForEntity(url, PlanetSearchSwapiResponse.class)).willReturn(swapiResponse);

        var result = swapiApi.getFilmsByResource(url);

        verify(swapiResponse, never()).getBody();

        then(result).isNotNull();
        then(result.getResults()).isEmpty();
    }
}
