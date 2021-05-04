package b2w.test.star.wars.planets.repositories;

import b2w.test.star.wars.planets.entities.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanetRepository extends MongoRepository<Planet, String> {
}
