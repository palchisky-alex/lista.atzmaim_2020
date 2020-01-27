package im.atzma.lista2020.tests;

import im.atzma.lista2020.model.ClientData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ModifyAppointmentTest extends TestBase {

    //    @Test(priority = 3)
//    public void createTempService() throws InterruptedException {
//        app.goTo().servicesPage();
//        app.service().initAddNewService();
//        app.service().fillServiceFrom("Temp services_katalon_2", "Temp category_katalon_2");
//        app.service().saveServiceFrom();
//
//        String actual = app.service().verifyTempService("Temp services_katalon_2");
//        String expected = "Temp services_katalon_2";
//        try {
//            Assert.assertEquals(actual, expected);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    @Test(priority = 3)
    public void createSecondTempClient() throws InterruptedException, IOException {
        app.goTo().clientPage();
        app.clientList().initAddNewClient();
        app.client().fillClientForm(new ClientData("Temp Client katalon_2", "0547613154",
                "temp2@gmail.com", "רוקח 19, תל אביב, ישראל"));

        String actual = app.client().verifyNewClientCreation();
        String expected = "Temp Client katalon_2";
        try {
            Assert.assertEquals(actual, expected);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 4)
    public void modifyAppointment() throws InterruptedException, IOException {
        app.goTo().calendarPage();
        app.appointment().clickOnExistsAppointment();
        app.appointment().initAppModification();

        app.appointment().modifyService();
        app.appointment().addServiceCategory("Temp services_katalon_2", "Temp category_katalon_2");
        app.appointment().modifyAppTime();
        app.appointment().modifyServiceDuration();
        app.appointment().modifyServicePrice();

        app.appointment().modifyClient();
        app.appointment().fillNewAppointment("Temp Client katalon_2");

        app.appointment().saveForm();

        String expected = "[10:00 - 11:45, Temp Client katalon_2, Temp services_katalon_2, 1ש45דק]";
        List<String> actual = app.appointment().verifyAppointmentCreation2();
        try {
            Assert.assertEquals(actual.toString(), expected, "verify appointment modification");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 5)
    public void verifyModifiedAppointment() throws InterruptedException {
        app.appointment().clickOnExistsAppointment2();

        String expected = "[Temp Client katalon_2, 0547613154, 10:00 - 11:45, 1ש45דק, Temp services_katalon_2, 100 ₪, מחק, ערוך, ×]";
        List<String> actual = app.appointment().verifyAppointmentElements();
        try {
            Assert.assertEquals(actual.toString(), expected);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test(priority = 6)
    public void verifyModifiedAppointmentForm() throws InterruptedException {
        app.appointment().initAppModification();
        String expected = "[1ש 45דק, ₪ 100, Temp services_katalon_2 ⨯ 1, 10:00-11:45, Temp Client katalon_2, חזור, שמור]";
        List<String> actual = app.appointment().verifyForm();

        try {
            Assert.assertEquals(actual.toString(), expected);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
