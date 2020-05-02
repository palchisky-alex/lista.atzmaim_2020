package lista2020.appmanager;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static io.restassured.RestAssured.given;

public class ServiceVerificationRestHelper {

    CookieManager cookieManager;
    String key = "7b7a53e239400a13bd6be6c91c4f6c4e";
    String value;
    String status;
    String deleted_account;
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

    public String createAccountWithType(String type) {
        Random random = new Random();
        int randomInt = random.nextInt();
        String random_mail = "api_servicetest" + randomInt + "@gmail.com";
        String password = "Pa$$w@rd";
        System.out.println("============ CREATE RANDOM ACCOUNT WITH BUSINESS TYPE " + type + " AND VERIFY JSON ============");
        System.out.println("==== CREATED " + random_mail + " WITH PASSWORD " + password);

        post_response = given().
                header("Content-Type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                formParam("added", currentDate + " " + currentTime).
                formParam("email", random_mail).
                formParam("pass", password).
                formParam("phone", "0547613154").
                formParam("permit_ads", "true").
                formParam("business_types", type).
                formParam("lang", "en").
                formParam("timezone", "Asia/Jerusalem").
                formParam("country", "IL").
                formParam("city", "Tel Aviv").
                when().log().all().
                post("/signup-new-account").then().
                extract().response();

        System.out.println("STATUS POST CODE " + post_response.getStatusCode());

        if (post_response.getStatusCode() == 201) {
            System.out.println("=== ACCOUNT: " + random_mail + " WAS CREATED ===");

            loginCookie = post_response.getCookies();

            for (Map.Entry<String, String> entry : loginCookie.entrySet()) {
                value = entry.getValue();
                accounts.put(random_mail, value);
            }
            String serviceResponse = getServiceList();
            return serviceResponse;
        } else {
            System.out.println("STATUS POST CODE:" + post_response.getStatusCode());
            System.out.println("=== ACCOUNT " + random_mail + " NOT CREATED");
        }
        return null;
    }

    public String getServiceList() {
        Response response = given().cookies(key, value).
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                when().log().all().
                get("/catalog/services/").then().extract().response();
        String responseString;

        responseString = response.headers().toString() + "\n\n" + response.body().prettyPrint();
        responseString = responseString.replaceAll("Set-Cookie.*", "###Set-Cookie##");
        responseString = responseString.replaceAll("Expires=Sat, .*", "###Expires=Sat##");
        responseString = responseString.replaceAll("Date=.*", "###Date##");
        responseString = responseString.replaceAll("X-Request-Id.*", "###X-Request-Id##");

        return responseString;
    }

    public Set<String> deleteAccount() {
        String value_delete;
        String accounts_for_deletion;

        System.out.println("//----------------------- DELETION ------------------------//");
        System.out.println("== ACCOUNTS DESIGNED FOR REMOVAL - 20");
        System.out.println("== ACCOUNTS RECEIVED FOR REMOVAL IN TEST: " + accounts.size());
        accounts.forEach((k, v) -> System.out.println("//: " + k));
        Set<String> removedAccounts = new HashSet<>();

        for (Map.Entry<String, String> entry : accounts.entrySet()) {
            value_delete = entry.getValue();
            accounts_for_deletion = entry.getKey();

            delete_response = given().cookies(key, value_delete).
                    header("content-type", "application/x-www-form-urlencoded").
                    header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                    header("X-Requested-With", "XMLHttpRequest").
                    when().log().all().
                    delete("/settings/business/account").
                    then().assertThat().statusCode(401).extract().response();

            if (delete_response.getStatusCode() == 401) {
                System.out.println("DELETED ACCOUNT " + " = " + accounts_for_deletion + " = STATUS CODE: " + delete_response.getStatusCode());
                deleted_account = accounts_for_deletion;
                deleted_account = "removed";
                removedAccounts.add(deleted_account);
            }
            else {
                removedAccounts.add(accounts_for_deletion);
            }
        }

        removedAccounts.remove(deleted_account);
        System.out.println("LIST OF REMOVED ACCOUNTS MUST BE EMPTY, AND CONTAINS: " + removedAccounts);
        return removedAccounts;
    }

}
