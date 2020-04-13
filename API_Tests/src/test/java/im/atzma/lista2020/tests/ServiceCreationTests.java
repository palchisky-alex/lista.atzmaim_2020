package im.atzma.lista2020.tests;

import im.atzma.lista2020.appmanager.RestRequests;
import org.approvaltests.Approvals;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ServiceCreationTests extends TestBase {

    @Test
    public void creatNewService() {
        int id = app.serviceCreationHelper().createCategory();
        for (int i = 0; i <1 ; i++) {
           app.serviceCreationHelper().createService(id);
        }
        Approvals.verify(app.serviceCreationHelper().getServiceList());
    }

}
