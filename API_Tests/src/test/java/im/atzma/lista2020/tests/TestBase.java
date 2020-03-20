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
    public void setUp() throws Exception {
      app.init();


    }

  //  @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.init();
    }

//    @BeforeMethod()
//    public void logTestStart(Method method, Object[] p) throws IOException {
//        logger.info("Start test " + method.getName() + " with parrametrs " + Arrays.asList(p));
//    }
//
//    @AfterMethod(alwaysRun = true)
//
//    public void logTestStop(Method method) {
//        logger.info("Stop test " + method.getName());
//    }

}
