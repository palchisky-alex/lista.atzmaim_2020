package im.atzma.lista2020.tests;

import im.atzma.lista2020.model.ClientData;
import im.atzma.lista2020.model.ModifyClientData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ClientModificationTest extends TestBase {


    @Test(priority = 1)
    public void testVerifyClientForm() throws InterruptedException, IOException {
        System.out.println("=== Test 0: number of elements on new client form equals to 20? ===");

        int expected = 20;
        app.goTo().clientPage();
        app.clientList().initAddNewClient();
        int actual = app.client().verifyNewClientForm();

        try {
            Assert.assertEquals(actual, expected, "number of elements on client form equals to 20?");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void testCreateClientCount_1() throws InterruptedException, IOException {
        System.out.println("=== Test 1: Create first client and verify creation in client list ===");

        app.goTo().clientPage();
        System.out.println("Number of client before client creation = " + app.clientList().getClientCount());
        int before = app.clientList().getClientCount();
        app.clientList().initAddNewClient();
        app.client().fillClientForm(new ClientData("Temp Client katalon", "0547111111",
                "temp@gmail.com", "רוקח 18, רמת גן, ישראל"));
        app.goTo().clientPage();
        System.out.println("Number of client after client creation = " + app.clientList().getClientCount());
        int  after = app.clientList().getClientCount();

        Assert.assertEquals(after, before + 1);
    }

    @Test(priority = 3)
    public void testCreateClientCount_2() throws InterruptedException, IOException {
        System.out.println("=== Test 2: Create second client and verify creation in client list ===");

        app.goTo().clientPage();
        System.out.println("Number of client before client creation = " + app.clientList().getClientCount());
        int before = app.clientList().getClientCount();
        app.clientList().initAddNewClient();
        app.client().fillClientForm(new ClientData("Temp Client katalon", "0547222222",
                "temp@gmail.com", "רוקח 18, רמת גן, ישראל"));
        app.goTo().clientPage();
        System.out.println("Number of client after client creation = " + app.clientList().getClientCount());
        int  after = app.clientList().getClientCount();

        Assert.assertEquals(after, before + 1);
    }

    @Test(priority = 4)
    public void testModificateClient_1() throws InterruptedException, IOException {
        System.out.println("=== Test 3: Verify that first client changed after modification (not equal to past) ===");

//        File photo = new File("src/test/resources/putin.jpg");
//        System.out.println(photo.getAbsolutePath());
//        System.out.println(photo.exists());

        app.goTo().clientPage();
        app.clientList().selectClient(0);
        List<ClientData> before =  app.client().getClientList();

        app.client().initClientModification();
        app.client().modify(new ModifyClientData("New temp client catalon", "054837465",
                "new_katalon@gmail.com", "Balfour St 33, Petah Tikva, Israel", "1960",
                "11", "11", 2, "new note"));

        app.goTo().clientPage();
        app.clientList().selectClient(0);
        List<ClientData> after =  app.client().getClientList();
        Assert.assertNotEquals(after, before);
    }

    @Test(priority = 5)
    public void testModificateClient_2() throws InterruptedException, IOException {
        System.out.println("=== Test 4: Verify that second client changed after modification (not equal to past) ===");

        app.goTo().clientPage();
        app.clientList().selectClient(1);
        List<ClientData> before =  app.client().getClientList();

        app.client().initClientModification();
        app.client().modify(new ModifyClientData("New temp client catalon", "054837465",
                "new_katalon@gmail.com", "Balfour St 33, Petah Tikva, Israel", "1960",
                "11", "11", 2, "new note"));

        app.goTo().clientPage();
        app.clientList().selectClient(1);
        List<ClientData> after =  app.client().getClientList();
        Assert.assertNotEquals(after, before);
    }

    @Test(priority = 6)
    public void testCompareClient() throws InterruptedException, IOException {
        System.out.println("=== Test 5: Compare first client elements with same second after modification ===");

        app.goTo().clientPage();
        app.clientList().selectClient(0);
        List<ClientData> before =  app.client().getClientList();

        app.goTo().clientPage();
        app.clientList().selectClient(1);
        List<ClientData> after =  app.client().getClientList();

        Assert.assertEquals(after, before);


    }
}
