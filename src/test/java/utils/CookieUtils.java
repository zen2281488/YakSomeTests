package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CookieUtils {
    static Gson gson = new Gson();

    public static void saveCookiesToFile(Set<Cookie> cookies, String filePath) {
        List<SerializableCookie> serializableCookies = new ArrayList<>();
        for (Cookie cookie : cookies) {
            serializableCookies.add(new SerializableCookie(cookie));
        }

        String json = gson.toJson(serializableCookies);

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<Cookie> loadCookiesFromFile(String filePath) {
        Set<SerializableCookie> serializableCookies = null;

        try (FileReader reader = new FileReader(filePath)) {
            serializableCookies = gson.fromJson(reader, new TypeToken<Set<SerializableCookie>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<Cookie> cookies = new HashSet<>();
        if (serializableCookies != null) {
            for (SerializableCookie serializableCookie : serializableCookies) {
                cookies.add(serializableCookie.toCookie());
            }
        }

        return cookies;
    }

    public static boolean cookieExist(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            gson.fromJson(reader, new TypeToken<Set<SerializableCookie>>() {
            }.getType());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void addCookie(WebDriver driver, Set<Cookie> cookiess) {
        for (Cookie cookie : cookiess) {
            driver.manage().addCookie(cookie);
        }
    }

    public static class SerializableCookie {
        private final String name;
        private final String value;

        public SerializableCookie(Cookie cookie) {
            this.name = cookie.getName();
            this.value = cookie.getValue();
        }

        public Cookie toCookie() {
            return new Cookie(this.name, this.value);
        }

    }
}


