package im.atzma.lista2020.appmanager;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static java.time.OffsetTime.from;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.RedirectConfig.redirectConfig;

public class ServiceCreationHelper {
    String key;
    String value;

    public ServiceCreationHelper(Map<String, String> firstCookie) {
        for (Map.Entry<String, String> entry : firstCookie.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
//            System.out.println("Key = " + entry.getKey() +
//                    ", Value = " + entry.getValue());
        }
    }


    public void createService() {
        given().cookies(key, value).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                formParam("name", "TestCategory2").
                formParam("duration", 300).
                formParam("price", 50).
                formParam("color", "#50e3c1").
                formParam("category_id", 1).
                formParam("added", "2020-01-26 21:39:41").
                when().
                post("/catalog/services").
                then().
                assertThat().
                statusCode(201).and().contentType("text/html; charset=UTF-8");


    }

    public void createService2() {
        Response response = given().cookies(key, value).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                when().
                get("/clients?limit=20&offset=0").then().extract().response();

        String resString = response.asString();   //convert response (RAW) to String
        System.out.println(resString);
        JsonPath jp = new JsonPath(resString);    //convert ressponse String to JSON
        int id = jp.get("id[0]");                 //get id from JSON
        System.out.println(id);
        //  body("name[0]",equalTo("novii client"));

        given().cookies(key, value).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                formParam("name", "TestCategory2").
                when().
                delete("/clients/" + id).
                then().
                assertThat().statusCode(204);
    }

//    public void getAppointmentJson(Map<String, String> loginCookie) {
//        String res = given().cookies(getLoginCookie()).
//                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
//                when().
//                get("/calendar?start=2020-01-25T00:00:00&end=2020-01-27T23:59:59&worker_id=1").toString();
//        System.out.println(res);
//    }
}

