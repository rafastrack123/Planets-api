package b2w.test.star.wars.planets.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import b2w.test.star.wars.planets.entities.Planet;
import b2w.test.star.wars.planets.exceptions.NotFoundException;
import b2w.test.star.wars.planets.repositories.PlanetRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

    @Mock
    private PlanetRepository planetRepository;

    @InjectMocks
    private PlanetService planetService;

    @Test
    void getByIdShouldThrowNotFoundException() {
        var planetId = "planet-id";

        given(planetRepository.findById(planetId)).willReturn(Optional.empty());

        assertThatCode(() -> planetService.getById(planetId))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void getById() {
        var planetId = "planet-id";
        var planet = mock(Planet.class);

        given(planetRepository.findById(planetId)).willReturn(Optional.of(planet));

        var result = planetService.getById(planetId);

        then(result).isEqualTo(planet);
    }

    @Test
    void deleteShouldThrowNotFoundException() {
        var planetId = "planet-id";

        given(planetRepository.findById(planetId)).willReturn(Optional.empty());

        assertThatCode(() -> planetService.delete(planetId))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void delete() {
        var planetId = "planet-id";
        var planet = mock(Planet.class);

        given(planetRepository.findById(planetId)).willReturn(Optional.of(planet));

        planetService.delete(planetId);

        verify(planetRepository).delete(planet);
    }

}
