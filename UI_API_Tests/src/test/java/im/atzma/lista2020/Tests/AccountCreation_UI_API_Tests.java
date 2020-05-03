package im.atzma.lista2020.Tests;

import org.approvaltests.Approvals;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountCreation_UI_API_Tests extends TestBase {

    @Test(priority = 1)
    public void createAccount() {
        Assert.assertEquals(app.accountCreation_UI_API_Helper().createAccount(), 201);
    }

    @Test(priority = 2)
    public void verifyAccountCreation() {
        Assert.assertEquals(app.accountCreation_UI_API_Helper().verifyAccountCreation(), "/en/calendar");
    }


    @Test(priority = 3)
    public void deleteAccount() {
        Assert.assertEquals(app.accountCreation_UI_API_Helper().deleteAccount(), 401);
    }

    @Test(priority = 4)
    public void verifyAccountDeletion() {
        Assert.assertEquals(app.accountCreation_UI_API_Helper().verifyAccountDeletion(), "/he/login");
    }


}