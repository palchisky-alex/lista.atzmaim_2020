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
    int client_id;
    // ArrayList<Integer> IDs_list = new ArrayList<>();
    Response response_get;
    Response response_post;
    Response response_put;
    String responseString;

    public ClientCreationHelper(Map<String, String> firstCookie) {
        for (Map.Entry<String, String> entry : firstCookie.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
        }
    }

    public Integer createClient() {
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
                        post("/clients").then().assertThat().statusCode(201).extract().response();
        System.out.println("STATUS POST CODE: " + response_post.getStatusCode());

        responseString = response_post.asString();   //convert response (RAW) to String

//        JsonPath jp = new JsonPath(responseString);    //convert response String to JSON
//            int id = Integer.parseInt(jp.get("id[" + i + "]").toString());
//            IDs_list.add(id);
        System.out.println("CLIENT ID ====== " + responseString);
//        { "client_id": 123 }
        return Integer.parseInt(responseString);

    }

    public String getClientList() {
        Response response = given().cookies(key, value).
                //  header("content-type", "application/json; charset=utf-8").
                        header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                        when().
                        get("/clients?limit=100000&offset=0/").then().extract().response();
        responseString = response.asString();
        if (!responseString.equals("[]")) {

            System.out.println("Client list: " + responseString);
            JsonPath jp = new JsonPath(responseString);    //convert response String to JSON
            client_id = jp.get("id[0]");                 //get id from JSON

            int count = jp.get("array.size()");
            for (int i = 0; i < count; i++) {
                System.out.println(jp.get("id[" + i + "]").toString());
            }

            System.out.println("client id = " + client_id);

        } else {
            System.out.println("client list: " + " = null");
        }
        responseString = response.headers().toString() + "\n\n" + response.body().prettyPrint();
        responseString = responseString.replaceAll("id.*", "#####");
        responseString = responseString.replaceAll("profile_image.*", "#####");
        responseString = responseString.replaceAll("Cookie.*", "#####");
        responseString = responseString.replaceAll("Expires=Sat, .*", "#####");
        responseString = responseString.replaceAll("Date=.*", "#####");
        return responseString;
    }

    public String getClientData(int client_id) {
        Response response = given().cookies(key, value).
                //  header("content-type", "application/json; charset=utf-8").
                        header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                        when().
                        get("/clients/" + client_id).then().extract().response();
        responseString = response.asString();
        responseString = response.headers().toString() + "\n\n" + response.body().prettyPrint();
        responseString = responseString.replaceAll("id.*", "#####");
        responseString = responseString.replaceAll("profile_image.*", "#####");
        responseString = responseString.replaceAll("hash.*", "#####");
        responseString = responseString.replaceAll("X-Request-Id.*", "#####");
        responseString = responseString.replaceAll("start.*", "#####");
        responseString = responseString.replaceAll("end.*", "#####");
        responseString = responseString.replaceAll("profile_picture.*", "#####");
        responseString = responseString.replaceAll("Cookie.*", "#####");
        responseString = responseString.replaceAll("Expires=Sat, .*", "#####");
        responseString = responseString.replaceAll("Date=.*", "#####");
        return responseString;
    }

    public void modificateClient(int client_id) {
        response_get = given().cookies(key, value).
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                formParam("birthdate", "03-01").
                formParam("birthyear", "1991").
                formParam("gender", "female").
                formParam("address", "Russia, Khabarovsk, Krasnorechenskaya 179").
                formParam("name", "Test_changeClientsData_after").
                formParam("email", "QA_Test-After@mail.ru").
                formParam("phone", "[\"0543666666\", \"033333333\"]").
                when().
                put("/clients/" + client_id).then().assertThat().statusCode(204).extract().response();
        System.out.println("STATUS PUT CODE: " + response_post.getStatusCode());

        responseString = response_post.asString();   //convert response (RAW) to String
        System.out.println("CLIENT ID after modification ====== " + responseString);
    }

    public void deleteClient() {
        getClientList();
        given().cookies(key, value).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                when().
                delete("/clients/" + client_id).
                then().
                assertThat().statusCode(204);

    }
}

