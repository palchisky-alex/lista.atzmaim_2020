package im.atzma.lista2020.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteAccountTest extends TestBase {

    @Test(priority = 1)
    public void testDeleteAccount() throws Exception {

        app.appointment().deleteAccount();
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



    @Test(priority = 2)
    public void testDeleteAccountVerification() throws Exception {

        app.goTo().login();

        String expected = "אנחנו לא מכירים את האימייל הזה והססמה";
        String actual = app.appointment().verifyAccountDeletion();

        try {
            Assert.assertEquals(actual, expected);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
