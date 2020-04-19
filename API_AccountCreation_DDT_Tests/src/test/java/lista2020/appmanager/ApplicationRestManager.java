package lista2020.appmanager;

import io.restassured.RestAssured;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.config;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.RedirectConfig.redirectConfig;

public class ApplicationRestManager {

    AccountCreationHelper accountCreationHelper;

    Properties properties = new Properties();

    public void init() {

        baseURI = "https://lista.atzma.im";
        RestAssured.config = config().redirect(redirectConfig().followRedirects(true).and().maxRedirects(10)).and().
                and().config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        RestAssured.useRelaxedHTTPSValidation();
        accountCreationHelper = new AccountCreationHelper();
    }


    public void stop() throws InterruptedException, IOException {

    }

    public AccountCreationHelper accountCreationHelper() { return accountCreationHelper; }

}
