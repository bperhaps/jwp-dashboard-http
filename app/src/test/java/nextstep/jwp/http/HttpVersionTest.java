package nextstep.jwp.http;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HttpVersionTest {

    @Test
    void from() {
        assertThat(HttpVersion.from("HTTP/1.1")).isEqualTo(HttpVersion.HTTP1_1);
    }

    @Test
    void from_invalidVersion() {
        assertThatThrownBy(() -> HttpVersion.from("HTTP/1.2"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}