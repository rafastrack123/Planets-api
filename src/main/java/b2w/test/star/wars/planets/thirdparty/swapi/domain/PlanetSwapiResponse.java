package b2w.test.star.wars.planets.thirdparty.swapi.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class PlanetSwapiResponse {

    private String name;
    private List<String> films = new ArrayList<>();

}
