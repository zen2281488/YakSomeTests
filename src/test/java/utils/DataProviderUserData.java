package utils;

import org.testng.annotations.DataProvider;

public class DataProviderUserData {

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
