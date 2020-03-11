package im.atzma.lista2020.appmanager;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestRequests {
    static int count;
    static int id;
    static String key;
    static String value;
    static ArrayList<Integer> IDs_list = new ArrayList<>();
    static Response response_get;
    Response response_post;
    static String responseString;

    public RestRequests(Map<String, String> firstCookie) {
        for (Map.Entry<String, String> entry : firstCookie.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
        }
    }

    public static ArrayList<Integer> count() {

        response_get = given().cookies(key, value).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                when().
                get("/clients?limit=100000&offset=0").then().assertThat().statusCode(200).extract().response();

        responseString = response_get.asString();   //convert response (RAW) to String

        JsonPath jp = new JsonPath(responseString);    //convert response String to JSON
        int IDs = jp.get("array.size()");

        for (int i = 0; i < IDs; i++) {
            int id = Integer.parseInt(jp.get("id[" + i + "]").toString());
            IDs_list.add(id);

            System.out.println("== Client # " + i + " ==");
            System.out.println("ID: " + jp.get("id[" + i + "]").toString());   //get IDs from JSON
            System.out.println("NAME: " + jp.get("name[" + i + "]").toString());
            System.out.println("Profile_image: " + jp.get("profile_image[" + i + "]").toString());
            System.out.println("NAME: " + jp.get("name[" + i + "]").toString());
            System.out.println("ADDRESS: " + jp.get("name[" + i + "]").toString());
            System.out.println("STATUS: " + jp.get("status[" + i + "]").toString());
            System.out.println("STATUS GET CODE: " + response_get.getStatusCode());
            System.out.println("========================");
        }
        return IDs_list;
    }

    public static void removeRest(ArrayList<Integer> IDs_list) {
        count = IDs_list.size();
        System.out.println("List size: " + count);
        for (int q = 0; q < IDs_list.size(); q++) {
            System.out.println("IDs list: " + IDs_list.get(q));
            id = IDs_list.get(q);

            response_get = given().cookies(key, value).
                    header("content-type", "application/x-www-form-urlencoded").
                    header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                    when().
                    delete("/clients/" + id).then().assertThat().statusCode(204).extract().response();
            count = count - 1;
            System.out.println("STATUS DELETE CODE: " + response_get.getStatusCode());
            System.out.println("Number of items: " + count);
        }
        IDs_list.clear();

    }

}
