package im.atzma.lista2020.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class ApplicationManager {
    private final String browser;
    public RemoteWebDriver driver;
    Properties properties;

    NavigationHelper navigationHelper;
    SingupPage singupPage;
    BusinessPage businessPage;
    AllSetPage allSetPage;
    CalendarPage calendarPage;
    ClientListPage clientPage;
    ServicesHelper servicesHelper;
    ClientHelper clientHelper;
    AppointmentHelper appointmentHelper;


    public StringBuffer verificationErrors = new StringBuffer();
    public boolean acceptNextAlert = true;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws InterruptedException, IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("81.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

         driver = new RemoteWebDriver(
                URI.create("http://67.205.150.243:4444/wd/hub").toURL(),
                capabilities
        );


        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        if (browser.equals("Chrome2")) {
            System.setProperty("webdriver.chrome.driver", "C:\\automation\\browser drivers\\chromedriver_83_win32\\chromedriver.exe");
//            ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
//            chromeOptions.addArguments(("--auto-open-devtools-for-tabs"));
//            chromeOptions.addArguments("--ignore-certificate-errors");
//            driver = new ChromeDriver(chromeOptions);
        } else if (browser.equals("Firefox2")) {
            System.setProperty("webdriver.gecko.driver", "C:\\automation\\browser drivers\\firefox\\geckodriver.exe");
            driver = new FirefoxDriver();
        }



        navigationHelper = new NavigationHelper(driver);
        singupPage = new SingupPage(driver);
        businessPage = new BusinessPage(driver);
        allSetPage = new AllSetPage(driver);
        calendarPage = new CalendarPage(driver);
        clientPage = new ClientListPage(driver);
        servicesHelper = new ServicesHelper(driver);
        clientHelper = new ClientHelper(driver);
        appointmentHelper = new AppointmentHelper(driver);
    }


    public void stop() {
        driver.close();
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
       // Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public SingupPage singupPage() {
        return singupPage;
    }

    public BusinessPage businessPage() {
        return businessPage;
    }

    public AllSetPage allSetPage() {
        return allSetPage;
    }

    public CalendarPage calendar() {
        return calendarPage;
    }

    public ClientListPage clientList() {
        return clientPage;
    }

    public ServicesHelper service() {
        return servicesHelper;
    }

    public ClientHelper client() {
        return clientHelper;
    }

    public AppointmentHelper appointment() {
        return appointmentHelper;
    }
}
