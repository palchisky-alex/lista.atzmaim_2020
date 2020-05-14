package im.atzma.lista2020.Tests;

import im.atzma.lista2020.appmanager.LogListener;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.logging.Log;
import org.approvaltests.Approvals;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class AccountCreation_UI_API_Tests extends TestBase_UI_API {

    @Test(priority = 1)
    @Step("create account - POST request")
    public void createAccount() {
       app.accountCreation_UI_API_Helper().createAccount().then().assertThat().statusCode(201);
    }


    @Test(priority = 2)
    public void verifyAccountCreation() {

        assertEquals(app.accountCreation_UI_API_Helper().verifyAccountCreation().asString(), "/en/calendar");
    }


    @Test(priority = 3)
    public void deleteAccount() {
        app.accountCreation_UI_API_Helper().deleteAccount().then().assertThat().statusCode(401);
    }


    @Test(priority = 4)
    @Description("verifyAccountDeletion")
    public void verifyAccountDeletion() {
        assertEquals(app.accountCreation_UI_API_Helper().verifyAccountDeletion(), "/he/login");
    }


    @Test(priority = 5)
    public void getBusinessTypeJSON() {
        Approvals.verify(app.accountCreation_UI_API_Helper().businessTypeResponse());
    }

}