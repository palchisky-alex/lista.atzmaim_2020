package im.atzma.lista2020.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingupTest extends TestBase {
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Test(priority = 1)
    public void testVerifySignupPageElements() throws Exception {
        app.goTo().homePage();
        app.goTo().singupPage();

        try {
            List<WebElement> elementList = app.singupPage().getSignupPageElements();
            for (int i = 0; i < elementList.size(); i++) {
                Assert.assertTrue(elementList.get(i).isDisplayed());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void testPasswordVisibleIcons() throws Exception {
        app.goTo().typeNewPassAndUser();


        try {
            Assert.assertTrue(app.singupPage().passwordVisible());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Assert.assertTrue(app.singupPage().eye_icon_off());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Assert.assertTrue(app.singupPage().eye_icon());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Assert.assertTrue(app.goTo().submit());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test(priority = 3)
    public void testVerifyBusinessTypePageElements() throws Exception {
        try {
            List<WebElement> elementList = app.businessPage().getBusnessPageElemenents();
            for (int i = 0; i < elementList.size(); i++) {
                Assert.assertTrue(elementList.get(i).isDisplayed());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 4)
    public void testMarkBussnessType_atzmaimButtonChecked() throws Exception {
        List<String> elementList_expected = new ArrayList<>();
        elementList_expected.add("rgba(255, 255, 255, 1)");
        elementList_expected.add("rgb(222, 206, 255) 0px 2px 8px 0px");
        elementList_expected.add("rgba(79, 45, 167, 1)");

        String submitButton_expected = "rgba(0, 0, 0, 0) linear-gradient(0deg, rgb(247, 112, 98), rgb(254, 81, 150))" +
                " repeat scroll 0% 0% / auto padding-box border-box";

        List<String> elementList_actual = app.businessPage().markBussnessType_button();

        try {
            Assert.assertEquals(elementList_actual, elementList_expected);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<WebElement> elementList = app.businessPage().markBussnessType_titles();
        for (int i = 0; i < elementList.size(); i++) {
            Assert.assertTrue(elementList.get(i).isDisplayed());
        }

        try {
            String submitButton_actual = app.businessPage().submitButtonColor();
            Assert.assertEquals(submitButton_actual, submitButton_expected);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //--------------------------------------------------------------------------Verify all-set page URL
            app.goTo().submit2();
            Assert.assertTrue(app.driver.getCurrentUrl().matches("https://lista.atzma.im/he/signup/all-set"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test(priority = 5)
    public void testVerifyAllSetPageElements() throws Exception {
        List<WebElement> elementList = app.allSetPage().getAllSetPageElements();
        for (int i = 0; i < elementList.size(); i++) {
            //--------------------------------------------------------------------------verify all-set page elements
            Assert.assertTrue(elementList.get(i).isDisplayed());
        }
    }

    @Test(priority = 6)
    public void testVerifyAllTestPageCheckboxes() {
        //-------------------------------------------------------------------------- css of default submit button
        List<String> submitButtonDefault_expected = Arrays.asList("18px, rgba(255, 255, 255, 1), " +
                "rgb(191, 175, 224) none repeat scroll 0% 0% / auto padding-box border-box");
        //-------------------------------------------------------------------------- css of active submit button
        List<String> submitButtonActive_expected = Arrays.asList("18px, rgba(255, 255, 255, 1)," +
                " rgba(0, 0, 0, 0) linear-gradient(0deg, rgb(247, 112, 98), rgb(254, 81, 150))" +
                " repeat scroll 0% 0% / auto padding-box border-box");

        try {
            //--------------------------------------------------------------------------verify css default submit button
            List<String> submitButtonDefault_actual = app.allSetPage().verifySubmitButton_default();
            Assert.assertEquals(submitButtonDefault_actual.toString(), submitButtonDefault_expected.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //--------------------------------------------------------------------------verify css default submit button after mark first checkbox
            List<String> submitButtonDefault_actual = app.allSetPage().clickFirstCheckbox();
            Assert.assertEquals(submitButtonDefault_actual.toString(), submitButtonDefault_expected.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //--------------------------------------------------------------------------verify css active submit button after mark second checkbox
            app.allSetPage().clickSecondCheckbox();
            List<String> submitButtonDefault_actual = app.allSetPage().verifySubmitButton_default();
            Assert.assertEquals(submitButtonDefault_actual.toString(), submitButtonActive_expected.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test(priority = 7)
    public void testCreateNewAccount_submit() throws InterruptedException {
        try {
            //--------------------------------------------------------------------------verify account creation by element in calendar
            Assert.assertTrue(app.goTo().submit3());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            //--------------------------------------------------------------------------verify account creation by part of URL
            Assert.assertTrue(app.driver.getCurrentUrl().contains("https://lista.atzma.im/he/calendar"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //--------------------------------------------------------------------------verify username in MenuTest

    }


    @Test(priority = 8)
    public void testLogOut() throws Exception {
        app.goTo().calendarPage();

        try {
            //--------------------------------------------------------------------------verify logout button in menu
            Assert.assertTrue(app.calendar().logout());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Assert.assertTrue(app.goTo().verifyEmailInput());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Assert.assertTrue(app.goTo().verifyPasswordInput());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test(priority = 9)
    public void testVerifyLoginWithNewUser() throws Exception {
        app.goTo().login();
        try {
            //--------------------------------------------------------------------------verify Login with new user (name in menu)
            Assert.assertTrue(app.calendar().verifyUserinMenu().contains("random_"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 10)
    public void testVerifyDefaultClientPage() throws Exception {
        app.goTo().clientPage();
        //--------------------------------------------------------------------------verify client page URL
        Assert.assertTrue(app.driver.getCurrentUrl().matches("https://lista.atzma.im/he/clients"));

        try {
            //--------------------------------------------------------------------------verify title text ('מאגר לקוחות')
            Assert.assertTrue(app.clientList().verifyTitleText());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String numberOfClient =app.clientList().verifyNumberOfClient();

        //--------------------------------------------------------------------------verify number of client (0)
        try {
            Assert.assertEquals(numberOfClient, "(0)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
