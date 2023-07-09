package utils;

import io.qameta.allure.Step;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class DataProviderUserData {
    @Step("Получение данных авторизации Учетной Записи")


    public static Stream<Arguments> getLogData() {
        return Stream.of(Arguments.of(
                        ConfProperties.getProperty("p2username"), ConfProperties.getProperty("p2pass")
                ),
                Arguments.of(
                        ConfProperties.getProperty("p2username"), ConfProperties.getProperty("wrongPassword")
                ),
                Arguments.of(
                        ConfProperties.getProperty("wrongLogin"), ConfProperties.getProperty("p2pass")
                ),
                Arguments.of(
                        ConfProperties.getProperty("p2username"), ConfProperties.getProperty("p2pass")
                ));
    }
}

