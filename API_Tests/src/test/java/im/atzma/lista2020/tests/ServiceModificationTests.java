package im.atzma.lista2020.tests;

import org.approvaltests.Approvals;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ServiceModificationTests extends TestBase {

    @Test
    public void modificateService() {
        int category_id = app.serviceCreationHelper().createCategory();
        int service_id = app.serviceCreationHelper().createService(category_id);
        app.serviceCreationHelper().modifyService(service_id, category_id);

        Approvals.verify(app.serviceCreationHelper().getServiceList());

    }

}
