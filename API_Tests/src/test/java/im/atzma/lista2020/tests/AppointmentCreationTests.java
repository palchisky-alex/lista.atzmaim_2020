package im.atzma.lista2020.tests;

import org.approvaltests.Approvals;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AppointmentCreationTests extends TestBase {

    @Test(priority = 1)
    public void createAppointment() {

        int category_id = app.serviceCreationHelper().createCategory();
        int service_id = app.serviceCreationHelper().createService(category_id);
        int client_id = app.clientCreationHelper().createClient();

        for (int i = 0; i < 1; i++) {

            app.appointmentHelper().createAppointment(client_id, service_id, category_id);
        }
        Approvals.verify(app.appointmentHelper().getAppointmentList());
    }

    @Test(priority = 2)
    public void deleteAppointment() {

        app.appointmentHelper().deleteAppointment();
        Assert.assertEquals(app.appointmentHelper().getAppointmentString(), "[]");
    }
}
