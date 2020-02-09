package im.atzma.lista2020.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateServiceTests extends TestBase {

    @Test
    public void creatNewService() {
        app.serviceCreationHelper().createService();
    }

    @Test
    public void appointmentList() {
        app.serviceCreationHelper().createService2();
    }

}
