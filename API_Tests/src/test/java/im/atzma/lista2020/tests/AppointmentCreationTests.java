package im.atzma.lista2020.tests;

import im.atzma.lista2020.appmanager.RestRequests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AppointmentCreationTests extends TestBase{

    @Test
    public void createAppointment() throws IOException {

        for (int i = 0; i < 100; i++) {

            int service_id = app.serviceCreationHelper().createService();
            int client_id = app.clientCreationHelper().createClient();
            app.appointmentHelper().createAppointment(client_id, service_id);

            app.appointmentHelper().deleteAppointment();
            app.serviceCreationHelper().deleteService();
            app.clientCreationHelper().deleteClient();
        }

    }
}
