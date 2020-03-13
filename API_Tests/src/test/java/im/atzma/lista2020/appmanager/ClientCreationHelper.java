package im.atzma.lista2020.appmanager;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ClientCreationHelper {
    int count;
    String key;
    String value;
    // ArrayList<Integer> IDs_list = new ArrayList<>();
    Response response_get;
    Response response_post;
    String responseString;

    public ClientCreationHelper(Map<String, String> firstCookie) {
        for (Map.Entry<String, String> entry : firstCookie.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
        }
    }

    public void createClient() {
        response_post = given().cookies(key, value).log().all().
                //    header("content-type", "application/x-www-form-urlencoded").
                        header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                        formParam("birthdate", "06-26").
                        formParam("birthyear", "1981").
                        formParam("added", "2020-08-02 16:00:00").
                        formParam("address", "Israel, Rokah 18, Ramat Gan").
                        formParam("phone", "0547613154").
                        formParam("email", "QA_Test-Before@gmail.com").
                        formParam("name", "Test_changeClientsData_before").
                        formParam("gender", "male").
                        formParam("status", "status - before").
                        when().
                        post("/clients");
        System.out.println("STATUS POST CODE: " + response_post.getStatusCode());

    }

}

