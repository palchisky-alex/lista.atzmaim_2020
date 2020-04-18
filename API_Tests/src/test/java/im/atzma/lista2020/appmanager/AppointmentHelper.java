package im.atzma.lista2020.appmanager;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class AppointmentHelper {
    String key;
    String value;
    Response response;
    String currentTime = LocalDate.now().toString();
    static ArrayList<Integer> IDs_list = new ArrayList<>();

    JsonPath jpath;
    int appointment_id;

    public AppointmentHelper(Map<String, String> firstCookie) {
        for (Map.Entry<String, String> entry : firstCookie.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
        }
    }


    public void createAppointment(int client_id, int service_id, int category_id) {

        Pattern p = Pattern.compile("[\\d][0-3]");
        Matcher m = p.matcher(currentTime);
        String newTime = m.replaceAll("");

        System.out.println("CURRENT TIME: " + newTime);
        List<String>slots = new ArrayList<>();
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
        List<String>years = new ArrayList<>();
        years.add("2020");
        years.add("2021");
        years.add("2022");
        years.add("2023");
        years.add("2024");
        years.add("2025");
        years.add("2026");
        years.add("2027");
        years.add("2028");
        years.add("2029");
        years.add("2030");
        years.add("2031");

        for (int w = 0; w < years.size(); w++) {

            String year = years.get(w);
            System.out.println("get year: " + year);

            for (int i = 0; i < slots.size(); i++) {
                String hour = slots.get(i);
                given().cookies(key, value).
                        header("content-type", "application/x-www-form-urlencoded").
                        header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                        header("X-Requested-With", "XMLHttpRequest").
                        formParam("start", year + newTime + hour).
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
    }

    public String getAppointmentList() {
        response = given().cookies(key, value).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                when().
                get("/calendar?start=2000-03-17T00:00:00&end=2121-03-19T23:59:59&worker_id=1").
                then().
                extract().response();

        String responseString = response.asString();   //convert response (RAW) to String
        System.out.println("Appointment String: " + responseString);

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
        response = given().cookies(key, value).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                when().
                get("/calendar?start=2000-03-17T00:00:00&end=2121-03-19T23:59:59&worker_id=1").
                then().
                extract().response();
        String responseString = response.asString();
        System.out.println("Appointment String: " + responseString);
        return responseString;
    }

    public ArrayList<Integer> getAppointmentID() {
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


        return IDs_list;
    }

    public void deleteAppointment() {
        getAppointmentID();
        int count = IDs_list.size();
        System.out.println("== List items size for deletion: " + count + " ==");
        System.out.print("== Items for deletion:");

        for (int q = 0; q < IDs_list.size(); q++) {
            System.out.println("== id for deletion: " + IDs_list.get(q) + " ==");
            appointment_id = IDs_list.get(q);
            response = given().cookies(key, value).
                    header("content-type", "application/x-www-form-urlencoded").
                    header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                    when().
                    delete("/calendar/" + appointment_id).then().assertThat().statusCode(200).extract().response();
            count = count - 1;
            System.out.println("STATUS DELETE CODE: " + response.getStatusCode());
            System.out.println("== Number of deleted item: " + count + " ==");
        }
        IDs_list.clear();

    }


    public void modifyAppointment(int client_id, int service_id, int category_id, int appointment_id) {
        given().cookies(key, value).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                formParam("start", currentTime+"T15:00:00").
                formParam("client_id", client_id).
                formParam("worker_id", 1).
                formParam("total_price", 100).
                formParam("duration", 120).
                formParam("services", "[{\"id\":\"" + service_id + "\",\"category\":{\"id\":"+category_id+"},\"count\":1}]").
                formParam("note", "call one hour after").
                formParam("is_reminders_set", "false").
                formParam("address", "Israel, Maron 8, Tel Aviv").
                formParam("added", currentTime + "T15:00:00").
                when().
                put("/calendar/" + appointment_id).
                then().
                assertThat().
                statusCode(200).and().contentType("application/json; charset=utf-8");

    }
}
