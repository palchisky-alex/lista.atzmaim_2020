package im.atzma.lista2020.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class P81_Login extends TestBase{

    @Test(priority = 1, alwaysRun = true)
    public void login() throws InterruptedException {
        app.P81_goTo().goToLoginPage();
        app.P81_goTo().closePopap();
        int count = app.p81_membersHelper().countLicenses();
        if(count < 8) {
            app.p81_membersHelper().deleteMembersTest();
        }
        else { System.out.println("No members in login");}
        Assert.assertTrue(app.P81_goTo().login("alex.palchisky@gmail.com", "!z8Jk4KpT9cCtd3"));
    }

}
