package im.atzma.lista2020.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class HelperBase {
    WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    Properties properties;


    public void highlight(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid red'", el);
    }

    public void click(WebElement el) {
//        wait.until(ExpectedConditions.elementToBeClickable(el));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
        highlight(el);


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        el.click();
    }

    public void clickJS(WebElement el) {
//        ((JavascriptExecutor) driver).executeScript("arguments[0].remove()')", el);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style','visibility: hidden')", el);
//        ((JavascriptExecutor) driver).executeScript("document.querySelector('.fc-slats tr:nth-child(1) > .fc-widget-content:nth-child(1).click");

    }

    public void clickJScript(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", el);
    }


    public void select(WebElement el, String myvalue) {
        new Select(el).selectByValue(myvalue);
        try {
            highlight(el);
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }


    public void fillText(WebElement el, String text) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
        highlight(el);
        if (text != null) {
            String existingText = el.getAttribute("value");
            if (!text.equals(existingText)) {
                Thread.sleep(200);
                el.click();
                el.clear();
                el.sendKeys(text);
            }
        }
    }

    public void attach(WebElement el, File file) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
        highlight(el);

        el.sendKeys(file.getAbsolutePath());
//        if (file != null) {
//        }
    }

//    public void fillText(WebElement el, String text) throws InterruptedException {
//        el.sendKeys(text);
//    }

    public void waitForLocation(String myURL) throws InterruptedException {

//        (new WebDriverWait(driver, 100)).until(new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver d) {
//                return d.getCurrentUrl().equals(myURL);
//            }
//        });
    }

    public void waitForElement(WebElement myElement) throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= 100) fail("timeout");
            if (myElement.isDisplayed()) break;
        }
    }

    public void waitForElements(List<WebElement> myElement) throws InterruptedException {
        Thread.sleep(3000);
    }

    public boolean isElementPresent2(List<WebElement> el) throws InterruptedException {
        if (el.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean isElementPresent(WebElement el) throws InterruptedException {
        System.out.println("Element: " + el);
        List<WebElement> itemlist = new ArrayList<>();
        itemlist.clear();
        itemlist.add(el);
        if (itemlist.size() > 0) {
            return true;
        } else System.out.println("Element not present");

        return false;
    }

    public boolean isElementVisible(WebElement el) {
        if ( el.getSize().getHeight() != 0) {
            System.out.println(el.getSize().getHeight());
            return true;
        } else
            return false;
    }


    public String propertiesList(String key) throws IOException, InterruptedException {
        ZoneId zid = ZoneId.of("Asia/Jerusalem");
        LocalDate local_time = LocalDate.now(zid);
        System.out.println("Localtime: " + local_time);
        String worker = "?worker_id=1&calendar_view_type=daily";

        properties = new Properties();
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        if (key.equals("web.BaseUrl")) {
            return properties.getProperty("web.BaseUrl");
        } else if (key.equals("web.singupURL")) {
            return properties.getProperty("web.singupURL");
        } else if (key.equals("web.businessURL")) {
            return properties.getProperty("web.businessURL");
        } else if (key.equals("web.allsetURL")) {
            return properties.getProperty("web.allsetURL");
        } else if (key.equals("web.onboardingURL")) {
            return properties.getProperty("web.onboardingURL");
        } else if (key.equals("web.onboarding1URL")) {
            return properties.getProperty("web.onboarding1URL");
        } else if (key.equals("web.onboarding2URL")) {
            return properties.getProperty("web.onboarding2URL");
        } else if (key.equals("web.loginURL")) {
            return properties.getProperty("web.loginURL");
        } else if (key.equals("web.clientURL")) {
            return properties.getProperty("web.clientURL");
        } else if (key.equals("web.servicesURL")) {
            return properties.getProperty("web.servicesURL");
        } else if (key.equals("web.calendarURL")) {
            return properties.getProperty("web.calendarURL") + local_time + worker;
        } else if (key.equals("web.newClientFormURL")) {
            return properties.getProperty("web.newClientFormURL");
        } else if (key.equals("web.adminLogin")) {
            return properties.getProperty("web.adminLogin");
        } else if (key.equals("web.adminPassword")) {
            return properties.getProperty("web.adminPassword");
        } else if (key.equals("web.settings")) {
            return properties.getProperty("web.settings");
        }

        return null;
    }
}
