package b2w.test.star.wars.planets.thirdparty.swapi;

import b2w.test.star.wars.planets.thirdparty.swapi.domain.PlanetSwapiResponse;
import b2w.test.star.wars.planets.thirdparty.swapi.domain.SwapiPage;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface SwapiApi {

    @RequestLine("GET /planets/?search={search}")
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    SwapiPage<PlanetSwapiResponse> getFilms(@Param String search);

}
