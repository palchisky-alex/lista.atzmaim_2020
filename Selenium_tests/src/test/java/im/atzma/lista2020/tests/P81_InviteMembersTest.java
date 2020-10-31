package im.atzma.lista2020.tests;

import im.atzma.lista2020.model.ClientData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class P81_InviteMembersTest extends TestBase {

    @Test(priority = 1, alwaysRun = true)
    public void initiateMemberInviting() throws InterruptedException {
        int count = app.p81_membersHelper().countLicenses();
        if(count < 8) {
            app.p81_membersHelper().deleteMembersTest();
        }
        else { System.out.println("No members in login");}

        Assert.assertTrue(app.p81_membersHelper().verifyInvitationButton());
    }

    @Test(priority = 2, alwaysRun = true)
    public void verifyPopupItems() throws InterruptedException {
        Assert.assertTrue(app.p81_membersHelper().verifyPopup());
    }

    @Test(priority = 3, alwaysRun = true)
    public void addNewMailTest() throws InterruptedException {
        Assert.assertTrue(app.p81_membersHelper().addMail("jon_snow@gmail.com", "ned_stark@gmail.com", "khal_drogo@gmail.com"));
    }

    @Test(priority = 4, alwaysRun = true)
    public void verifyInvitationsTest() {
        Assert.assertTrue(app.p81_membersHelper().verifyNewInvitationsInList());
    }

    @Test(priority = 5, alwaysRun = true)
    public void verifyRolesTest() {
        Assert.assertTrue(app.p81_membersHelper().verifyRoles("User"));
    }
}
