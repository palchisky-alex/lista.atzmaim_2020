package im.atzma.lista2020.tests;

import org.testng.annotations.Test;

import java.io.IOException;

public class Login extends TestBase{

    @Test
    public void login() throws InterruptedException, IOException {
        app.goTo().calendarPage();
        app.goTo().login();
    }
}
