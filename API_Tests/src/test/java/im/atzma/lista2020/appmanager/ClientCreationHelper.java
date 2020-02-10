package im.atzma.lista2020.appmanager;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import java.io.IOException;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class ClientCreationHelper {
    String key;
    String value;
    int client_id;
    Response response;
    String responseString;

    public ClientCreationHelper(Map<String, String> firstCookie) {
        for (Map.Entry<String, String> entry : firstCookie.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
        }
    }

    public void createClient() throws IOException {
        getClientList();
        given().cookies(key, value).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                formParam("birthdate", "06-26").
                formParam("birthyear", "1981").
                formParam("added", "2020-08-02 15:00:00").
                formParam("address", "Israel, Rokah 18, Ramat Gan").
                formParam("phone", "0547613154").
                formParam("email", "QA_Test-Before@gmail.com").
                formParam("name", "Test_changeClientsData_before").
                formParam("gender", "male").
                formParam("status", "status - before").
                when().
                post("/clients").
                then().
                assertThat().statusCode(201);

    }

    public void getClientList() throws IOException {
        response = given().cookies(key, value).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                when().
                get("/clients?limit=20&offset=0").then().extract().response();
        ResponseBody body = response.getBody();

        responseString = response.asString();   //convert response (RAW) to String

        //responseMap.forEach((k, v) -> System.out.println("map : " + k + " Value : " + v));
        if(!responseString.equals("[]")) {

            System.out.println("client list: " + responseString);
            JsonPath jp = new JsonPath(responseString);    //convert response String to JSON
            client_id = jp.get("id[0]");                 //get id from JSON
            System.out.println("client id = " + client_id);

        }
        else {
            System.out.println("client list: " + " = null");
        }
    }

    public void deleteClient() throws IOException {
        given().cookies(key, value).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                when().
                delete("/clients/" + client_id).
                then().
                assertThat().statusCode(204);
        getClientList();

        //  body("name[0]",equalTo("novii client"));

    }

}

