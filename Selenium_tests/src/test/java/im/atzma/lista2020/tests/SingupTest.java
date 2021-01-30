package im.atzma.lista2020.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SingupTest extends TestBase {
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Test(priority = 1)
    public void testLoginPage() throws Exception {
        String myURL = app.singupPage().getURL("web.loginURL");
        app.goTo().homePage();
        app.singupPage().pressOnLoginButton();
        Assert.assertEquals(app.singupPage().getCurrentURL(myURL), myURL);
    }

    @Test(priority = 2)
    public void testSingupPage() throws Exception {
        String myURL = app.singupPage().getURL("web.singupURL");
        app.singupPage().pressOnSingupButton();
        Assert.assertEquals(app.singupPage().getCurrentURL(myURL), myURL);
    }

    @Test(priority = 3)
    public void testBusinessTypePage() throws Exception {
        String myURL = app.singupPage().getURL("web.businessURL");
        app.singupPage().fillAccountData();
        Assert.assertEquals(app.singupPage().getCurrentURL(myURL), myURL);
    }

    @Test(priority = 4)
    public void testAllSetPage() throws Exception {
        String myURL = app.singupPage().getURL("web.allsetURL");
        app.singupPage().pressOnSkipHereButton();
        Assert.assertEquals(app.singupPage().getCurrentURL(myURL), myURL);
    }

    @Test(priority = 5)
    public void testOnboardingPage() throws Exception {
        String myURL = app.singupPage().getURL("web.onboardingURL");
        app.singupPage().pressOnIAgreeCheckbox();
        app.singupPage().pressOnDoneButton();
        Assert.assertEquals(app.singupPage().getCurrentURL(myURL), myURL);
    }

    @Test(priority = 6)
    public void testOnboarding1Page() throws Exception {
        String myURL = app.singupPage().getURL("web.onboarding1URL");
        app.singupPage().pressOnNextButton();
        Assert.assertEquals(app.singupPage().getCurrentURL(myURL), myURL);
    }

    @Test(priority = 7)
    public void testOnboarding2Page() throws Exception {
        String myURL = app.singupPage().getURL("web.onboarding2URL");
        app.singupPage().pressOnNextButton();
        Assert.assertEquals(app.singupPage().getCurrentURL(myURL), myURL);
    }

    @Test(priority = 8)
    public void testCalendarPage() throws Exception {
        String myURL = app.singupPage().getURL("web.calendarURL");
        app.singupPage().pressOnNextButton();
        Assert.assertTrue(app.singupPage().verifyButton());
    }

    @Test(priority = 9)
    public void testPopupInstallation() throws Exception {
        app.goTo().calendarPage();
    }

}
