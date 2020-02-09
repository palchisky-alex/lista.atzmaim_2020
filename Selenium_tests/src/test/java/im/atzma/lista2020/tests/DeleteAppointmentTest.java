package im.atzma.lista2020.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class DeleteAppointmentTest extends TestBase {

    @Test(priority = 1)
    public void testAppointmentDeletion() throws InterruptedException, IOException {
        app.goTo().calendarPage();
        int before = app.appointment().appointmentList();
        app.appointment().deleteAppointment();
        app.calendar().logout();
        app.goTo().login();

        int after = app.appointment().appointmentList();
        Assert.assertEquals(after, before - 1);

    }

    @Test(priority = 2)
    public void testServiceDeletion() throws InterruptedException, IOException {
        app.goTo().servicesPage();
        app.service().deleteTempService();
        app.goTo().servicesPage();

        String expected = "[פגישה 30 דקות, פגישה 45 דקות, פגישה 60 דקות]";
        List<String> actual = app.service().verifyServiceDeletion();

        Assert.assertEquals(actual.toString(), expected, "verify service deletion");

    }
}
