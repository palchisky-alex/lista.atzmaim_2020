package im.atzma.lista2020.appmanager;

import io.restassured.RestAssured;

import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.config;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.RedirectConfig.redirectConfig;

public class ApplicationRest_UI_API_Manager {

    AccountCreation_UI_API_Helper accountCreation_UI_API_Helper;
    Properties properties = new Properties();

    public void init() {

        baseURI = "https://lista.atzma.im";
        RestAssured.config = config().redirect(redirectConfig().followRedirects(true).and().maxRedirects(10)).and()
                .config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        RestAssured.useRelaxedHTTPSValidation();
        accountCreation_UI_API_Helper = new AccountCreation_UI_API_Helper();
    }


    public void stop() throws InterruptedException, IOException {

    }

    public AccountCreation_UI_API_Helper accountCreation_UI_API_Helper() { return accountCreation_UI_API_Helper; }

}
