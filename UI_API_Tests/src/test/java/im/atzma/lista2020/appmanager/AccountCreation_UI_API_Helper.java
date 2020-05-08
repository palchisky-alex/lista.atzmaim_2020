package im.atzma.lista2020.appmanager;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.output.WriterOutputStream;
import org.testng.annotations.Listeners;

import java.io.PrintStream;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static io.restassured.RestAssured.given;
@Listeners(LogListener.class)
public class AccountCreation_UI_API_Helper extends RequestSpecBuilder {
    String key = "7b7a53e239400a13bd6be6c91c4f6c4e";
    String value;
    String headerValue;
    String accountCreationResponse;
    Response get_response;
    Response delete_response;
    Response post_response;
    Map<String, String> loginCookie = new HashMap<>();
    List<String> cookies = new ArrayList<>();
    Map<String, String> accounts = new HashMap<>();
    String currentDate = LocalDate.now().toString();
    LocalTime time = LocalTime.now();
    DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss");
    String currentTime = time.format(dtf2);


    public String businessTypeResponse() {

        System.out.println("=== GET BUSINESS TYPE JSON ===");
        get_response = given().filter(new AllureRestAssured()).
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("lang", "en").
                when().
                get("/business_types?lang=en").then().extract().response();
        String responseString = get_response.asString();
        System.out.println(responseString);
        return responseString;
    }


    public int createAccount() {
        Random random = new Random();
        int randomInt = random.nextInt();
        String random_for_mail = "api_test_ui" + randomInt;

        System.out.println("=== CREATE RANDOM ACCOUNT, STATUS MUST BE 201 ===");
        accounts.put(random_for_mail + "@gmail.com", "Pa$$w@rd");

//        final StringWriter writerReqest= new StringWriter();
//        final StringWriter writerResponse = new StringWriter();
//        final PrintStream requestVar = new PrintStream(new WriterOutputStream(writerReqest), true);
//        final PrintStream responseVar = new PrintStream(new WriterOutputStream(writerResponse), true);
        String requestTemplatePath = "resources/tpl/http-request.ftl";
        String responseTemplatePath = "resources/tpl/http-response.ftl";

        StringWriter writerReqes = new StringWriter();
        final StringWriter writerResponse = new StringWriter();
        final PrintStream requestVar = new PrintStream(new WriterOutputStream(writerReqes));
        final PrintStream responseVar = new PrintStream(new WriterOutputStream(writerResponse), true);

        post_response = given(). filters(new CustomAllureRestAssured().setRequestTemplate(requestTemplatePath).setResponseTemplate(responseTemplatePath)).

                header("Content-Type", "application/x-www-form-urlencoded").
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                header("X-Requested-With", "XMLHttpRequest").
                formParam("added", currentDate + " " + currentTime).
                formParam("email", random_for_mail + "@gmail.com").
                formParam("pass", "Pa$$w@rd").
                formParam("phone", "0547613154").
                formParam("permit_ads", "true").
                formParam("business_types", "[1]").
                formParam("lang", "en").
                formParam("timezone", "Asia/Jerusalem").
                formParam("country", "IL").
                formParam("city", "Tesl Aviv").
                when().
                post("/signup-new-account").then().
                extract().response();

        accountCreationResponse = post_response.asString();
        Allure.getLifecycle().updateTestCase((t) -> {t.setStatusDetails( t.getStatusDetails().setMessage(accountCreationResponse));
        });
        loginCookie = post_response.getCookies();

        for (Map.Entry<String, String> entry : loginCookie.entrySet()) {
            value = entry.getValue();
            cookies.add(value);
            System.out.println("Cookie value account creation : " + value);
        }
        System.out.println("Create account status: " + post_response.getStatusCode());
        return post_response.getStatusCode();
    }

    public String verifyAccountCreation() {
        System.out.println("ACCOUNT CREATION RESPONSE MUST BE '/en/calendar'");
        System.out.println("Account creation response: " + accountCreationResponse);
        return accountCreationResponse;
    }

    public int deleteAccount() {
        System.out.println("=== REMOVE ALL ACCOUNTS, STATUS MUST BE 401 ===");
        System.out.println("=== ACCOUNTS DESIGNED FOR REMOVAL - 1 ");
        System.out.println("=== ACCOUNTS RECEIVED FOR REMOVAL IN TEST: " + accounts.size());
        for (int i = 0; i < cookies.size(); i++) {
            String value_delete = cookies.get(i);

            delete_response = given().cookies(key, value_delete).filter(new AllureRestAssured()).
                    header("content-type", "application/x-www-form-urlencoded").
                    header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                    header("X-Requested-With", "XMLHttpRequest").
                    when().log().all().
                    delete("/settings/business/account").
                    then().
                    extract().response();
        }
        System.out.println("Account deletion status: " + delete_response.getStatusCode());
        return delete_response.getStatusCode();
    }


    public String verifyAccountDeletion() {
        System.out.println("=== VERIFY ACCOUNTS DELETION ===");
        System.out.println("=== STATUS MUST BE 302 AND 'LOCATION' VALUE - '/he/login' ===");

        Allure.getLifecycle().updateTestCase((t) -> {t.setStatusDetails( t.getStatusDetails().setMessage("=== STATUS MUST BE 302 AND 'LOCATION' VALUE - '/he/login' ==="));
        });

        for (Map.Entry<String, String> entry : accounts.entrySet()) {
            String delete_mail = entry.getKey();
            String delete_password = entry.getValue();

            System.out.println("Deleted mail - " + delete_mail);
            System.out.println("Deleted password - " + delete_password);

            Response response = given().filter(new AllureRestAssured()).
                    header("Content-Type", "application/x-www-form-urlencoded").
                    header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                    header("Accept-Encoding", "gzip, deflate, br").
                    header("X-Requested-With", "XMLHttpRequest").
                    header("Referer", "/he/login").
                    formParam("time_zone", "Asia/Jerusalem").
                    formParam("email", delete_mail).
                    formParam("pass", delete_password).
                    when().
                    post("/check-login").then().statusCode(302).
                    extract().response();
            headerValue = response.getHeader("Location");
            System.out.println("Login into deleted account status: " + response.getStatusCode());
            System.out.println("'Location' header value: " + headerValue);
            Allure.getLifecycle().updateTestCase((t) -> {t.setStatusDetails( t.getStatusDetails().setMessage("'Location' header value: " + headerValue));
            });

        }
        return headerValue;
    }

}



