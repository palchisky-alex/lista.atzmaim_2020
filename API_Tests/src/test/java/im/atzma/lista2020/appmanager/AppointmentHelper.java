package im.atzma.lista2020.appmanager;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AppointmentHelper {
    String key;
    String value;
    Response response;

    JsonPath jpath;
    int appointment_id;

    public AppointmentHelper(Map<String, String> firstCookie) {
        for (Map.Entry<String, String> entry : firstCookie.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
        }
    }


    public void createAppointment(int client_id, int service_id) {
        String currentTime = LocalDate.now().toString();
        System.out.println("TIME: " + currentTime);

        given().cookies(key, value).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                formParam("start", currentTime+"T12:00:00").
                formParam("client_id", client_id).
                formParam("worker_id", 1).
                formParam("total_price", 50).
                formParam("duration", 60).
                formParam("services", "[{\"id\":\"" + service_id + "\",\"category\":{\"name\":\"Common\",\"id\":1},\"count\":1}]").
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

    public String getAppointmentList() {
        response = given().cookies(key, value).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                when().
                get("/calendar?start=2019-03-17T00:00:00&end=2121-03-19T23:59:59&worker_id=1").
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

    public void deleteAppointment() {
        getAppointmentList();
        given().cookies(key, value).
                header("content-type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                when().
                delete("/calendar/" + appointment_id).
                then().
                assertThat().statusCode(204);
    }


}
