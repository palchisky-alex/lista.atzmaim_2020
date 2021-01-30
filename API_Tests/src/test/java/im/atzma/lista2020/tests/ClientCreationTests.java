package im.atzma.lista2020.tests;

import org.approvaltests.Approvals;
import org.testng.annotations.Test;

import java.io.IOException;


public class ClientCreationTests extends TestBase {

    @Test
    public void createClient() throws IOException {
        app.clientCreationHelper().createClient();
        Approvals.verify(app.clientCreationHelper().getClientList());

    }
}
