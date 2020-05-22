package im.atzma.lista2020.Tests;
import im.atzma.lista2020.appmanager.CustomAllureRestAssured;
import im.atzma.lista2020.appmanager.LogListener;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
@Listeners(LogListener.class)
public class AccountCreation_UI_API_Tests extends TestBase_UI_API {


    String key = "7b7a53e239400a13bd6be6c91c4f6c4e";
    String value;
    String bodyS;
    String headerValue;
    String accountCreationResponse;
    Response get_response;
    Response delete_response;
    Response post_response;
    Map<String, String> loginCookie = new HashMap<>();
    List<String> cookies = new ArrayList<>();
    Map<String, String> accounts = new HashMap<>();
    String currentDate = LocalDate.now().toString();
    LocalTime time = LocalTime.now();
    DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss");
    String currentTime = time.format(dtf2);
    @Test(priority = 1)
    public void createAccount() throws FileNotFoundException {
     //   app.accountCreation_UI_API_Helper().createAccount().then().assertThat().statusCode(201);

        Random random = new Random();
        int randomInt = random.nextInt();
        String random_for_mail = "api_test_ui" + randomInt;

        System.out.println("=== CREATE RANDOM ACCOUNT, STATUS MUST BE 201 ===");
        accounts.put(random_for_mail + "@gmail.com", "Pa$$w@rd");

        RequestSpecification specification = new RequestSpecBuilder().addFilter(new AllureRestAssured()).build();

//        PrintStream fileOutPutStream = new PrintStream(new File("somefile.txt"));
//        config = config().logConfig(new LogConfig().defaultStream(fileOutPutStream));
//.filters(new CustomAllureRestAssured(), new RequestLoggingFilter())

        post_response = given().log().all().filter(new CustomAllureRestAssured()).
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
                post("/signup-new-account");
        bodyS =   post_response.getBody().asString();
        accountCreationResponse = post_response.asString();

        loginCookie = post_response.getCookies();

        for (Map.Entry<String, String> entry : loginCookie.entrySet()) {
            value = entry.getValue();
            cookies.add(value);
            System.out.println("Cookie value account creation : " + value);
        }
        System.out.println("Create account status: " + post_response.getStatusCode());


    }


    @Test(priority = 2)
    public void verifyAccountCreation() {
        assertEquals(app.accountCreation_UI_API_Helper().verifyAccountCreation(), "/en/calendar");
    }


    @Test(priority = 3)
    public void deleteAccount() {
        app.accountCreation_UI_API_Helper().deleteAccount().then().assertThat().statusCode(401);
    }



    @Test(priority = 4)
    public void verifyAccountDeletion() {
        assertEquals(app.accountCreation_UI_API_Helper().verifyAccountDeletion(), "/he/login");
    }


}