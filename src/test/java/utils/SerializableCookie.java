package utils;

import org.openqa.selenium.Cookie;

public class SerializableCookie {
    private String name;
    private String value;

    public SerializableCookie(Cookie cookie) {
        this.name = cookie.getName();
        this.value = cookie.getValue();
    }

    public Cookie toCookie() {
        Cookie cookie = new Cookie(this.name, this.value);
        return cookie;
    }

}
