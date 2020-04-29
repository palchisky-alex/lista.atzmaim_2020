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
                post("/signup-new-account").then().assertThat().statusCode(201).
                extract().response();

        String responseToString = post_response.asString();
        System.out.println("Account creation response: " + responseToString);
        System.out.println("Create account code: " + post_response.getStatusCode());

        if( post_response.getStatusCode() == 201) {
            System.out.println("=== ACCOUNT: " + random_mail + " ===");
            accounts.put(random_mail, password);

            loginCookie = post_response.getCookies();

            for (Map.Entry<String, String> entry : loginCookie.entrySet()) {
                value = entry.getValue();
                cookies.add(value);
                System.out.println("Cookie value account creation : " + value);
            }
            String serviceResponse = getServiceList();
            return serviceResponse;
        }
        else {
            System.out.println("=== ACCOUNT " + random_mail + " NOT CREATED");
        }
        return null;
    }

    public String getServiceList() {
        Response response = given().cookies(key, value).
                header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                when().log().all().
                get("/catalog/services/").then().extract().response();

        String responseString = response.headers().toString() + "\n\n" + response.body().prettyPrint();
        responseString = responseString.replaceAll("Set-Cookie.*", "###Set-Cookie##");
        responseString = responseString.replaceAll("Expires=Sat, .*", "###Expires=Sat##");
        responseString = responseString.replaceAll("Date=.*", "###Date##");
        responseString = responseString.replaceAll("X-Request-Id.*", "###X-Request-Id##");
        responseString = responseString.replaceAll("ייעוץ", "Iuts");
        responseString = responseString.replaceAll("ראשונה", "reshona");
        responseString = responseString.replaceAll("אבחון", "ivhun");
        responseString = responseString.replaceAll("דק'", "min");
        responseString = responseString.replaceAll("ביקורת", "Bikoret");
        responseString = responseString.replaceAll("היכרות", "ikerut");
        responseString = responseString.replaceAll("פגישת", "Pgishat");
        responseString = responseString.replaceAll("פגישה", "Pgisha");
        responseString = responseString.replaceAll("יעוץ", "Iuts");
        responseString = responseString.replaceAll("טיפול", "Tipul");
        responseString = responseString.replaceAll("זוהר", "zoer");
        responseString = responseString.replaceAll("יופי", "yoffi");
        responseString = responseString.replaceAll("מזוטרפיה", "mezoterapya");
        responseString = responseString.replaceAll("החדרת", "ahdarat");
        responseString = responseString.replaceAll("לחות", "lahut");
        responseString = responseString.replaceAll("פיגמנטציה", "pigmentasya");
        responseString = responseString.replaceAll("אקנה", "akne");
        responseString = responseString.replaceAll("אנטיאייג'ינג", "antiaging");
        responseString = responseString.replaceAll("מיצוק", "Mitzuk");
        responseString = responseString.replaceAll("פילינג", "Piling");
        responseString = responseString.replaceAll("לק", "Lak");
        responseString = responseString.replaceAll("ברגליים", "reglaim");
        responseString = responseString.replaceAll("מניקור", "Manikur");
        responseString = responseString.replaceAll("אקספרס", "express");
        responseString = responseString.replaceAll("פדיקור", "Pedikur");
        responseString = responseString.replaceAll("רפואי", "refui");
        responseString = responseString.replaceAll("בנייה", "Bnya");
        responseString = responseString.replaceAll("בניית", "Bnyat");
        responseString = responseString.replaceAll("מילוי", "Miluy");
        responseString = responseString.replaceAll("הסרת", "Asarat");
        responseString = responseString.replaceAll("ג'ל", "dgel");
        responseString = responseString.replaceAll("ציפורניים", "Bniyat tzipornaim");
        responseString = responseString.replaceAll("ג'ל", "dgel");
        responseString = responseString.replaceAll("תיקון", "Tikun");
        responseString = responseString.replaceAll("ציפורן", "tzipornayim");
        responseString = responseString.replaceAll("רגיל", "ragil");
        responseString = responseString.replaceAll("מילוי", "Milui");
        responseString = responseString.replaceAll("הדבקת", "Adbakat");
        responseString = responseString.replaceAll("ציפורניים", "tzipornayim");
        responseString = responseString.replaceAll("גבות", "gabot");
        responseString = responseString.replaceAll("סידור", "Sidur");
        responseString = responseString.replaceAll("שפם", "Sapham");
        responseString = responseString.replaceAll("ידיים", "yadaim");
        responseString = responseString.replaceAll("רגליים", "reglaim");
        responseString = responseString.replaceAll("שחי", "shehi");
        responseString = responseString.replaceAll("לייזר", "Leizer");
        responseString = responseString.replaceAll("עליונה", "ilyona");
        responseString = responseString.replaceAll("רגל", "regel");
        responseString = responseString.replaceAll("תחתונה", "tahtona");
        responseString = responseString.replaceAll("תחתון", "tahton");
        responseString = responseString.replaceAll("ישבן", "yashvan");
        responseString = responseString.replaceAll("גב", "gav");
        responseString = responseString.replaceAll("ר", "r");
        responseString = responseString.replaceAll("בטן", "beten");
        responseString = responseString.replaceAll("עורף", "oref");
        responseString = responseString.replaceAll("סנטר", "santer");
        responseString = responseString.replaceAll("שפם", "safam");
        responseString = responseString.replaceAll("לחיים", "lahaim");
        responseString = responseString.replaceAll("חזה", "haze");
        responseString = responseString.replaceAll("פס", "pas");
        responseString = responseString.replaceAll("שעווה", "Shaava");
        responseString = responseString.replaceAll("פנים", "panim");
        responseString = responseString.replaceAll("מפשעות", "mafshaut");
        responseString = responseString.replaceAll("פאות", "peot");
        responseString = responseString.replaceAll("רגליים", "reglaim");
        responseString = responseString.replaceAll("חצי", "hatzi");
        responseString = responseString.replaceAll("בקיני", "bikini");
        responseString = responseString.replaceAll("י", "ן");
        responseString = responseString.replaceAll("רגליים", "reglaim");
        responseString = responseString.replaceAll("ידיים", "adaim");
        responseString = responseString.replaceAll("גב", "gav");
        responseString = responseString.replaceAll("חזה", "haze");
        responseString = responseString.replaceAll("כתפיים", "ktefaim");
        responseString = responseString.replaceAll("יד", "yad");
        responseString = responseString.replaceAll("חצי", "hatzi");
        responseString = responseString.replaceAll("קבוע", "kavua");
        responseString = responseString.replaceAll("אייליינר", "aylayner ");
        responseString = responseString.replaceAll("שפתיים", "sfataim");
        responseString = responseString.replaceAll("חידוש", "Hidush");
        responseString = responseString.replaceAll("ערב", "erev");
        responseString = responseString.replaceAll("כלה", "kala");
        responseString = responseString.replaceAll("קבוע", "kavua");
        responseString = responseString.replaceAll("תספורת", "Tisporet");
        responseString = responseString.replaceAll("ילד", "eled");
        responseString = responseString.replaceAll("אישה", "isha");
        responseString = responseString.replaceAll("פן", "Fen");
        responseString = responseString.replaceAll("קצר", "katzar");
        responseString = responseString.replaceAll("ארוך", "aroh");
        responseString = responseString.replaceAll("כלול", "kalul");
        responseString = responseString.replaceAll("בייביליס", "beybilis");
        responseString = responseString.replaceAll("תסרוקת", "Tisroket");
        responseString = responseString.replaceAll("ערב", "erev");
        responseString = responseString.replaceAll("זקן", "zakan");
        responseString = responseString.replaceAll("החלקה", "ahlaka");
        responseString = responseString.replaceAll("צבע", "Tzeva");
        responseString = responseString.replaceAll("שורש", "shoresh");
        responseString = responseString.replaceAll("שטיפה", "Shtifa");
        responseString = responseString.replaceAll("גוונים", "Gvanim");
        responseString = responseString.replaceAll("ראש", "rosh");
        responseString = responseString.replaceAll("מלא", "male");
        responseString = responseString.replaceAll("חצי", "hatzi");
        responseString = responseString.replaceAll("אומברה", "Umbra");
        responseString = responseString.replaceAll("בליאז'", "Blidg");
        responseString = responseString.replaceAll("הבהרה", "Avara");
        responseString = responseString.replaceAll("פתיחת", "Ptihat");
        responseString = responseString.replaceAll("תלתלים", "taltalim");
        responseString = responseString.replaceAll("החלקת", "Ahlakat");
        responseString = responseString.replaceAll("קארטין", "keratin");
        responseString = responseString.replaceAll("החלקה", "Ahlaka");
        responseString = responseString.replaceAll("אורגנית", "organit");
        responseString = responseString.replaceAll("משולבת", "meshulevet");
        responseString = responseString.replaceAll("יפנית", "yapanit");
        responseString = responseString.replaceAll("מחליק", "Mahlik");
        responseString = responseString.replaceAll("גיהוץ", "Giutz");
        responseString = responseString.replaceAll("טלפונית", "telephonit");
        responseString = responseString.replaceAll("שיחה", "Siha");
        responseString = responseString.replaceAll("התקנה", "Atkana");
        responseString = responseString.replaceAll("עבודה", "avoda");
        responseString = responseString.replaceAll("קצרה", "kzara");
        responseString = responseString.replaceAll("שעה", "shaa");
        responseString = responseString.replaceAll("שעתיים", "shaataim");
        responseString = responseString.replaceAll(" ארוכה", "aruka");
        responseString = responseString.replaceAll("שעות", "shaot");
        responseString = responseString.replaceAll("לקוח", "lakuah");
        responseString = responseString.replaceAll("עם", "im");
        responseString = responseString.replaceAll("יום", "Yom");
        responseString = responseString.replaceAll("שלם", "shalem");
        responseString = responseString.replaceAll("מלווה", "melave");
        responseString = responseString.replaceAll("ניסיון", "niasyon");
        responseString = responseString.replaceAll("איפור", "Ipur");
        responseString = responseString.replaceAll("כלה", " kala");
        responseString = responseString.replaceAll("מלווה", "melave");
        responseString = responseString.replaceAll("חבילת", "Havilat");
        responseString = responseString.replaceAll("שיער", "siar");
        responseString = responseString.replaceAll("הגעה", "Agaa");
        responseString = responseString.replaceAll("ליווי", "livui");
        responseString = responseString.replaceAll("לאירוע", "laIrua");
        responseString = responseString.replaceAll("מחיר", "mehir");
        responseString = responseString.replaceAll("הצעת", "atzaat");
        responseString = responseString.replaceAll("חדרים", "haderim");
        responseString = responseString.replaceAll("דירת", "dira");
        responseString = responseString.replaceAll("הדברה", "Adbara");
        responseString = responseString.replaceAll("יחידת דיור", "yahidat diur");
        responseString = responseString.replaceAll("בית", "bayt");
        responseString = responseString.replaceAll("פרטי", "prati");
        responseString = responseString.replaceAll("קטן", "katan");
        responseString = responseString.replaceAll("גדול", "gadol");
        responseString = responseString.replaceAll("ביוב", "biyv");
        responseString = responseString.replaceAll("מרכזי", "merkazi");
        responseString = responseString.replaceAll("הדברת", "Adbarat");
        responseString = responseString.replaceAll("חיצונית", "hitzonit");
        responseString = responseString.replaceAll("חצר", "hatzer");
        responseString = responseString.replaceAll("חנות", "hanut");
        responseString = responseString.replaceAll("עסק", "esek");
        responseString = responseString.replaceAll("משרד", "misrad");
        responseString = responseString.replaceAll("מוסדות", "mosdot");
        responseString = responseString.replaceAll("ציבור", "tzibur");
        responseString = responseString.replaceAll("שיעור", "Shiur");
        responseString = responseString.replaceAll("אישי", "ishi");
        responseString = responseString.replaceAll("אימון", "Imun ");
        responseString = responseString.replaceAll("זוגי", "zugi");
        responseString = responseString.replaceAll("תוכנית", "tohnit");

        return responseString;
    }

    public void deleteAccount() {
        System.out.println("//----------------------- DELETION ------------------------//");
        System.out.println("== ACCOUNTS FOR DELETION: " + cookies.size());
        for (int i = 0; i < cookies.size(); i++) {
            String value_delete = cookies.get(i);

            int AccountNumber = 0;

            delete_response = given().cookies(key, value_delete).
                    header("content-type", "application/x-www-form-urlencoded").
                    header("user-agent", "alpalch-qpEzhaOvY0Ecb4e0").
                    header("X-Requested-With", "XMLHttpRequest").
                    when().log().all().
                    delete("/settings/business/account").
                    then().
                    extract().response();
            AccountNumber = AccountNumber + 1;
            System.out.println("Delete account " + AccountNumber + " code: " + delete_response.getStatusCode());
            status = String.valueOf(delete_response.getStatusCode());

            if (delete_response.getStatusCode() != 401) {
                System.out.println("VALUE FAILD! - " + value_delete);
            }
        }
    }

    public String checkAccountDeletion() {
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
                    formParam("email", "katalon14444@gmail.com").
                    formParam("pass", "Pa$$w@rd").
                    when().
                    post("/check-login").then().extract().response();
            c = response.getCookies().toString();

        }
        return c;
    }

}
