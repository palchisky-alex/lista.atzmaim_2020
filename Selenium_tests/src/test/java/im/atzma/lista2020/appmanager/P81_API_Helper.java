package im.atzma.lista2020.appmanager;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.RedirectConfig.redirectConfig;


public class P81_API_Helper {
    Response post_response;
    Response delete_response;
    String key = null;
    String value = null;
    Map<String, String> loginCookie;

    public void p81_cookie() {


        baseURI = "https://auth.perimeter81.com";
        RestAssured.config = config().redirect(redirectConfig().followRedirects(true).and().maxRedirects(10)).and().
                and().config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        RestAssured.useRelaxedHTTPSValidation();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("client_id", "3tEhXmQE6tOF11srAgqEQa5U2e2qCPz6");
        jsonAsMap.put("credential_type", "http://auth0.com/oauth/grant-type/password-realm");
        jsonAsMap.put("password", "!z8Jk4KpT9cCtd3");
        jsonAsMap.put("realm", "perimeter81-db-single");
        jsonAsMap.put("username", "alex.palchisky@gmail.com");

        post_response = given().log().all().
                header("Content-Type", "application/json").
                header("Referer", "https://testcompany4.perimeter81.com/").
                header("User-Agent", "PostmanRuntime/7.26.5").
                header("Host", "auth.perimeter81.com").
                body(jsonAsMap).
                when().log().all().
                post("/co/authenticate").then().assertThat().statusCode(200).
                extract().response();

        //Cookies pot_response = post_response.detailedCookies();
        loginCookie = post_response.getCookies();
        System.out.println("COOKA: " + loginCookie);
      // getMembers_API(pot_response);
        loginCookie.forEach((k, v) -> System.out.println("login cookie : " + k + " Value : " + v));

    }

    public void getMembers_API() {

        Response response = given().log().all().
                header("Cookie", "AWSALB=JxKB7w6ta4aeuLwSi8mO18B6sDs4Mv/1K5pYSQd7pX73GpeLsl45wsYy6F8tCNKNYNFanNo2g0ZjLEgHCIouMwZ8ap5oHdQyvZA36KiyurqAdtB+CGsltgBkERMy; AWSALBCORS=JxKB7w6ta4aeuLwSi8mO18B6sDs4Mv/1K5pYSQd7pX73GpeLsl45wsYy6F8tCNKNYNFanNo2g0ZjLEgHCIouMwZ8ap5oHdQyvZA36KiyurqAdtB+CGsltgBkERMy").
                header("content-type", "application/json; charset=utf-8").
                header("Authorization", "Bearer bfuCt/qKpH4Nc/hH+Ycz7xU2UUYYWX6rZcJTUNKcG0s").
                header("Referer", "https://testcompany4.perimeter81.com/").
                header("Origin", "https://testcompany4.perimeter81.com").
                when().
                get("https://api.perimeter81.com/api/members?page=1&limit=10&field=createdAt&dir=desc&q=").then().extract().response();
        String responseString = response.asString();

        if (!responseString.equals("[]")) {

            JsonPath jp = new JsonPath(responseString);    //convert response String to JSON
            int client_id = jp.get("id[0]");                 //get id from JSON

            int count = jp.get("array.size()");
            for (int i = 0; i < count; i++) {
                System.out.println(jp.get("id[" + i + "]").toString());
            }

            System.out.println("client id = " + client_id);

        } else {
            System.out.println("client list: " + " = null");
        }
    }
}
