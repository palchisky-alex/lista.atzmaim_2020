package im.atzma.lista2020.Tests;

import im.atzma.lista2020.appmanager.LogListener;
import io.qameta.allure.Description;
import org.apache.commons.logging.Log;
import org.approvaltests.Approvals;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static io.restassured.RestAssured.given;

@Listeners({LogListener.class })
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
        given().
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
                post("/signup-new-account").then().assertThat().statusCode(201);
       // Assert.assertEquals(app.accountCreation_UI_API_Helper().createAccount(), 201);
    }


    @Test(priority = 2)
    public void verifyAccountCreation() {

        Assert.assertEquals(app.accountCreation_UI_API_Helper().verifyAccountCreation(), "/en/calendar");
    }


    @Test(priority = 3)
    public void deleteAccount() {
        Assert.assertEquals(app.accountCreation_UI_API_Helper().deleteAccount(), 401);
    }


    @Test(priority = 4)
    @Description("verifyAccountDeletion")
    public void verifyAccountDeletion() {
        Assert.assertEquals(app.accountCreation_UI_API_Helper().verifyAccountDeletion(), "/he/login");
    }


    @Test(priority = 5)
    public void getBusinessTypeJSON() {
        Approvals.verify(app.accountCreation_UI_API_Helper().businessTypeResponse());
    }


}