package im.atzma.lista2020.tests;

import com.mashape.unirest.http.exceptions.UnirestException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.RedirectConfig.redirectConfig;


public class RestTests {

    @Test
    public void testLogin() throws IOException, UnirestException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        baseURI = "https://lista.atzma.im";
        RestAssured.config = config().redirect(redirectConfig().followRedirects(true).and().maxRedirects(10)).and().
                and().config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        RestAssured.useRelaxedHTTPSValidation();

       Response response = given().
                header("Content-Type", "application/x-www-form-urlencoded").
                header("user-agent","alpalch-qpEzhaOvY0Ecb4e0").
                formParam("time_zone","Asia/Jerusalem").
                formParam(  "email","katalon13@gmail.com").
                formParam( "pass","Pa$$w@rd").
                when().
                post("/check-login");

        Map<String, String> allCookies = response.getCookies();
        allCookies.forEach((k,v)->System.out.println("Key : " + k + " Value : " + v));


        given().cookies(response.getCookies()).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent","alpalch-qpEzhaOvY0Ecb4e0").
                formParam("name", "TestCategory2").
                formParam("duration", 300).
                formParam("price", 50).
                formParam("color","#50e3c1").
                formParam("category_id", 2).
                formParam("added", "2020-01-26 21:39:41").
                when().
                post("/catalog/services").
                then().
                assertThat().
                statusCode(201);

        Response response2 = given().cookies(response.getCookies()).
                header("user-agent","alpalch-qpEzhaOvY0Ecb4e0").
                when().
                get("/calendar?start=2020-01-25T00:00:00&end=2020-01-27T23:59:59&worker_id=1");
        System.out.println(response2.body().asString());


    }

}
