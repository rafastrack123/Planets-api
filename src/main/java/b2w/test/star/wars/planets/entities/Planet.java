package b2w.test.star.wars.planets.entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("Planet")
public class Planet {

    private String name;
    private String terrain;
    private String climate;
    private int movieAppearances;

}
