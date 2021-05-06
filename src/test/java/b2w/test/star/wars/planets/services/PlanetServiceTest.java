package b2w.test.star.wars.planets.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import b2w.test.star.wars.planets.entities.Planet;
import b2w.test.star.wars.planets.exceptions.NotFoundException;
import b2w.test.star.wars.planets.repositories.PlanetRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

    @Mock
    private MovieAppearancesService movieAppearancesService;
    @Mock
    private PlanetRepository planetRepository;

    @InjectMocks
    private PlanetService planetService;

    @Test
    void listByName() {
        var name = "planet-name";
        var pageable = mock(Pageable.class);
        var planets = List.of(mock(Planet.class));
        var planetsPage = new PageImpl<>(planets);

        given(planetRepository.findByName(name, pageable)).willReturn(planetsPage);

        var result = planetService.list(name, pageable);

        verify(planetRepository, never()).findAll(pageable);

        then(result).isEqualTo(planetsPage);
    }

    @Test
    void listAll() {
        var pageable = mock(Pageable.class);
        var planets = List.of(mock(Planet.class));
        var planetsPage = new PageImpl<>(planets);

        given(planetRepository.findAll(pageable)).willReturn(planetsPage);

        var result = planetService.list(null, pageable);

        verify(planetRepository, never()).findByName(anyString(), eq(pageable));

        then(result).isEqualTo(planetsPage);
    }

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
    void save() {
        var planet = mock(Planet.class);

        given(movieAppearancesService.getMovieAppearances(planet)).willReturn(5);

        planetService.save(planet);

        verify(planet).setMovieAppearances(5);
        verify(planetRepository).save(planet);
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
