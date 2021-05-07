package b2w.test.star.wars.planet.integrationtest.data;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.data.domain.PageRequest.of;

import b2w.test.star.wars.planets.entities.Planet;
import b2w.test.star.wars.planets.repositories.PlanetRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class PlanetRepositoryIT {

    @Autowired
    private PlanetRepository planetRepository;

    private List<Planet> createdPlanets;

    @Test
    void shouldFindFullName() {
        var inputOne = new Planet();
        var inputTwo = new Planet();

        inputOne.setName("correct-name");
        inputTwo.setName("another-name");

        var planetOne = planetRepository.save(inputOne);
        var planetTwo = planetRepository.save(inputTwo);

        var result = planetRepository.findByName("correct-name", of(0, 50));

        then(result.getContent()).containsOnly(planetOne);

        createdPlanets = List.of(planetOne, planetTwo);
    }

    @Test
    void shouldFindPartialName() {
        var inputOne = new Planet();
        var inputTwo = new Planet();

        inputOne.setName("name-one");
        inputTwo.setName("name-two");

        var planetOne = planetRepository.save(inputOne);
        var planetTwo = planetRepository.save(inputTwo);

        var result = planetRepository.findByName("name", of(0, 50));

        then(result.getContent()).contains(planetOne, planetTwo);

        createdPlanets = List.of(planetOne, planetTwo);
    }

    @AfterEach
    void tearDown() {
        planetRepository.deleteAll(createdPlanets);
    }

}
