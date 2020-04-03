package im.atzma.lista2020.tests;

import org.approvaltests.Approvals;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class ClientModificationTests extends TestBase {

    @Test(priority = 1)
    public void modificateClient() throws IOException, InterruptedException {
        int client_id = app.clientCreationHelper().createClient();
        app.clientCreationHelper().modificateClient(client_id);

        Approvals.verify(app.clientCreationHelper().getClientData(client_id));

    }
}
