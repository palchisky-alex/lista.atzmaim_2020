package lista2020.tests;

import org.approvaltests.Approvals;
import org.testng.annotations.Test;

public class AccountCreationTests extends TestBase {

    @Test(priority = 1)
    public void getBusinessTypeJSON() {

        Approvals.verify(app.accountCreationHelper().businessTypeResponse());

    }

    @Test(priority = 2)
    public void createAccount() {
        app.accountCreationHelper().createAccount();
    }

    @Test(priority = 3)
    public void deleteAccount() {
        app.accountCreationHelper().deleteAccount();
    }
}
