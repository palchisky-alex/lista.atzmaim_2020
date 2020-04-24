package lista2020.appmanager;

import io.restassured.response.Response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static io.restassured.RestAssured.*;

public class AccountCreationHelper {
    CookieManager cookieManager;
    String key = "7b7a53e239400a13bd6be6c91c4f6c4e";
    String value;
    Response get_response;
    Response delete_response;
    Response post_response;
    Map<String, String> loginCookie = new HashMap<>();
    List<String> cookies = new ArrayList<>();
    String currentDate = LocalDate.now().toString();
    LocalTime time = LocalTime.now();
    DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("hh:mm:ss");
    String currentTime = time.format(dtf2);

    public String businessTypeResponse() {
        get_response = given().
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("lang", "en").
                when().
                get("/business_types?lang=en").then().extract().response();
        String responseString = get_response.asString();
        System.out.println(responseString);
        return responseString;
    }

    public String createAccount(String mail, String pass, String tel, String timezone, String country, String city) {

        post_response = given().
                header("Content-Type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                formParam("added", currentDate + " " + currentTime).
                formParam("email", mail).
                formParam("pass", pass).
                formParam("phone", tel).
                formParam("permit_ads", "true").
                formParam("business_types", "[1]").
                formParam("lang", "en").
                formParam("timezone", timezone).
                formParam("country", country).
                formParam("city", city).
                when().log().all().
                post("/signup-new-account").then().assertThat().statusCode(201).
                extract().response();

        String responseString = post_response.asString();
        System.out.println("Account creation response: " + responseString);
        System.out.println("Create account code: " + post_response.getStatusCode());

//        loginCookie = post_response.getCookies();
//
//        for (Map.Entry<String, String> entry : loginCookie.entrySet()) {
//            key = entry.getKey();
//            value = entry.getValue();
//            cookies.add(value);
//        }

        return responseString;
    }

    public Integer deleteAccount(String email, String pass) {
        loginCookie = login(email, pass);

        System.out.println("Cookies map size: " + loginCookie.size());
        for (Map.Entry<String, String> entry : loginCookie.entrySet()) {
            value = entry.getValue();
            cookies.add(value);
        }

        int AccountNumber = 0;

        delete_response = given().cookies(key, value).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                when().
                delete("/settings/business/account").
                then().assertThat().statusCode(401).
                extract().response();
        AccountNumber = AccountNumber + 1;
        System.out.println("Delete account " + AccountNumber + " code: " + delete_response.getStatusCode());

        return cookies.size();
    }

    public Map<String, String> login(String email, String pass) {

        Response response = given().
                header("Content-Type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                formParam("time_zone", "Asia/Jerusalem").
                formParam("email", email).
                formParam("pass", pass).
                when().
                post("/check-login");
        Map<String, String> loginCookie = response.getCookies();
        loginCookie.forEach((k, v) -> System.out.println("login cookie : " + k + " Value : " + v));
        return loginCookie;
    }
}



