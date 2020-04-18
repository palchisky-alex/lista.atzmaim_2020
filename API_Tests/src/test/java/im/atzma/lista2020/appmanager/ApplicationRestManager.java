package im.atzma.lista2020.appmanager;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.fail;

public class ApplicationRestManager {

    AccountCreationHelper accountCreationHelper;
    ServiceCreationHelper serviceCreationHelper;
    ClientCreationHelper clientCreationHelper;
    AppointmentHelper appointmentHelper;
    RestRequests restRequests;
    Properties properties = new Properties();

    public void init() throws IOException, InterruptedException {

        CookieManager cookieManager = new CookieManager();
        Map<String, String> firstCookie = cookieManager.createLoginCookie();
        firstCookie.forEach((k,v)->System.out.println("Key : " + k + " Value : " + v));
        restRequests = new RestRequests(firstCookie);
       // restRequests.cleaner();
        serviceCreationHelper = new ServiceCreationHelper(firstCookie);
        clientCreationHelper = new ClientCreationHelper(firstCookie);
        appointmentHelper = new AppointmentHelper(firstCookie);





    }




    public void stop() throws InterruptedException, IOException {

    }

    //    public CookieManager cookieManager() {return cookieManager;}
    public AccountCreationHelper accountCreationHelper() {
        return accountCreationHelper;
    }
    public ServiceCreationHelper serviceCreationHelper() {
        return serviceCreationHelper;
    }
    public ClientCreationHelper clientCreationHelper() { return  clientCreationHelper; }
    public  AppointmentHelper appointmentHelper() { return  appointmentHelper; }
    public RestRequests restRequests() {return  restRequests; }

}
