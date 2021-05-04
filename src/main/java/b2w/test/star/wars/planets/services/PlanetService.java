package b2w.test.star.wars.planets.services;

import b2w.test.star.wars.planets.entities.Planet;
import b2w.test.star.wars.planets.exceptions.NotFoundException;
import b2w.test.star.wars.planets.repositories.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanetService {

    private final PlanetRepository planetRepository;

    public Planet getById(String planetId) {
        return planetRepository.findById(planetId)
                .orElseThrow(() -> new NotFoundException("planet", planetId));
    }

    public void delete(String planetId) {
        var planet = planetRepository.findById(planetId).
                orElseThrow(() -> new NotFoundException("planet", planetId));

        planetRepository.delete(planet);
    }


}
