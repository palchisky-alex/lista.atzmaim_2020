package im.atzma.lista2020.tests;

import im.atzma.lista2020.appmanager.RestRequests;
import org.approvaltests.Approvals;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AppointmentCreationTests extends TestBase {

  //  @Test(priority = 1)
    public void createAppointment() throws IOException {

        for (int i = 0; i < 1; i++) {

            int service_id = app.serviceCreationHelper().createService();
            int client_id = app.clientCreationHelper().createClient();
            app.appointmentHelper().createAppointment(client_id, service_id);

//            Assert.assertNotEquals(app.appointmentHelper().getAppointmentList(), "[]");

//            app.appointmentHelper().deleteAppointment();
//            app.serviceCreationHelper().deleteService();
//            app.clientCreationHelper().deleteClient();
        }
    }

    @Test(priority = 2)
    public void deleteAppointment() throws IOException, InterruptedException {

        int service_id = app.serviceCreationHelper().createService();
        int client_id = app.clientCreationHelper().createClient();
        for (int i = 0; i < 1; i++) {
            app.appointmentHelper().createAppointment(client_id, service_id);
        }
//        app.init();
//        Assert.assertEquals(app.appointmentHelper().getAppointmentList(), "[]");
//        Assert.assertEquals(app.serviceCreationHelper().getServiceList(), "[]");
//        Assert.assertEquals(app.clientCreationHelper().getClientList(), "[]");

        Approvals.verify(app.appointmentHelper().getAppointmentList());


    }
}
