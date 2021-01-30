package im.atzma.lista2020.tests;

import org.approvaltests.Approvals;
import org.testng.Assert;
import org.testng.annotations.Test;

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
        app.appointmentHelper().modifyAppointment(client_id, service_id, category_id);

        Approvals.verify(app.appointmentHelper().getAppointmentList());
    }

    @Test(priority = 3)
    public void deleteAppointment() {
        app.appointmentHelper().deleteAppointment();
        Assert.assertEquals(app.appointmentHelper().getAppointmentString(), "[]");
    }

    @Test(priority = 4)
    public void verifyClientAfterAppointmentDeletion() {
        Approvals.verify(app.clientCreationHelper().getClientList());
    }

    @Test(priority = 5)
    public void verifyServiceAfterAppointmentDeletion() {
        Approvals.verify(app.serviceCreationHelper().getServiceList());
    }

    @Test(priority = 6)
    public void verifySettingsAfterAppointmentDeletion() {
        Approvals.verify(app.appointmentHelper().getSettings());
    }
}
