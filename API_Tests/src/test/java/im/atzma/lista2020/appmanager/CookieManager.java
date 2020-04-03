package im.atzma.lista2020.appmanager;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.RedirectConfig.redirectConfig;

public class CookieManager {
//    9olovoj99@lista.app
//            dumaj999

    public Map<String, String> createLoginCookie() {
        baseURI = "https://lista.atzma.im";
        RestAssured.config = config().redirect(redirectConfig().followRedirects(true).and().maxRedirects(10)).and().
                and().config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        RestAssured.useRelaxedHTTPSValidation();

        Response response = given().
                header("Content-Type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With","XMLHttpRequest").
                formParam("time_zone", "Asia/Jerusalem").
                formParam("email", "Katalon@gmail.com").
                formParam("pass", "Pa$$w@rd").
                when().
                post("/check-login");
        System.out.println("create login cookie");
        Map<String, String> loginCookie = response.getCookies();
        loginCookie.forEach((k, v) -> System.out.println("login cookie : " + k + " Value : " + v));
        return loginCookie;
    }


}
