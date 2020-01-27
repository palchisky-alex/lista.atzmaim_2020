package im.atzma.lista2020.appmanager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.testng.Assert.fail;

public class HelperBase {
    WebDriver driver;
    public  WebDriverWait wait;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void highlight(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid red'", el);
    }


    public void click(WebElement el) {
//        wait.until(ExpectedConditions.elementToBeClickable(el));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        el.click();
    }

    public void clickJS(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
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
                Thread.sleep(500);
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
        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (myURL.equals(driver.getCurrentUrl())) break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }
    }

    public void waitForElement(WebElement myElement) throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (myElement.isDisplayed()) break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }

    }


    public boolean isElementPresent(WebElement el) {

        List<WebElement> itemlist = new ArrayList<>();
        itemlist.add(el);
        if (itemlist.size() > 0) {
            return true;
        }
        return false;
    }

    public String propertiesList(String key) throws IOException {

        Properties properties = new Properties();
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        if (key.equals("web.BaseUrl"))
        { return properties.getProperty("web.BaseUrl");
        } else if (key.equals("web.singupURL")) { return properties.getProperty("web.singupURL");
        } else if (key.equals("web.businessURL")) { return properties.getProperty("web.businessURL");
        } else if (key.equals("web.allsetURL")) { return properties.getProperty("web.allsetURL");
        } else if (key.equals("web.loginURL")) { return properties.getProperty("web.loginURL");
        } else if (key.equals("web.clientURL")) { return properties.getProperty("web.clientURL");
        } else if (key.equals("web.servicesURL")) { return properties.getProperty("web.servicesURL");
        } else if (key.equals("web.calendarURL")) { return properties.getProperty("web.calendarURL");
        } else if (key.equals("web.newClientFormURL")) { return properties.getProperty("web.newClientFormURL");
        } else if (key.equals("web.adminLogin")) { return properties.getProperty("web.adminLogin");
        } else if (key.equals("web.adminPassword")) { return properties.getProperty("web.adminPassword");
        } else if (key.equals("web.settings")) { return properties.getProperty("web.settings");
        }

        return null;
    }
}
