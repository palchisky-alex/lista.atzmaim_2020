package im.atzma.lista2020.tests;

import im.atzma.lista2020.appmanager.ApplicationRestManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;


public class TestBase {
    //Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected static final ApplicationRestManager app = new ApplicationRestManager();

    @BeforeMethod (alwaysRun = true)
    public void printStart() {

        System.out.println("================================ START TEST ===================================");
    }

    @BeforeSuite (alwaysRun = true)
    public void setUp() throws Exception {
      app.init();


    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    @AfterMethod (alwaysRun = true)
    public void printEnd() {

        System.out.println("================================ END TEST ===================================");
    }
}
