package im.atzma.lista2020.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class MenuTest extends TestBase {

    @Test(priority = 1)
    public void verifyLinkText() throws InterruptedException, IOException {

        app.goTo().calendarPage();


        String expected = "[יומן, מאגר לקוחות, קבוצות, מטלות, SMS, טיפולים, הגדרות, תמיכה, Rate us, לצאת]";
        List<String> actual = app.calendar().verifyMenuLinks();
        //--------------------------------------------------------------------------verify menu elements
        Assert.assertEquals(actual.toString(), expected);
    }
}
