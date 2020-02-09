package im.atzma.lista2020.tests;

import im.atzma.lista2020.model.ClientData;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClientDeletionTest extends TestBase {

    @Test(priority = 1)
    public void testDeleteClientId() throws InterruptedException, IOException {
        System.out.println("=== Create two same client / delete client with max ID / verify first client not deleted ===");

        app.goTo().clientPage();
        ClientData clientData = new ClientData("testDeleteClientId_1", "0547333334", "katalon@gmail.com",
                "רוקח 18, רמת גן, ישראל");

        app.clientList().initAddNewClient();
        app.client().fillClientForm(clientData);
        app.goTo().clientPage();
        Set<ClientData> before = app.clientList().getClientId();

        app.clientList().initAddNewClient();
        app.client().fillClientForm(clientData);
        app.goTo().clientPage();

        Set<ClientData> after_add_client = app.clientList().getClientId();
        for(ClientData c: after_add_client) {
            System.out.println(c);
        }

//        int max = after_add_client.stream().max(Comparator.comparingInt(ClientData::getId)).get().getId();
        int max = after_add_client.stream().mapToInt((g) -> g.getId()).max().getAsInt();
        System.out.println("max id- " + max);

        app.clientList().deleteClientWithMaxId(max);
        Set<ClientData> after = app.clientList().getClientId();

        assertThat(after, equalTo(before));

    }

}
