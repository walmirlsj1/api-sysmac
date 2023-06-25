package br.com.limac.sysmac.security.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

public class CookieService {
    public static void setCookie(HttpServletResponse response, String key, String value, int timelife) {
        Cookie cookie = new Cookie(key, URLEncoder.encode(value, StandardCharsets.UTF_8));
        cookie.setMaxAge(timelife);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);
    }

    public static String getCookie(HttpServletRequest request, String key) {
        String value = Optional.ofNullable(request.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(cookie -> key.equals(cookie.getName()))
                        .findAny())
                .map(Cookie::getValue)
                .orElse(null);

        return (value != null) ? URLDecoder.decode(value, StandardCharsets.UTF_8) : null;

    }

}