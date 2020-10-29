package im.atzma.lista2020.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class P81_MembersTest extends TestBase {

    @Test
    public void initiateMemberInviting() throws InterruptedException {
        Assert.assertTrue(app.p81_membersHelper().verifyInvitationButton());
    }

    @Test
    public void verifyPopupItems() throws InterruptedException {
        Assert.assertTrue(app.p81_membersHelper().verifyPopup());
    }

    @Test
    public void addNewMailTest() throws InterruptedException {

        Assert.assertTrue(app.p81_membersHelper().addMail("jon_snow@gmail.com", "ned_stark@gmail.com", "khal_drogo@gmail.com"));
    }

    @Test
    public void verifyInvitationsTest() {
        Assert.assertTrue(app.p81_membersHelper().verifyNewInvitationsInList());
    }

    @Test
    public void verifyRolesTest() {
        Assert.assertTrue(app.p81_membersHelper().verifyRoles("User"));
    }
}
