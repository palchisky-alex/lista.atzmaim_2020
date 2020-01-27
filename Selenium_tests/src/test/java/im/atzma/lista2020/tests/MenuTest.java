package im.atzma.lista2020.tests;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class MenuTest extends TestBase {

    @Test(priority = 1)
    public void verifyLinkText() throws InterruptedException, IOException {
        Executor executor = Executor.newInstance();
        String json = executor.execute(Request.Post("https://atzma.im/check-login")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .bodyForm(new BasicNameValuePair("email", "katalon25@gmail.com"),
                        new BasicNameValuePair("pass", "Pa$$w@rd"),
                        new BasicNameValuePair("time_zone", "Asia/Jerusalem"))).returnContent().asString();
        String json2 = executor.execute(Request.Get("https://atzma.im/clients?limit=20&offset=0&q=Test_changeClientsData"))
                .returnContent().asString();
        System.out.println(json);


        app.goTo().calendarPage();


        String expected = "[יומן, מאגר לקוחות, תזכורות, קבוצות, טיפולים, הגדרות, תמיכה, Rate us, לצאת]";
        List<String> actual = app.calendar().verifyMenuLinks();
        //--------------------------------------------------------------------------verify menu elements
        Assert.assertEquals(actual.toString(), expected);
    }
}
