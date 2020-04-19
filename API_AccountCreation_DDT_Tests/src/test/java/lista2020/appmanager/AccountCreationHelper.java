package lista2020.appmanager;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.RedirectConfig.redirectConfig;

public class AccountCreationHelper {
    CookieManager cookieManager;
    String key;
    String value;
    Response get_response;
    Response delete_response;
    Response post_response;

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

    public void createAccount() {

        post_response = given().
                header("Content-Type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                formParam("added", "2020-04-20 21:47:06").
                formParam("email", "katalon_post@gmail.com").
                formParam("pass", "Pa$$w@rd").
                formParam("phone", "0547613154").
                formParam("permit_ads", "true").
                formParam("business_types", "[12]").
                formParam("lang", "he").
                formParam("timezone", "Asia/Jerusalem").
                formParam("country", "IL").
                formParam("city", "Netanya").
                when().log().all().
                post("/signup-new-account").then().assertThat().statusCode(201).
                extract().response();

        String responseString = post_response.asString();
        System.out.println("Account creation response: " + responseString);
        System.out.println("Create account code: " + post_response.getStatusCode());

        Map<String, String> loginCookie = post_response.getCookies();
        for (Map.Entry<String, String> entry : loginCookie.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
        }

    }

    public void deleteAccount() {
        delete_response = given().cookies(key, value).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                when().
                delete("/settings/business/account").
                then().assertThat().statusCode(401).
                extract().response();

        System.out.println("Delete account code: " + delete_response.getStatusCode());
    }
}
