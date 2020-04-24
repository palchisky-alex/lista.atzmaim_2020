package im.atzma.lista2020.appmanager;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.RedirectConfig.redirectConfig;

public class CookieManager {
    String currentDate = LocalDate.now().toString();
    LocalTime time = LocalTime.now();
    DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("hh:mm:ss");
    String currentTime = time.format(dtf2);

    Random random = new Random();
    int randomInt = random.nextInt();
    String randomMail = "api_tests" + randomInt + "@gmail.com";

    Response post_response;
    Response delete_response;
    Map<String, String> loginCookie;

    public Map<String, String> createLoginCookie() {
        baseURI = "https://lista.atzma.im";
        RestAssured.config = config().redirect(redirectConfig().followRedirects(true).and().maxRedirects(10)).and().
                and().config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        RestAssured.useRelaxedHTTPSValidation();

        System.out.println("Create random account - " + randomMail);

        post_response = given().
                header("Content-Type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                formParam("added", currentDate + " " + currentTime).
                formParam("email", randomMail).
                formParam("pass", "Pa$$w@rd").
                formParam("phone", "0547613154").
                formParam("permit_ads", "true").
                formParam("business_types", "[1]").
                formParam("lang", "en").
                formParam("timezone", "Asia/Jerusalem").
                formParam("country", "IL").
                formParam("city", "Tel Aviv").
                when().log().all().
                post("/signup-new-account").then().assertThat().statusCode(201).
                extract().response();

        String responseString = post_response.asString();
        System.out.println("Account creation response: " + responseString);
        System.out.println("Create account code: " + post_response.getStatusCode());

//        ==== LOGIN REQUEST ====
//        Response response = given().
//                header("Content-Type", "application/x-www-form-urlencoded").
//                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
//                header("X-Requested-With","XMLHttpRequest").
//                formParam("time_zone", "Asia/Jerusalem").
//                formParam("email", randomMail).
//                formParam("pass", "Pa$$w@rd").
//                when().
//                post("/check-login");
//        System.out.println("create login cookie");
//        =====================================
        loginCookie = post_response.getCookies();
        loginCookie.forEach((k, v) -> System.out.println("login cookie : " + k + " Value : " + v));
        return loginCookie;
    }


    public void deleteAccount() {

        System.out.println("Cookies map size: " + loginCookie.size());
        for (Map.Entry<String, String> entry : loginCookie.entrySet()) {
            String value = entry.getValue();
            String key = entry.getKey();

            delete_response = given().cookies(key, value).
                    header("content-type", "application/x-www-form-urlencoded").
                    header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                    header("X-Requested-With", "XMLHttpRequest").
                    when().
                    delete("/settings/business/account").
                    then().assertThat().statusCode(401).
                    extract().response();

            System.out.println("RANDOM ACCOUNT " + randomMail + " WAS DELETED / STATUS CODE: " + delete_response.getStatusCode());
        }
    }
}
