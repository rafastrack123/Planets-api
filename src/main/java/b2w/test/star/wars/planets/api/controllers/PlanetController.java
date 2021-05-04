package b2w.test.star.wars.planets.api.controllers;

import static java.util.Collections.emptyList;
import static org.springframework.http.HttpStatus.CREATED;

import b2w.test.star.wars.planets.api.models.PlanetRequest;
import b2w.test.star.wars.planets.api.models.PlanetResponse;
import b2w.test.star.wars.planets.converters.PlanetConverter;
import b2w.test.star.wars.planets.services.PlanetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ap1/v1/planet")
@Slf4j
public class PlanetController {

    private final PlanetService service;
    private final PlanetConverter converter;

    @GetMapping
    public Page<PlanetResponse> list(@RequestParam(value = "name", required = false) String name) {
        log.info("Listing planets with filter: {}", name);
        return new PageImpl<>(emptyList());
    }

    @GetMapping("/{planetId}")
    public PlanetResponse getById(@PathVariable("planetId") String planetId) {
        log.info("Getting planet by id: {}", planetId);

        var planet = service.getById(planetId);

        return converter.from(planet);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public PlanetResponse createPlanet(@RequestBody PlanetRequest request) {
        log.info("Creating planet: {}", request);
        return new PlanetResponse();
    }

    @DeleteMapping("/{planetId}")
    public void deletePlanet(@PathVariable("planetId") String planetId) {
        log.info("Deleting planet by id: {}", planetId);
        service.delete(planetId);
    }

}
