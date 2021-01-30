package im.atzma.lista2020.appmanager;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AppointmentRestHelper {
    String key;
    String value;
    Response response;
    String currentTime = LocalDate.now().toString();
    static ArrayList<Integer> IDs_list = new ArrayList<>();
    List<String> slots = new ArrayList<>();
    List<String> start = new ArrayList<>();
    String hour;

    JsonPath jpath;
    int appointment_id;
    int appointment2_id;

    public AppointmentRestHelper(Map<String, String> firstCookie) {
        for (Map.Entry<String, String> entry : firstCookie.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
        }
    }


    public void createAppointment(int client_id, int service_id, int category_id) {
        System.out.println("================================= CREATE 12 APPOINTMENTS =================================");
        System.out.println("CURRENT TIME: " + currentTime);

        slots.add("T09:00:00");
        slots.add("T10:00:00");
        slots.add("T11:00:00");
        slots.add("T12:00:00");
        slots.add("T13:00:00");
        slots.add("T14:00:00");
        slots.add("T15:00:00");
        slots.add("T16:00:00");
        slots.add("T17:00:00");
        slots.add("T18:00:00");
        slots.add("T19:00:00");
        slots.add("T20:00:00");


        for (int i = 0; i < slots.size(); i++) {

            hour = slots.get(i);
            System.out.println("get slot time: " + currentTime + hour);

            given().cookies(key, value).
                    header("content-type", "application/x-www-form-urlencoded").
                    header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                    header("X-Requested-With", "XMLHttpRequest").
                    formParam("start", currentTime + hour).
                    formParam("client_id", client_id).
                    formParam("worker_id", 1).
                    formParam("total_price", 50).
                    formParam("duration", 60).
                    formParam("services", "[{\"id\":\"" + service_id + "\",\"category\":{\"id\":" + category_id + "},\"count\":1}]").
                    formParam("note", "call one hour before").
                    formParam("is_reminders_set", "false").
                    formParam("address", "Israel, Rokah 18, Ramat Gan").
                    formParam("added", "2020-03-19T10:30:00").
                    when().
                    post("/calendar").
                    then().
                    assertThat().
                    statusCode(201).and().contentType("application/json; charset=utf-8");

        }
    }

    public String getAppointmentList() {
        System.out.println("================================= VERIFY APPOINTMENTS CREATION OR MODIFICATION =================================");
        response = given().cookies(key, value).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                when().
                get("/calendar?start=2000-03-17T00:00:00&end=2121-03-19T23:59:59&worker_id=1").
                then().
                extract().response();

        String responseString = response.asString();   //convert response (RAW) to String

        if (!responseString.equals("[]")) {

            JsonPath jp = new JsonPath(responseString);    //convert response String to JSON
            appointment_id = jp.get("id[0]");                 //get id from JSON
            System.out.println("appointment id = " + appointment_id);

        } else {
            System.out.println("appointment id: " + " = null");
        }
        responseString = response.headers().toString() + "\n\n" + response.body().prettyPrint();
        responseString = responseString.replaceAll("id.*", "#####");
        responseString = responseString.replaceAll("id.*", "#####");
        responseString = responseString.replaceAll("Id.*", "#####");
        responseString = responseString.replaceAll("start.*", "#####");
        responseString = responseString.replaceAll("end.*", "#####");
        responseString = responseString.replaceAll("profile_picture.*", "#####");
        responseString = responseString.replaceAll("Cookie.*", "#####");
        responseString = responseString.replaceAll("Expires=Sat, .*", "#####");
        responseString = responseString.replaceAll("Date=.*", "#####");
        return responseString;
    }

    public String getAppointmentString() {
        System.out.println("================================= VERIFY APPOINTMENT =================================");
        response = given().cookies(key, value).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                when().
                get("/calendar?start=2000-03-17T00:00:00&end=2121-03-19T23:59:59&worker_id=1").
                then().
                extract().response();
        String responseString = response.asString();
        System.out.println("APPOINTMENT STRING MUST BE EMPTY: " + responseString);
        return responseString;
    }

    public ArrayList<Integer> getAppointmentID() {
        System.out.println("================================= GET APPOINTMENT ID =================================");
        response = given().cookies(key, value).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                when().
                get("/calendar?start=2000-03-17T00:00:00&end=2121-03-19T23:59:59&worker_id=1").
                then().
                extract().response();
        String responseString = response.asString();
        JsonPath jp = new JsonPath(responseString);    //convert response String to JSON
        int IDs = jp.get("array.size()");

        for (int i = 0; i < IDs; i++) {
            int id = Integer.parseInt(jp.get("id[" + i + "]").toString());
            IDs_list.add(id);
        }
        for (int i = 0; i < IDs; i++) {
            start.add(jp.get("start[" + i + "]").toString());
        }
        return IDs_list;
    }

    public void modifyAppointment(int client_id, int service_id, int category_id) {
        getAppointmentID();
        System.out.println("================================= MODIFY APPOINTMENT =================================");
        for (int i = 0; i < IDs_list.size(); i++) {
            int count = 0;
            appointment_id = IDs_list.get(i);
            hour = start.get(i);
            String[] splitted = hour.split(" ");
            String data = splitted[0];
            String time = splitted[1];

            given().cookies(key, value).
                    header("content-type", "application/x-www-form-urlencoded").
                    header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                    header("X-Requested-With", "XMLHttpRequest").
                    formParam("start", data + "T" + time + ":00").
                    formParam("client_id", client_id).
                    formParam("worker_id", 1).
                    formParam("total_price", 100).
                    formParam("duration", 60).
                    formParam("services", "[{\"id\":\"" + service_id + "\",\"category\":{\"id\":" + category_id + "},\"count\":1}]").
                    formParam("note", "call one hour after").
                    formParam("is_reminders_set", "false").
                    formParam("address", "Israel, Maron 8, Tel Aviv").
                    formParam("added", currentTime).
                    when().log().all().
                    put("/calendar/" + appointment_id).
                    then().
                    assertThat().
                    statusCode(200).and().contentType("application/json; charset=utf-8");
        }
    }

    public void deleteAppointment() {
        System.out.println("================================= DELETE APPOINTMENTS =================================");
        IDs_list.clear();
        getAppointmentID();
        System.out.println("== APPOINTMENTS DESIGN FOR REMOVAL: 12 ==");
        System.out.println("== APPOINTMENTS FOR REMOVAL IN TEST: "+ IDs_list.size() + " ==");

        for (int q = 0; q < IDs_list.size(); q++) {
            appointment_id = IDs_list.get(q);
            response = given().cookies(key, value).
                    header("content-type", "application/x-www-form-urlencoded").
                    header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                    when().log().all().
                    delete("/calendar/" + appointment_id).then().assertThat().statusCode(200).extract().response();

            System.out.println("STATUS DELETE CODE: " + response.getStatusCode());
        }
    }

    public String getSettings() {
        System.out.println("================================= GET DEFAULT SETTINGS JSON =================================");
        response = given().cookies(key, value).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                when().
                get("/settings/business_data").then().assertThat().statusCode(200).
                log().all().
                extract().response();
        String responseString = response.asString();
        return responseString;
    }
}
