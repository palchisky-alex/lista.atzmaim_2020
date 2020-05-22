package im.atzma.lista2020.Tests;
import im.atzma.lista2020.appmanager.LogListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import static org.testng.Assert.assertEquals;
@Listeners(LogListener.class)
public class AccountCreation_UI_API_Tests extends TestBase_UI_API {

    @Test(priority = 1)
    public void createAccount() throws FileNotFoundException {
        app.accountCreation_UI_API_Helper().createAccount().then().assertThat().statusCode(201);
    }


    @Test(priority = 2)
    public void verifyAccountCreation() {
        assertEquals(app.accountCreation_UI_API_Helper().verifyAccountCreation(), "/en/calendar");
    }


    @Test(priority = 3)
    public void deleteAccount() {
        app.accountCreation_UI_API_Helper().deleteAccount().then().assertThat().statusCode(401);
    }



    @Test(priority = 4)
    public void verifyAccountDeletion() {
        assertEquals(app.accountCreation_UI_API_Helper().verifyAccountDeletion(), "/he/login");
    }


}