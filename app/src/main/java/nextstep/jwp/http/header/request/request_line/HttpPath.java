package nextstep.jwp.http.header.request.request_line;

import java.util.HashMap;
import java.util.Map;
import nextstep.jwp.http.common.ParameterExtractor;

public class HttpPath {

    private final String uri;
    private final Map<String, String> parameters;

    public HttpPath(String uri) {
        this.uri = extractPath(uri);
        this.parameters = extractParams(uri);
    }

    private String extractPath(String uri) {
        if(!uri.contains("?")) {
            return uri;
        }

        return uri.substring(0, uri.indexOf("?"));
    }

    private Map<String, String> extractParams(String uri) {
        if(!uri.contains("?")) {
            return new HashMap<>();
        }

        return ParameterExtractor.extract(uri);
    }

    public boolean containsQueryParam(String key) {
        return parameters.containsKey(key);
    }

    public String getParam(String key) {
        return parameters.get(key);
    }

    public String getUri() {
        return uri;
    }
}