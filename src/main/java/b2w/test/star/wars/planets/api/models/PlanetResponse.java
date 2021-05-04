package b2w.test.star.wars.planets.api.models;

import lombok.Data;

@Data
public class PlanetResponse {

    private String id;
    private String name;
    private String terrain;
    private String climate;
    private int movieAppearances;

}
