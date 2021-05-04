package b2w.test.star.wars.planets.utils;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;

public class StringUtilsTest {

    @Test
    void isBlank() {
        then(StringUtils.isBlank(null)).isTrue();
        then(StringUtils.isBlank("      ")).isTrue();
        then(StringUtils.isBlank("")).isTrue();
        then(StringUtils.isBlank("text-value")).isFalse();
    }

}
