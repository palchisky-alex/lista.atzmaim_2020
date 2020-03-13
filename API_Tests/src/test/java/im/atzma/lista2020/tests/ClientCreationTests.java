package im.atzma.lista2020.tests;

import im.atzma.lista2020.appmanager.RestRequests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class ClientCreationTests extends TestBase {

    @Test
    public void createClient() throws IOException {
        RestRequests.deleteRest();
        for (int i = 0; i < 10; i++) {
            app.clientCreationHelper().createClient();
        }
        Assert.assertTrue(RestRequests.count().size() > 0);

    }
}
