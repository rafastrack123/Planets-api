package b2w.test.star.wars.planets.repositories;

import b2w.test.star.wars.planets.entities.Planet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PlanetRepository extends MongoRepository<Planet, String> {

    @Query("{ name: {$regex: ?0 }})")
    Page<Planet> findByName(String name, Pageable pageable);
}
