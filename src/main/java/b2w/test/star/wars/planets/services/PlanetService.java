package b2w.test.star.wars.planets.services;

import static b2w.test.star.wars.planets.utils.StringUtils.isBlank;

import b2w.test.star.wars.planets.entities.Planet;
import b2w.test.star.wars.planets.exceptions.NotFoundException;
import b2w.test.star.wars.planets.repositories.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanetService {

    private final PlanetRepository planetRepository;

    public Page<Planet> list(String name, Pageable pageable) {

        if (isBlank(name)) {
            return planetRepository.findAll(pageable);
        }

        return planetRepository.findByName(name, pageable);
    }

    public Planet getById(String planetId) {
        return planetRepository.findById(planetId)
                .orElseThrow(() -> new NotFoundException("planet", planetId));
    }

    public Planet save(Planet planet) {
        return planetRepository.save(planet);
    }

    public void delete(String planetId) {
        var planet = planetRepository.findById(planetId).
                orElseThrow(() -> new NotFoundException("planet", planetId));

        planetRepository.delete(planet);
    }


}
