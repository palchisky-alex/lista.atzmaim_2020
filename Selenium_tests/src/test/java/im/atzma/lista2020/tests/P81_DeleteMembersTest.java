package im.atzma.lista2020.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class P81_DeleteMembersTest extends TestBase {

    @Test(priority = 1, alwaysRun = true)
    public void initiateMemberDeletionTest() throws InterruptedException {
        Assert.assertTrue(app.p81_membersHelper().initiateMemberDeletion());
    }

    @Test(priority = 2, alwaysRun = true)
    public void deleteMembersTest() throws InterruptedException {
        Assert.assertTrue(app.p81_membersHelper().deleteMembersTest());
    }
}
