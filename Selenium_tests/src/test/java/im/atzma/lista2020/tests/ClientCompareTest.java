package im.atzma.lista2020.tests;

import im.atzma.lista2020.model.ClientData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ClientCompareTest extends TestBase {

    @Test(priority = 1)
    public void testCreateClientCount_1() throws InterruptedException, IOException {
        System.out.println("=== Create first client and verify creation in client list ===");

        app.goTo().clientPage();
        System.out.println("Number of client before client creation = " + app.clientList().getClientCount());
        int before = app.clientList().getClientCount();
        app.clientList().initAddNewClient();
        app.client().fillClientForm(new ClientData("Temp Client katalon", "0547111111",
                "temp@gmail.com", "רוקח 18, רמת גן, ישראל"));
        app.goTo().clientPage();
        System.out.println("Number of client after client creation = " + app.clientList().getClientCount());
        int after = app.clientList().getClientCount();

        Assert.assertEquals(after, before + 1);
    }

    @Test(priority = 2)
    public void testCreateClientCount_2() throws InterruptedException, IOException {
        System.out.println("=== Create second client and verify creation in client list ===");

        app.goTo().clientPage();
        System.out.println("Number of client before client creation = " + app.clientList().getClientCount());
        int before = app.clientList().getClientCount();
        app.clientList().initAddNewClient();
        app.client().fillClientForm(new ClientData("Temp Client katalon", "0547222222",
                "temp@gmail.com", "רוקח 18, רמת גן, ישראל"));
        app.goTo().clientPage();
        System.out.println("Number of client after client creation = " + app.clientList().getClientCount());
        int after = app.clientList().getClientCount();

        Assert.assertEquals(after, before + 1);
    }


    @Test(priority = 3)
    public void testCompareClient() throws InterruptedException, IOException {
        System.out.println("=== Compare  first client elements with same second ===");

        app.goTo().clientPage();
        app.clientList().selectClient(0);
        List<ClientData> before =  app.client().getClientList();

        app.goTo().clientPage();
        app.clientList().selectClient(1);
        List<ClientData> after =  app.client().getClientList();

        Assert.assertEquals(after, before);

    }
}
