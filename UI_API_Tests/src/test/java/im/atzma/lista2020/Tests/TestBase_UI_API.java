package im.atzma.lista2020.Tests;


import im.atzma.lista2020.appmanager.ApplicationRest_UI_API_Manager;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.mockserver.integration.ClientAndServer;
import org.testng.annotations.*;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;


public class TestBase_UI_API {
   // Logger logger = LoggerFactory.getLogger(TestBase_UI_API.class);
    static final ApplicationRest_UI_API_Manager app = new ApplicationRest_UI_API_Manager();

    private ClientAndServer mockServer;

    @BeforeClass
    public void startServer() {
        mockServer = startClientAndServer(1080);
    }
    @AfterClass
    public void stopServer() {
        mockServer.stop();
    }

    @BeforeMethod(alwaysRun = true)
    public void printStart() {
        System.out.println("<<<< START TEST >>>>");
        System.out.println("____________________");
    }

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        RestAssured.filters(
                ResponseLoggingFilter.responseLogger(),
                new RequestLoggingFilter());
        app.init();


    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    @AfterMethod(alwaysRun = true)
    public void printEnd() {
        System.out.println("__________________");
        System.out.println(">>>> END TEST <<<<");
    }
}
