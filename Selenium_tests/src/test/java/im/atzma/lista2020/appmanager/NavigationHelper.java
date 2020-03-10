package im.atzma.lista2020.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.Random;

public class NavigationHelper extends HelperBase {
    @FindBy(xpath = "//input[@type='email']")
    WebElement input_email;

    @FindBy(xpath = "//input[@type='password']")
    WebElement input_password;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btn_submit;

    @FindBy(xpath = "//div[@class='more_wrap']")
    WebElement menu_gamburger;

    @FindBy(xpath = "//button[text()='להיכנס']")
    WebElement btn_login_to_account;

    @FindBy(xpath = "//a[@href='/he/login']")
    WebElement btn_login;


    Random random = new Random();
    long randomLong = random.nextLong();

    public final String mail = "katalon_" + randomLong + "@gmail.com";

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }


    public void homePage() throws InterruptedException, IOException {
        driver.get(propertiesList("web.BaseUrl"));
        waitForLocation(propertiesList("web.BaseUrl"));
    }

    public void goToLoginPage() throws InterruptedException, IOException {
        driver.get(propertiesList("web.loginURL"));
    }

    public void singupPage() throws InterruptedException, IOException {
        driver.findElement(By.xpath("//a[@href='/he/signup?utm_source=autotest-selenium']")).click();
        waitForLocation(propertiesList("web.singupURL"));
    }

    public void clientPage() throws InterruptedException, IOException {
        driver.get(propertiesList("web.clientURL"));
        waitForLocation(propertiesList("web.clientURL"));
    }

    public void servicesPage() throws InterruptedException, IOException {
        driver.get(propertiesList("web.servicesURL"));
        waitForLocation(propertiesList("web.servicesURL"));
    }

    public void calendarPage() throws InterruptedException, IOException {
        driver.get(propertiesList("web.calendarURL"));
//        waitForLocation(calendarURL);
    }

    public void typeNewPassAndUser() throws InterruptedException, IOException {
        String userName = propertiesList("web.adminLogin");
        String pass = propertiesList("web.adminPassword");

        waitForElement(input_email);
        switch (userName) {
            case "random_":
                fillText(input_email,   userName + randomLong + "@gmail.com");
                break;
            case "katalon":
                fillText(input_email,  "katalon13@gmail.com");
                break;
        }

        fillText(input_password, pass);

        System.out.println("email: " + userName);
        System.out.println("password: " + pass);
    }

    public boolean submit() throws InterruptedException, IOException {
        String businessURL = propertiesList("web.businessURL");

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        waitForLocation(businessURL);
        if (businessURL.equals(driver.getCurrentUrl())) {
            return true;
        } else return false;
    }

    public void submit2() throws InterruptedException, IOException {
        driver.findElement(By.xpath("//span[text()='בואו נתחיל!']/..")).click();
        waitForLocation(propertiesList("web.allsetURL"));
    }

    public boolean submit3() throws InterruptedException {
        driver.findElement(By.xpath("//button")).click(); //-------------click on last submit button and creat account
        waitForElement(menu_gamburger); //------------- wait for menu of calendar and confirm account creation
        highlight(menu_gamburger);
        if (menu_gamburger.isDisplayed()) {
            return true;
        } else return false;
    }

    public void login() throws InterruptedException, IOException {
        homePage();
        highlight(btn_login);
        click(btn_login);
        waitForElement(input_email);
        typeNewPassAndUser();
        click(btn_submit);
    }


    public boolean verifyEmailInput() {
        if (input_email.isDisplayed()) {
            highlight(input_email);
            return true;
        } else {
            return false;
        }
    }


    public boolean verifyPasswordInput() {
        if (input_password.isDisplayed()) {
            highlight(input_password);
            return true;
        } else {
            return false;
        }
    }

}
