package lista2020.tests;
import lista2020.appmanager.ApplicationRestManager;
import org.testng.annotations.BeforeSuite;


public class TestBase {
    //Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected static final ApplicationRestManager app = new ApplicationRestManager();


    @BeforeSuite (alwaysRun = true)
    public void setUp() {
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
