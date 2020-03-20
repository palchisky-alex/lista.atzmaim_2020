package im.atzma.lista2020.tests;

import im.atzma.lista2020.appmanager.RestRequests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ServiceCreationTests extends TestBase {

    @Test
    public void creatNewService() {
        int id = 0;
        for (int i = 0; i <10 ; i++) {

            id = app.serviceCreationHelper().createService();
        }
        Assert.assertTrue(id > 0);
    }

}
