package im.atzma.lista2020.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class P81_MembersActivationTest extends TestBase {

    @Test(priority = 1, alwaysRun = true)
    public void verifyMenu_Of_memberActivationTest() throws InterruptedException {
        Assert.assertTrue(app.p81_membersHelper().verifyMenuOfMember());
    }

    @Test(priority = 2, alwaysRun = true)
    public void copyLinkTest() throws InterruptedException {
        Assert.assertTrue(app.p81_membersHelper().copyLink("jon_snow@gmail.com", "ned_stark@gmail.com", "khal_drogo@gmail.com"));
    }


    @Test(priority = 3, alwaysRun = true)
    public void activateNewMemberTest() throws InterruptedException {
        Assert.assertTrue(app.p81_membersHelper().activateMember());
    }

    @Test(priority = 4, alwaysRun = true)
    public void verifyNewActiveMembersTest() throws InterruptedException {
        Assert.assertTrue(app.p81_membersHelper().verifyNew_activeMembers());
    }

    @Test(priority = 5, alwaysRun = true)
    public void verifyRolesTest() {
        Assert.assertTrue(app.p81_membersHelper().verifyRolesAfterActivation("User"));
    }

    @Test(priority = 6, alwaysRun = true)
    public void initiateChangeOfRoleTest() throws InterruptedException {
        Assert.assertTrue(app.p81_membersHelper().initiateChangeOfRole());
    }

    @Test(priority = 7, alwaysRun = true)
    public void changeMemberRoleTest() throws InterruptedException {
        Assert.assertTrue(app.p81_membersHelper().changeMemberRole("Manager"));
    }


}
