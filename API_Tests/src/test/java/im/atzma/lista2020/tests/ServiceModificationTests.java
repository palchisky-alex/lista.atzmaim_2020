package im.atzma.lista2020.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ServiceModificationTests extends TestBase {

    @Test
    public void creatNewService() {
        int id = 0;
        for (int i = 0; i <1 ; i++) {

           // id = app.serviceCreationHelper().createService();
        }
        Assert.assertTrue(id > 0);
    }

}
