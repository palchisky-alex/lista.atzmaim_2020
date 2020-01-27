package im.atzma.lista2020.tests;


import im.atzma.lista2020.model.ClientData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ClientCreationTest extends TestBase {
    @Test
    public void photos() throws InterruptedException, IOException {
        app.goTo().clientPage();
        app.clientList().initAddNewClient();
        app.client().fillClientForm(new ClientData("Temp Client katalon", "0547019283",
                "temp@gmail.com", "רוקח 18, רמת גן, ישראל"));


    }

    @Test(priority = 1)
    public void initNewClientCreation() throws InterruptedException, IOException {

        app.goTo().clientPage();
        app.clientList().initAddNewClient();
        try {
            Assert.assertTrue(app.driver.getCurrentUrl().matches("https://lista.atzma.im/he/adding-client"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void verifyClientForm() {
        int expected = 20;
        int actual = app.client().verifyNewClientForm();

        try {
            Assert.assertEquals(actual, expected, "number of elements on client form");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 3)
    public void fillClientForm() throws InterruptedException {
        app.client().fillClientForm(new ClientData("Temp Client katalon", "0547019283",
                "temp@gmail.com", "רוקח 18, רמת גן, ישראל"));

        String actual = app.client().verifyNewClientCreation();
        String expected = "Temp Client katalon";
        try {
            Assert.assertEquals(actual, expected);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 4)
    public void testCreateClientCount() throws InterruptedException, IOException {
        app.goTo().clientPage();
        System.out.println("Number of client before client creation = " + app.clientList().getClientCount());
        int before = app.clientList().getClientCount();
        app.clientList().initAddNewClient();
        app.client().fillClientForm(new ClientData("Temp Client katalon", "0547019283",
                "temp@gmail.com", "רוקח 18, רמת גן, ישראל"));
        app.goTo().clientPage();
        System.out.println("Number of client after client creation = " + app.clientList().getClientCount());
        int after = app.clientList().getClientCount();

        Assert.assertEquals(after, before + 1);
    }


}
