package b2w.test.star.wars.planets.exceptions;

import static java.lang.String.format;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String resourceNameKey, String resourceId) {
        super(format("%s %s not found", resourceNameKey, resourceId));
    }

}
