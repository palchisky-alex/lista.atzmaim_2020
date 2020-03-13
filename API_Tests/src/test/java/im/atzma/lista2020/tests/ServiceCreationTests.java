package im.atzma.lista2020.tests;

import im.atzma.lista2020.appmanager.RestRequests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ServiceCreationTests extends TestBase {

    @Test
    public void creatNewService() {
        //app.serviceCreationHelper().getServiceList();
            app.serviceCreationHelper().createService();
        Assert.assertTrue(RestRequests.count().size() > 0);

//        app.serviceCreationHelper().deleteService();
    }

}
