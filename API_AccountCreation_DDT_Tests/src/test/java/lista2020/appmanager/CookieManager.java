package lista2020.appmanager;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class CookieManager {
//    9olovoj99@lista.app
//            dumaj999

    public Map<String, String> createLoginCookie() {

        Response response = given().
                header("Content-Type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With","XMLHttpRequest").
                formParam("time_zone", "Asia/Jerusalem").
                formParam("email", "katalon222@mail.ru").
                formParam("pass", "123").
                when().
                post("/check-login");
        System.out.println("create login cookie");
        Map<String, String> loginCookie = response.getCookies();
      //  AccountCreationHelper accountCreationHelper = new AccountCreationHelper(loginCookie);
        loginCookie.forEach((k, v) -> System.out.println("login cookie : " + k + " Value : " + v));

        return loginCookie;
    }


}
