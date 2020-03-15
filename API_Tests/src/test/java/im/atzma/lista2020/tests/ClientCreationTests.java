package im.atzma.lista2020.tests;

import im.atzma.lista2020.appmanager.RestRequests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;


public class ClientCreationTests extends TestBase {

    @Test
    public void createClient() throws IOException {

        app.clientCreationHelper().createClient();
      //  Assert.assertTrue(RestRequests.count().size() > 0);
    }
}
