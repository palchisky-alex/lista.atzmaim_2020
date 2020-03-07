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


    public void getClientList() throws IOException {
        response_get = given().cookies(key, value).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                when().
                get("/clients?limit=20&offset=0").then().log().all().extract().response();

        responseString = response_get.asString();   //convert response (RAW) to String

        if (!responseString.equals("[]")) {

            System.out.println("client list: " + responseString);
            JsonPath jp = new JsonPath(responseString);    //convert response String to JSON

            int count = jp.get("array.size()");
            for (int i = 0; i < count; i++) {
                int id = Integer.parseInt(jp.get("id[" + i + "]").toString());
                System.out.println(id);
//                IDs_list.add(id);

//                System.out.println("== Client # " + i + " ==");
//                System.out.println("ID: " + jp.get("id[" + i + "]").toString());   //get IDs from JSON
//                System.out.println("NAME: " + jp.get("name[" + i + "]").toString());
//                System.out.println("Profile_image: " + jp.get("profile_image[" + i + "]").toString());
//                System.out.println("NAME: " + jp.get("name[" + i + "]").toString());
//                System.out.println("ADDRESS: " + jp.get("name[" + i + "]").toString());
//                System.out.println("STATUS: " + jp.get("status[" + i + "]").toString());
//                System.out.println("STATUS GET CODE: " + response_get.getStatusCode());
//                System.out.println("========================");

            }

        } else {
            System.out.println("client list: " + " = null");
        }

    }

//    public void deleteClient() throws IOException {
//        count = IDs_list.size();
//        for (int q = 0; q < IDs_list.size(); q++) {
//            System.out.println("IDs list: " + IDs_list.get(q));
//            int id = IDs_list.get(q);
//            if (IDs_list.size() > 0) {
//                response_get = given().cookies(key, value).
//                        header("content-type", "application/x-www-form-urlencoded").
//                        header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
//                        when().
//                        delete("/clients/" + id);
//                count = count - 1;
//                System.out.println("STATUS DELETE CODE: " + response_get.getStatusCode());
//                System.out.println("Number of client: " + count);
//            } else {
//                responseString.equals("[]");
//                System.out.println("Clients not exists");
//            }
//
//        }
//        //  body("name[0]",equalTo("novii client"));
//
//    }

    public ArrayList<Integer> deleteRest() {
        if (RestRequests.count().size() > 0) {
            RestRequests.removeRest(RestRequests.IDs_list);
        }
        return RestRequests.count();

    }
}

