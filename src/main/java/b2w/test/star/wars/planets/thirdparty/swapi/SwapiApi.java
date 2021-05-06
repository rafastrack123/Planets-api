package b2w.test.star.wars.planets.thirdparty.swapi;

import static org.springframework.http.HttpStatus.OK;

import b2w.test.star.wars.planets.thirdparty.swapi.domain.PlanetSearchSwapiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class SwapiApi {

    private final RestTemplate restTemplate;

    @Value("${swapi.api.url}")
    String swapiUrl;

    public PlanetSearchSwapiResponse getFilmsByName(String name) {
        var url = swapiUrl + "/planets?search={planetName}";
        var response = restTemplate.getForEntity(url, PlanetSearchSwapiResponse.class, name);

        return getSuccessResponseOrDefaultEntity(response);
    }

    public PlanetSearchSwapiResponse getFilmsByResource(String filmsResource) {
        var response = restTemplate.getForEntity(filmsResource, PlanetSearchSwapiResponse.class);

        return getSuccessResponseOrDefaultEntity(response);
    }

    private PlanetSearchSwapiResponse getSuccessResponseOrDefaultEntity(ResponseEntity<PlanetSearchSwapiResponse> response) {
        if (response.getStatusCode() == OK) {
            return response.getBody();
        }

        return new PlanetSearchSwapiResponse();
    }

}
