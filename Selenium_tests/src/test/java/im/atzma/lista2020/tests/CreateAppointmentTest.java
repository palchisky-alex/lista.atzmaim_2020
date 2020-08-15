package im.atzma.lista2020.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class CreateAppointmentTest extends TestBase {

    @Test()
    public void testAppointmentCreation_A() throws InterruptedException, IOException {
        app.goTo().calendarPage();
        app.appointment().create_A();
        app.appointment().addNewQueueAdress();
        app.appointment().addQueueNote("My note");
        app.appointment().saveForm();

        String expected = "[◀ Duration: 1ש15דק ▶, ◀ Time: 12:00 - 13:15 ▶, ◀ Client name: לקוח מזדמן ▶, ◀ Service:  ▶, ◀ Address: רוקח 18, רמת גן, ישראל ▶, ◀ Note: My note ▶]";
        List<String> actual = app.appointment().verifyAppointmentCreation();
        Assert.assertEquals(actual.toString(), expected, "verify appointment creation");
    }

    @Test()
    public void testAppointmentCreation_B() throws InterruptedException, IOException {
        app.goTo().calendarPage();
        app.appointment().create_B("Temp Client", "0547613154", "Temp Services", "Temp Category");
        app.appointment().addNewQueueAdress();
        app.appointment().addQueueNote("My note");
        app.appointment().saveForm();

        String expected = "[◀ Duration: 1ש15דק ▶, ◀ Time: 12:00 - 13:15 ▶, ◀ Client name: Temp Client ▶, ◀ Service: Temp Services ▶, ◀ Address: רוקח 18, רמת גן, ישראל ▶, ◀ Note: My note ▶]";
        List<String> actual = app.appointment().verifyAppointmentCreation();
        Assert.assertEquals(actual.toString(), expected, "verify appointment creation");
    }

    @Test()
    public void testAppointmentCreation_C() throws InterruptedException, IOException {
        app.goTo().calendarPage();
        app.appointment().create_C("Temp Client katalon", "Temp services_katalon");
        app.appointment().addQueueAdress();
        app.appointment().addQueueNote("My note");
        app.appointment().saveForm();

        String expected = "[◀ Duration: 1ש45דק ▶, ◀ Time: 12:00 - 13:45 ▶, ◀ Client name: Temp Client katalon ▶, ◀ Service: Temp services_katalon ▶, ◀ Address: רוקח 18, רמת גן, ישראל ▶, ◀ Note: My note ▶]";
        List<String> actual = app.appointment().verifyAppointmentCreation();
        Assert.assertEquals(actual.toString(), expected, "verify appointment creation");
    }

    @Test()
    public void testAppointmentCreation_D() throws InterruptedException, IOException {
        app.goTo().calendarPage();
        app.appointment().create_D("Temp Client katalon");
        app.appointment().addQueueAdress();
        app.appointment().addQueueNote("My note");
        app.appointment().saveForm();

        String expected = "[◀ Duration: 1ש15דק ▶, ◀ Time: 12:00 - 13:15 ▶, ◀ Client name: Temp Client katalon ▶, ◀ Service:  ▶, ◀ Address: רוקח 18, רמת גן, ישראל ▶, ◀ Note: My note ▶]";
        List<String> actual = app.appointment().verifyAppointmentCreation();
        Assert.assertEquals(actual.toString(), expected, "verify appointment creation");
    }

    @Test()
    public void testAppointmentCreation_E() throws InterruptedException, IOException {
        app.goTo().calendarPage();
        app.appointment().create_E("Temp services_katalon");
        app.appointment().addNewQueueAdress();
        app.appointment().addQueueNote("My note");
        app.appointment().saveForm();

        String expected = "[◀ Duration: 1ש45דק ▶, ◀ Time: 12:00 - 13:45 ▶, ◀ Client name: לקוח מזדמן ▶, ◀ Service: Temp services_katalon ▶, ◀ Address: רוקח 18, רמת גן, ישראל ▶, ◀ Note: My note ▶]";
        List<String> actual = app.appointment().verifyAppointmentCreation();
        Assert.assertEquals(actual.toString(), expected, "verify appointment creation");
    }

    @Test()
    public void testAppointmentCreation_F() throws InterruptedException, IOException {
        app.goTo().calendarPage();
        app.appointment().create_F("Temp Client katalon");
        app.appointment().addServiceCategory("Temp services_katalon", "Temp category_katalon");
        app.appointment().addQueueAdress();
        app.appointment().addQueueNote("My note");
        app.appointment().saveForm();

        String expected = "[◀ Duration: 1ש15דק ▶, ◀ Time: 12:00 - 13:15 ▶, ◀ Client name: Temp Client katalon ▶, ◀ Service: Temp services_katalon ▶, ◀ Address: רוקח 18, רמת גן, ישראל ▶, ◀ Note: My note ▶]";
        List<String> actual = app.appointment().verifyAppointmentCreation();
        Assert.assertEquals(actual.toString(), expected, "verify appointment creation");
    }

    @Test()
    public void testAppointmentCreation_G() throws InterruptedException, IOException {
        app.goTo().clientPage();
        app.appointment().create_G("Temp Client katalon", "Temp services_katalon");
        app.appointment().addQueueAdress();
        app.appointment().addQueueNote("My note");
        app.appointment().saveForm();

        String expected = "[◀ Duration: 1ש45דק ▶, ◀ Time: 12:00 - 13:45 ▶, ◀ Client name: Temp Client katalon ▶, ◀ Service: Temp services_katalon ▶, ◀ Address: רוקח 18, רמת גן, ישראל ▶, ◀ Note: My note ▶]";
        List<String> actual = app.appointment().verifyAppointmentCreation();
        Assert.assertEquals(actual.toString(), expected, "verify appointment creation");
    }


}
