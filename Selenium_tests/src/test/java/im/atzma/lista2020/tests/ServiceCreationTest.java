package im.atzma.lista2020.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ServiceCreationTest extends TestBase {

    @Test(priority = 1)
    public void testVerifyURL() throws InterruptedException, IOException {
        app.goTo().servicesPage();
        Assert.assertTrue(app.driver.getCurrentUrl().matches("https://lista.atzma.im/he/catalog/services"));
    }

    @Test(priority = 2)
    public void testVerifyDefaultServices30min() throws Exception {

        try {
            List<WebElement> elementList = app.service().verifyDefaultService_30min();
            for (int i = 0; i < elementList.size(); i++) {
                //--------------------------------------------------------------------------verify default service elements (30 min)
                Assert.assertTrue(elementList.get(i).isDisplayed());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //--------------------------------------------------------------------------verify trash icon present
            Assert.assertTrue(app.service().verifyTrashIconPresent_1());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 3)
    public void testVerifyDefaultServices45min() throws Exception {
        try {
            List<WebElement> elementList = app.service().verifyDefaultService_45min();
            for (int i = 0; i < elementList.size(); i++) {
                //--------------------------------------------------------------------------verify default service elements (45 min)
                Assert.assertTrue(elementList.get(i).isDisplayed());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //--------------------------------------------------------------------------verify trash icon present
            Assert.assertTrue(app.service().verifyTrashIconPresent_2());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test(priority = 4)
    public void testVerifyDefaultServices60min() throws Exception {
        try {
            List<WebElement> elementList = app.service().verifyDefaultService_60min();
            for (int i = 0; i < elementList.size(); i++) {
                //--------------------------------------------------------------------------verify default service elements (60 min)
                Assert.assertTrue(elementList.get(i).isDisplayed());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //--------------------------------------------------------------------------verify trash icon present
            Assert.assertTrue(app.service().verifyTrashIconPresent_3());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            //--------------------------------------------------------------------------verify trash icon not present
//            Assert.assertFalse(app.getServicesPage().verifyTrashIcon());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        app.service().initAddNewService();
    }

    @Test(priority = 5)
    public void testVerifyDefaultServiceFormElements() {
        Assert.assertTrue(app.driver.getCurrentUrl().matches("https://lista.atzma.im/he/catalog/services/adding"));

        List<WebElement> elementList = app.service().verifyDefaultServiceFormElements();
        for (int i = 0; i < elementList.size(); i++) {
            //--------------------------------------------------------------------------verify Default Service Form Elements
            Assert.assertTrue(elementList.get(i).isDisplayed());
        }

        String disabledButton_expected = "rgba(155, 155, 155, 1)";
        String disabledButton_actual = app.service().verifyDisabledButtonColor();

        try {
            //--------------------------------------------------------------------------verify save new service disabled button css
            Assert.assertEquals(disabledButton_actual, disabledButton_expected);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test(priority = 6)
    public void testFillServiceForm() throws InterruptedException, IOException {

        app.goTo().servicesPage();
        app.service().fillServiceFrom("Temp services_katalon", "Temp category_katalon");
//        app.goTo().servicesPage();
        String actual = app.service().verifyTempService("Temp services_katalon");
        String expected = "Temp services_katalon";
        Assert.assertEquals(actual, expected);

    }


}
