package im.atzma.lista2020.tests;
import org.approvaltests.Approvals;
import org.testng.annotations.Test;

import java.util.HashSet;

import static org.testng.Assert.assertEquals;

public class ServiceCreationTests extends TestBase {

    @Test(priority = 1)
    public void creatNewService() {
        int id = app.serviceCreationHelper().createCategory();
        app.serviceCreationHelper().createService(id);

        Approvals.verify(app.serviceCreationHelper().getServiceList());

    }


}
