package nextstep.jwp.http.controller.custom.login;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import nextstep.jwp.fixture.Fixture;
import nextstep.jwp.http.exception.UnauthorizedException;
import nextstep.jwp.http.request.HttpRequest;
import nextstep.jwp.http.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GetLoginControllerTest {

    @DisplayName("로그인을 시도한다.")
    @ParameterizedTest
    @MethodSource("parametersForDoService")
    void doService(String uri, int code, String content) {
        final GetLoginController getLoginController = new GetLoginController();
        final HttpRequest httpRequest = new HttpRequest(Fixture.getHttpRequest(uri));

        final Response response = getLoginController.doService(httpRequest);
        assertThat(response.asString()).contains(String.valueOf(code));
        assertThat(response.asString()).contains(String.valueOf(content));
    }

    private static Stream<Arguments> parametersForDoService() {
        return Stream.of(
            Arguments.of("/login", 200, "로그인"),
            Arguments.of("/login?password=123", 200, "로그인"),
            Arguments.of("/login?account=1", 200, "로그인"),
            Arguments.of("/login?account=gugu&password=password", 302, "302")
        );
    }

    @DisplayName("로그인 실패시 예외.")
    @Test
    void doService_invalid_fail() {
        final GetLoginController getLoginController = new GetLoginController();
        final HttpRequest httpRequest =
            new HttpRequest(Fixture.getHttpRequest("/login?account=gugu&password=123"));

        assertThatThrownBy(() -> getLoginController.doService(httpRequest))
            .isInstanceOf(UnauthorizedException.class);
    }

    @DisplayName("컨트롤러 실행 조건을 확인한다.")
    @Test
    void isSatisfiedBy() {
        final GetLoginController getLoginController = new GetLoginController();
        final HttpRequest httpRequest = new HttpRequest(Fixture.getHttpRequest("/login"));

        assertThat(getLoginController.isSatisfiedBy(httpRequest)).isTrue();
    }
}