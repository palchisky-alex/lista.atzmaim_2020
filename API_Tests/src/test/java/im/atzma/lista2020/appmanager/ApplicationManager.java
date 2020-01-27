package im.atzma.lista2020.appmanager;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.fail;

public class ApplicationManager {
    Properties properties;
    AccountCreationHelper accountCreationHelper;

    public ApplicationManager(String browser) {
        properties = new Properties();
    }

    public void init() throws InterruptedException, IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));


        accountCreationHelper = new AccountCreationHelper();

    }


    public void stop() throws InterruptedException, IOException {

    }

    public AccountCreationHelper accountCreationHelper() {return accountCreationHelper;}

}
