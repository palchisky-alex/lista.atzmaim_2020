package lista2020.appmanager;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static io.restassured.RestAssured.*;

public class AccountCreationHelper {
    CookieManager cookieManager;
    String key = "7b7a53e239400a13bd6be6c91c4f6c4e";
    String value;
    String status;
    Response get_response;
    Response delete_response;
    Response post_response;
    Map<String, String> loginCookie = new HashMap<>();
    List<String> cookies = new ArrayList<>();
    Map<String, String> accounts = new HashMap<>();
    String currentDate = LocalDate.now().toString();
    LocalTime time = LocalTime.now();
    DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("hh:mm:ss");
    String currentTime = time.format(dtf2);


    public String businessTypeResponse() {
        System.out.println("=== GET BUSINESS TYPE JSON ===");
        get_response = given().
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("lang", "en").
                when().
                get("/business_types?lang=en").then().extract().response();
        String responseString = get_response.asString();
        System.out.println(responseString);
        return responseString;
    }

    public String createAccount(String mail, String pass, String tel, String timezone, String country, String city) {
        Random random = new Random();
        int randomInt = random.nextInt();
        String random_for_mail = "api_accounts_" + randomInt;

        System.out.println("=== CREATE RANDOM ACCOUNT: " + random_for_mail + mail + " AND VERIFY JSON ===");
        accounts.put(random_for_mail + mail, pass);

        post_response = given().
                header("Content-Type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                formParam("added", currentDate + " " + currentTime).
                formParam("email", random_for_mail + mail).
                formParam("pass", pass).
                formParam("phone", tel).
                formParam("permit_ads", "true").
                formParam("business_types", "[1]").
                formParam("lang", "en").
                formParam("timezone", timezone).
                formParam("country", country).
                formParam("city", city).
                when().log().all().
                post("/signup-new-account").then().assertThat().statusCode(201).
                extract().response();

        String responseString = post_response.asString();
        System.out.println("Account creation response: " + responseString);
        System.out.println("Create account code: " + post_response.getStatusCode());

        loginCookie = post_response.getCookies();

        for (Map.Entry<String, String> entry : loginCookie.entrySet()) {
            value = entry.getValue();
            cookies.add(value);
            System.out.println("Cookie value account creation : " + value);
        }

        return responseString;
    }

    public void deleteAccount() {
        System.out.println("================= REMOVE ALL ACCOUNTS AND VERIFY DELETION ===============");
        System.out.println("=== ACCOUNTS DESIGNED FOR REMOVAL - 21 ");
        System.out.println("=== ACCOUNTS RECEIVED FOR REMOVAL IN TEST: " + accounts.size());
        for (int i = 0; i < cookies.size(); i++) {
            String value_delete = cookies.get(i);

            delete_response = given().cookies(key, value_delete).
                    header("content-type", "application/x-www-form-urlencoded").
                    header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                    header("X-Requested-With", "XMLHttpRequest").
                    when().log().all().
                    delete("/settings/business/account").
                    then().
                    extract().response();
            System.out.println("============== DELETED ACCOUNT " + " STATUS: " + delete_response.getStatusCode());
            status = String.valueOf(delete_response.getStatusCode());

            if (delete_response.getStatusCode() != 401) {
                System.out.println("=== DELETION STATUS NOT 401, VALUE " + value_delete + " FAILD!");
            }
        }
    }

    public String checkAccountDeletion() {
        System.out.println("======== VERIFY ACCOUNTS DELETION =======");
        String c = null;
        for (Map.Entry<String, String> entry : accounts.entrySet()) {
            String delete_mail = entry.getKey();
            String delete_password = entry.getValue();

            System.out.println("Deleted mail - " + delete_mail);
            System.out.println("Deleted password - " + delete_password);

            Response response = given().
                    header("Content-Type", "application/x-www-form-urlencoded").
                    header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                    header("X-Requested-With", "XMLHttpRequest").
                    formParam("time_zone", "Asia/Jerusalem").
                    formParam("email", delete_password).
                    formParam("pass", delete_mail).
                    when().
                    post("/check-login").then().extract().response();
            c = response.getCookies().toString();

        }
        return c;
    }

}



