package im.atzma.lista2020.tests;

import org.approvaltests.Approvals;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class AppointmentModificationTests extends TestBase {

    @Test(priority = 1)
    public void createAppointment() {

        int category_id = app.serviceCreationHelper().createCategory();
        int service_id = app.serviceCreationHelper().createService(category_id);
        int client_id = app.clientCreationHelper().createClient();
        app.appointmentHelper().createAppointment(client_id, service_id, category_id);

        Approvals.verify(app.appointmentHelper().getAppointmentList());
    }

    @Test(priority = 2)
    public void modifyAppointment() {
        int category_id = app.serviceCreationHelper().getCategoryID();
        int service_id = app.serviceCreationHelper().getServiceID();
        int client_id = app.clientCreationHelper().getClientID();
        app.clientCreationHelper().modificateClient(client_id);
        ArrayList<Integer> IDs = app.appointmentHelper().getAppointmentID();
        for (int i = 0; i < IDs.size(); i++) {
            int appointment_id = IDs.get(i);
            app.appointmentHelper().modifyAppointment(client_id, service_id, category_id, appointment_id);
        }

        Approvals.verify(app.appointmentHelper().getAppointmentList());

    }

    @Test(priority = 3)
    public void deleteAppointment() {
        app.appointmentHelper().deleteAppointment();
        Assert.assertEquals(app.appointmentHelper().getAppointmentString(), "[]");

    }
}
