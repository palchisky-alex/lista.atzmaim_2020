package im.atzma.lista2020.tests;

import org.testng.annotations.Test;

public class ServiceCreationTests extends TestBase {

    @Test
    public void creatNewService() {
        app.serviceCreationHelper().getServiceList();
       // app.serviceCreationHelper().createService();
//        app.serviceCreationHelper().deleteService();
    }

}
