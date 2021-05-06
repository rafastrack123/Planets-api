package b2w.test.star.wars.planets.thirdparty.swapi.domain;

import static b2w.test.star.wars.planets.utils.StringUtils.isBlank;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class PlanetSearchSwapiResponse {

    private int count;
    private String next;
    private String previous;
    private List<PlanetSwapiResponse> results = new ArrayList<>();

    public boolean doesNotHaveNext() {
        return isBlank(next);
    }

}
