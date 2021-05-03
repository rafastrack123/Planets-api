package b2w.test.star.wars.planets.services;

import b2w.test.star.wars.planets.repositories.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanetService {

    private PlanetRepository planetRepository;


}
