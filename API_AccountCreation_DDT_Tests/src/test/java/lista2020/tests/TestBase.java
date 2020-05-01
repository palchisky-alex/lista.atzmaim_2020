package lista2020.tests;

import lista2020.appmanager.ApplicationRestDDTManager;
import org.testng.annotations.*;


public class TestBase {
    //Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected static final ApplicationRestDDTManager app = new ApplicationRestDDTManager();

    @BeforeMethod (alwaysRun = true)
    public void printStart() {

        System.out.println("================================ START TEST ===================================");
    }

    @BeforeSuite (alwaysRun = true)
    public void setUp() {
      app.init();

    }

    @AfterMethod(alwaysRun = true)
    public void printEnd()  {

        System.out.println("================================ END TEST ===================================+++");
    }

}
