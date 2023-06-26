package utils;

import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;

public class DataProviderUserData {
@Step("Получение данных авторизации Учетной Записи")
    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {
        return new Object[][]{
                {ConfProperties.getProperty("correctLogin"), ConfProperties.getProperty("correctPassword")},
                {ConfProperties.getProperty("correctLogin"), ConfProperties.getProperty("wrongPassword")},
                {ConfProperties.getProperty("wrongLogin"), ConfProperties.getProperty("correctPassword")},
                {ConfProperties.getProperty("correctLogin"), ConfProperties.getProperty("correctPassword")}
        };
    }
}
