package im.atzma.lista2020.tests;

import org.testng.annotations.Test;

import java.io.IOException;

public class AppointmentCreationTests extends TestBase{

    @Test
    public void createAppointment() throws IOException {
//        app.serviceCreationHelper().createService();
//        app.clientCreationHelper().createClient();
//        app.clientCreationHelper().getClientList();
        app.appointmentHelper().createAppointment();
        app.appointmentHelper().deleteAppointment();
    }
}
