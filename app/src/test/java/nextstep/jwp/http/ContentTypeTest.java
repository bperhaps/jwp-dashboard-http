package nextstep.jwp.http;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ContentTypeTest {

    @ParameterizedTest
    @CsvSource(value = {"a.txt:text/plain", "a.js:application/js", "a.css:text/css", "a.html:text/html"}, delimiterString = ":")
    void from(String input, String expected) {
        final String actual = ContentType.from(input);

        assertThat(actual).isEqualTo(expected);
    }
}