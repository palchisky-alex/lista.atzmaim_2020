package im.atzma.lista2020.Tests;

import io.restassured.response.Response;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.Header;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.StringBody.exact;
import static org.testng.Assert.assertEquals;

@Listeners(LogListener.class)
public class AccountCreation_UI_API_Tests extends TestBase_UI_API {

    @Test(priority = 1)
    public void createAccount() {
        String currentDate = LocalDate.now().toString();
        LocalTime time = LocalTime.now();
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss");
        String currentTime = time.format(dtf2);
        Random random = new Random();
        int randomInt = random.nextInt();
        String random_for_mail = "api_test_ui" + randomInt;

        System.out.println("=== CREATE RANDOM ACCOUNT, STATUS MUST BE 201 ===");
        Response post_response = given().log().all().
                header("Content-Type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                formParam("added", currentDate + " " + currentTime).
                formParam("email", random_for_mail + "@gmail.com").
                formParam("pass", "Pa$$w@rd").
                formParam("phone", "0547613154").
                formParam("permit_ads", "true").
                formParam("business_types", "[1]").
                formParam("lang", "en").
                formParam("timezone", "Asia/Jerusalem").
                formParam("country", "IL").
                formParam("city", "Tesl Aviv").
                when().
                post("/signup-new-account").then().extract().response();
        assertEquals(post_response.statusCode(), 201);

        new MockServerClient("127.0.0.1", 1080)
                .when(
                        request().withMethod("POST")
                .withPath("/en/calendar")
                .withHeader("\"Content-type\", \"application/json\"")
                .withBody(exact("{username: 'foo', password: 'bar'}")),
                exactly(1))
                .respond(
                response()
                        .withStatusCode(201)
                        .withHeaders(
                                new Header("Content-Type", "application/json; charset=utf-8"),
                                new Header("Cache-Control", "public, max-age=86400"))
                        .withBody("{ message: 'incorrect username and password combination' }")
                        .withDelay(TimeUnit.SECONDS,1)
        );
    }

//    @Test(priority = 1)
//    public void createAccount2() {
//        app.accountCreation_UI_API_Helper().createAccount().then().assertThat().statusCode(201);
//    }
//
//    @Test(priority = 2)
//    public void verifyAccountCreation() {
//        assertEquals(app.accountCreation_UI_API_Helper().verifyAccountCreation(), "/en/calendar");
//    }
//
//
//    @Test(priority = 3)
//    public void deleteAccount() {
//        app.accountCreation_UI_API_Helper().deleteAccount().then().assertThat().statusCode(401);
//    }
//
//
//
//    @Test(priority = 4)
//    public void verifyAccountDeletion() {
//        assertEquals(app.accountCreation_UI_API_Helper().verifyAccountDeletion(), "/he/login");
//    }


}