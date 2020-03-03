package im.atzma.lista2020.tests;

import org.testng.annotations.Test;

public class ServiceCreationTests extends TestBase {

    @Test
    public void creatNewService() {
        //app.serviceCreationHelper().getServiceList();
        for (int i = 0; i < 100; i++) {
            app.serviceCreationHelper().createService();
        }
//        app.serviceCreationHelper().deleteService();
    }

}
