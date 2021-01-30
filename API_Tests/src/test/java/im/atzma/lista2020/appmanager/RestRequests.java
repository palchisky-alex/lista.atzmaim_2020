package im.atzma.lista2020.appmanager;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class RestRequests extends HelperBaseAPI {
    static int count;
    static int id;
    static String key;
    static String value;
    public String requests;
    static String parametrName;
    static ArrayList<Integer> IDs_list = new ArrayList<>();
    static List<String> get_list = new ArrayList<>();
    static List<String> delete_list = new ArrayList<>();
    public List<String> properties_list = new ArrayList<>();
    //    static String req_get = null;
//    static String req_delete = null;
    static Response response_get;
    Response response_post;
    static String responseString;
    Properties properties = new Properties();

    public RestRequests(Map<String, String> firstCookie) throws IOException {
        for (Map.Entry<String, String> entry : firstCookie.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
        }
    }

    public void cleaner() throws IOException, InterruptedException {
        Map<String, ArrayList<Integer>> r = new HashMap<>();

        properties_list.clear();
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/rest.properties", target))));

        properties_list.add(properties.getProperty("get.appointment"));
        properties_list.add(properties.getProperty("delete.appointment"));

        properties_list.add(properties.getProperty("get.clients"));
        properties_list.add(properties.getProperty("delete.clients"));

        properties_list.add(properties.getProperty("get.services"));
        properties_list.add(properties.getProperty("delete.services"));


        for (int q = 0; q < properties_list.size(); q++) {
            String rex = properties_list.get(q);
            Pattern p = Pattern.compile("^(\\w+)-(\\w+)-(.*)$");
            Matcher m = p.matcher(rex);

            if (m.find()) {
                String method = m.group(1);
                String api = m.group(2);
                String url = m.group(3);

                if (method.equals("get")) {
//                    get_list.add();
                    System.out.println("api: " + api);
                    System.out.println("url: " + url);
                    System.out.println("method: " + method);
                    r.put(api, count(url));


                    // System.out.println("== Send request: " + req_get + " ==");
                } else if (method.equals("delete")) {
//                delete_list.add( rex.replace("delete-", ""));
                    // System.out.println("== Send request: " + req_delete + " ==");
                    removeRest(r.get(api), url);

                }

            }
        }
       // r.forEach((k, v) -> System.out.println("get IDs array : " + k + " Value : " + StringUtils.join(',', v)));

    }


    public static ArrayList<Integer> count(String req_get) {

        response_get = given().cookies(key, value).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                when().
                get(req_get).then().extract().response();

        responseString = response_get.asString();   //convert response (RAW) to String

        JsonPath jp = new JsonPath(responseString);    //convert response String to JSON
        int IDs = jp.get("array.size()");

        for (int i = 0; i < IDs; i++) {
            int id = Integer.parseInt(jp.get("id[" + i + "]").toString());
            IDs_list.add(id);
        }


        return IDs_list;

    }

    public static void removeRest(ArrayList<Integer> IDs_list, String req_delete) throws InterruptedException {
        System.out.println("=========== REMOVE ALL DEFAULT ELEMENTS FROM RANDOM ACCOUNT BEFORE TESTS =========== ");
        System.out.println(req_delete);
        if(req_delete == "/clients/") {
             parametrName = "clients";
        }
        count = IDs_list.size();
        System.out.println("== List items size for deletion: " + count + " ==");
        System.out.print("== Items for deletion:");
        //response_get.getBody().print();
        for (int q = 0; q < IDs_list.size(); q++) {
            System.out.println("== id for deletion: " + IDs_list.get(q) + " ==");
            id = IDs_list.get(q);
            response_get = given().cookies(key, value).
                    header("Content-Type", "application/x-www-form-urlencoded").
                    header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0")
//                    .formParam(parametrName, id)
                    .when()
                    .delete(req_delete)
                    .then().extract().response();
            count = count - 1;
            System.out.println("STATUS DELETE CODE: " + response_get.getStatusCode());
            System.out.println("== Number of deleted item: " + count + " ==");
        }
        IDs_list.clear();


    }


    public void loadProperties() throws IOException {
        properties_list.clear();
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/rest.properties", target))));

        properties_list.add(properties.getProperty("get.clients"));
        properties_list.add(properties.getProperty("delete.clients"));

        properties_list.add(properties.getProperty("get.services"));
        properties_list.add(properties.getProperty("delete.services"));

        for (int q = 0; q < properties_list.size(); q++) {
            String rex = properties_list.get(q);
            if (rex.contains("get-")) {
                get_list.add(rex.replace("get-", ""));
                // System.out.println("== Send request: " + req_get + " ==");
            } else if (rex.contains("delete-")) {
                delete_list.add(rex.replace("delete-", ""));
                // System.out.println("== Send request: " + req_delete + " ==");
            }
        }
        for (int i = 0; i < delete_list.size(); i++) {
            System.out.println(delete_list.get(i));

        }

    }

}
