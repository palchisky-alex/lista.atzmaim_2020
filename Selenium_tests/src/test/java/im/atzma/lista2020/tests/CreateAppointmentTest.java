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

        String expected = "[10:00 - 11:00, לקוח מזדמן, , 1 שעה, רוקח 18, רמת גן, ישראל 1, My note]";
        List<String> actual = app.appointment().verifyAppointmentCreation_A();
        Assert.assertEquals(actual.toString(), expected, "verify appointment creation");
    }

    @Test()
    public void testAppointmentCreation() throws InterruptedException, IOException {
        app.goTo().calendarPage();
        app.appointment().create("Temp Client katalon");
        app.appointment().addServiceCategory("Temp services_katalon", "Temp category_katalon");
        app.appointment().addQueueAdress();
        app.appointment().addQueueNote("My note");
        app.appointment().saveForm();

        String expected = "[10:00 - 11:00, Temp Client katalon, Temp services_katalon, 1 שעה, רוקח 18, רמת גן, ישראל, My note]";
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

        String expected = "[10:00 - 11:00, Temp Client katalon, Temp services_katalon, 1 שעה, רוקח 18, רמת גן, ישראל, My note]";
        List<String> actual = app.appointment().verifyAppointmentCreation();
        Assert.assertEquals(actual.toString(), expected, "verify appointment creation");
    }

}
