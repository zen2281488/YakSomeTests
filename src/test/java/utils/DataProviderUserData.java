package utils;

import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;

public class DataProviderUserData {
@Step("Получение данных авторизации Учетной Записи")
    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {
        return new Object[][]{
                {ConfProperties.getProperty("p2username"), ConfProperties.getProperty("p2pass")},
                {ConfProperties.getProperty("p2username"), ConfProperties.getProperty("wrongPassword")},
                {ConfProperties.getProperty("wrongLogin"), ConfProperties.getProperty("p2pass")},
                {ConfProperties.getProperty("p2username"), ConfProperties.getProperty("p2pass")}
        };
    }
}
