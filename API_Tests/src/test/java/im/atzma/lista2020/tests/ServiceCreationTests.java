package im.atzma.lista2020.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ServiceCreationTests extends TestBase {

    @Test
    public void creatNewService() {
        app.serviceCreationHelper().getServiceList();
       // app.serviceCreationHelper().createService();
//        app.serviceCreationHelper().deleteService();
    }

}
