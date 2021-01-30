package im.atzma.lista2020.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class P81_Login_negative extends TestBase{

    @Test(priority = 1, alwaysRun = true)
    public void login_negative_01() throws InterruptedException {
        List<String> my_errors = Arrays.asList("Can't be blank");

        app.P81_goTo().goToLoginPage();
        app.P81_goTo().closePopap();
        List<String> login_error = app.P81_goTo().login_negative("alex.palchisky@gmail.com", "");

        Assert.assertEquals(login_error, my_errors);
    }

    @Test(priority = 2, alwaysRun = true)
    public void login_negative_02() throws InterruptedException {
        List<String> my_errors = Arrays.asList("Can't be blank");

        app.P81_goTo().goToLoginPage();
        List<String> login_error = app.P81_goTo().login_negative("abcd@gmail.com", "");

        Assert.assertEquals(login_error, my_errors);
    }


    @Test(priority = 3, alwaysRun = true)
    public void login_negative_03() throws InterruptedException {
        List<String> my_errors = Arrays.asList("WRONG EMAIL OR PASSWORD.");

        app.P81_goTo().goToLoginPage();
        List<String> login_error = app.P81_goTo().login_negative("alex.palchisky@gmail.com", " !z8Jk4KpT9cCtd3");

        Assert.assertEquals(login_error, my_errors);
    }

    @Test(priority = 4, alwaysRun = true)
    public void login_negative_04() throws InterruptedException {
        List<String> my_errors = Arrays.asList("WRONG EMAIL OR PASSWORD.");
        Random random = new Random();
        int randomInt = random.nextInt();
        app.P81_goTo().goToLoginPage();
        List<String> login_error = app.P81_goTo().login_negative("ab'"+randomInt+"'cd@gmail.com", "!z8Jk4KpT9cCtd3");

        Assert.assertEquals(login_error, my_errors);
    }

    @Test(priority = 5, alwaysRun = true)
    public void login_negative_05() throws InterruptedException {
        List<String> my_errors = Arrays.asList("Can't be blank","Can't be blank");

        app.P81_goTo().goToLoginPage();
        List<String> login_error = app.P81_goTo().login_negative("", "");

        Assert.assertEquals(login_error, my_errors);
    }

    @Test(priority = 6, alwaysRun = true)
    public void login_negative_06() throws InterruptedException {
        List<String> my_errors = Arrays.asList("Invalid");

        app.P81_goTo().goToLoginPage();
        List<String> login_error = app.P81_goTo().login_negative("!z8Jk4KpT9cCtd3", "alex.palchisky@gmail.com");

        Assert.assertEquals(login_error, my_errors);
    }

    @Test(priority = 7, alwaysRun = true)
    public void login_negative_07() throws InterruptedException {
        List<String> my_errors = Arrays.asList("Invalid");

        app.P81_goTo().goToLoginPage();
        List<String> login_error = app.P81_goTo().login_negative("z8Jk4KpT9cCtd3", "palchisky@gmail.com");

        Assert.assertEquals(login_error, my_errors);
    }

    @Test(priority = 8, alwaysRun = true)
    public void login_negative_08() throws InterruptedException {
        List<String> my_errors = Arrays.asList("Can't be blank");

        app.P81_goTo().goToLoginPage();
        List<String> login_error = app.P81_goTo().login_negative("", "!z8Jk4KpT9cCtd3");

        Assert.assertEquals(login_error, my_errors);
    }
}
