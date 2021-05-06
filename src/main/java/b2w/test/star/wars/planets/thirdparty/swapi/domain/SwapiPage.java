package b2w.test.star.wars.planets.thirdparty.swapi.domain;

import java.util.List;
import lombok.Data;

@Data
public class SwapiPage<T> {

    private int count;
    private String next;
    private String previous;
    private List<T> results;

}
