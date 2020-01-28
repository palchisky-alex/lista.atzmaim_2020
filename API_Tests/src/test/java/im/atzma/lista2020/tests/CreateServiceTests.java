package im.atzma.lista2020.tests;

import org.testng.annotations.Test;

public class CreateServiceTests extends TestBase{

    @Test
    public void creatNewService() {
        app.serviceCreationHelper().createService();
    }
}
