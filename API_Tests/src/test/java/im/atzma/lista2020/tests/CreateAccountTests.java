package im.atzma.lista2020.tests;

import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {

    @Test
    public void creatNewAccount() {
        app.accountCreationHelper().createAcoount();
    }
}
