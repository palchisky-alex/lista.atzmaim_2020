package im.atzma.lista2020.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class P81_MembersActivationTest extends TestBase {

    @Test
    public void verifyMenu_Of_memberActivationTest() throws InterruptedException {
        Assert.assertTrue(app.p81_membersHelper().verifyMenuOfMember());
    }

    @Test
    public void copyLinkTest() throws InterruptedException {
        Assert.assertTrue(app.p81_membersHelper().copyLink());
    }


    @Test
    public void activateNewMemberTest() throws InterruptedException {
        Assert.assertTrue(app.p81_membersHelper().activateMember());
    }

    @Test
    public void verifyNewActiveMembersTest() throws InterruptedException {
        Assert.assertTrue(app.p81_membersHelper().verifyNew_activeMembers());
    }

}
