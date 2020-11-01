package im.atzma.lista2020.tests;

import org.testng.annotations.Test;

public class P81_InviteMembersTest_API extends TestBase {

    @Test(priority = 1, alwaysRun = true)
    public void memberInviting_API() throws InterruptedException {
        app.p81_api_helper().getMembers_API();
    }

}
