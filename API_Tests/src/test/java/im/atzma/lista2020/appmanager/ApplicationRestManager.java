package im.atzma.lista2020.appmanager;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.fail;

public class ApplicationRestManager {

    ServiceCreationHelper serviceCreationHelper;
    ClientCreationHelper clientCreationHelper;
    AppointmentRestHelper appointmentHelper;
    RestRequests restRequests;
    Properties properties = new Properties();
    CookieManager cookieManager = new CookieManager();

    public void init() throws IOException, InterruptedException {

        Map<String, String> firstCookie = cookieManager.createLoginCookie();
        firstCookie.forEach((k,v)->System.out.println("Key : " + k + " Value : " + v));
        restRequests = new RestRequests(firstCookie);
        restRequests.cleaner();
        serviceCreationHelper = new ServiceCreationHelper(firstCookie);
        clientCreationHelper = new ClientCreationHelper(firstCookie);
        appointmentHelper = new AppointmentRestHelper(firstCookie);

    }




    public void stop() throws InterruptedException, IOException {
        cookieManager.deleteAccount();
    }

    //    public CookieManager cookieManager() {return cookieManager;}
    public ServiceCreationHelper serviceCreationHelper() {
        return serviceCreationHelper;
    }
    public ClientCreationHelper clientCreationHelper() { return  clientCreationHelper; }
    public AppointmentRestHelper appointmentHelper() { return  appointmentHelper; }
    public RestRequests restRequests() {return  restRequests; }

}
