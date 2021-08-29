package nextstep.jwp.http.message.element.cookie;

import java.util.*;
import java.util.stream.Collectors;

public class ProxyHttpCookie implements Cookie {

    private Cookie cookie;
    private final Map<String, String> changedCookie;

    public ProxyHttpCookie() {
        this(null);
    }

    public ProxyHttpCookie(Cookie cookie) {
        this.cookie = cookie;
        this.changedCookie = new HashMap<>();
    }

    public boolean isChanged() {
        return this.changedCookie.size() != 0;
    }

    @Override
    public Optional<String> get(String key) {
        createCookieIfNotExist();
        return cookie.get(key);
    }

    @Override
    public List<String> getKeys() {
        createCookieIfNotExist();
        return cookie.getKeys();
    }

    @Override
    public void put(String key, String value) {
        createCookieIfNotExist();
        changedCookie.put(key, value);
        cookie.put(key, value);
    }

    @Override
    public int size() {
        createCookieIfNotExist();
        return cookie.size();
    }

    @Override
    public String asString() {
        createCookieIfNotExist();
        return cookie.asString();
    }

    public String asStringOfChanged() {
        return changedCookie.entrySet().stream()
                .map(e -> String.format("%s=%s", e.getKey(), e.getValue()))
                .collect(Collectors.joining("; "));
    }

    private void createCookieIfNotExist() {
        if (Objects.isNull(this.cookie)) {
            this.cookie = new HttpCookie();
        }
    }
}
