package b2w.test.star.wars.planets.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Planet")
@Getter
@Setter
@ToString
public class Planet {

    @Id
    private String id;
    private String name;
    private String terrain;
    private String climate;
    private int movieAppearances;

}
