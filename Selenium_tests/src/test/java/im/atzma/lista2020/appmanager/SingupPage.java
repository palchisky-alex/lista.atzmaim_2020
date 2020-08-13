package im.atzma.lista2020.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.io.IOException;
import java.util.Random;

public class SingupPage extends HelperBase {

    public SingupPage(WebDriver driver) { super(driver); }

    public String  getCurrentURL(String url) throws InterruptedException {
        String currentURL = driver.getCurrentUrl();
        if (!currentURL.equals(url)) {
            Thread.sleep(10000);
        }
        return driver.getCurrentUrl();
    }

    public String getURL(String url) throws IOException, InterruptedException { return propertiesList(url); }

    public void pressOnLoginButton() {
        click(driver.findElement(By.cssSelector(".login-btn")));  // FOR DESKTOP
//        click(driver.findElement(By.cssSelector(".header_login_link")));   //FOR SELL
    }

    public void pressOnSingupButton() {
        click(driver.findElement(By.cssSelector(".sign_label")));
    }

    public void pressOnSkipHereButton() {
        click(driver.findElement(By.cssSelector(".choose-menu__button")));
    }

    public void pressOnIAgreeCheckbox() { click(driver.findElement(By.id("twice"))); }

    public void pressOnDoneButton() { click(driver.findElement(By.cssSelector(".all-set-form__button"))); }

    public void pressOnNextButton() throws InterruptedException { click(driver.findElement(By.cssSelector(".next-step"))); }

    public void fillAccountData() throws InterruptedException {
        Random random = new Random();
        int randomInt = random.nextInt();
        String random_for_mail = "selenium_" + randomInt + "@gmail.com";
        fillText(driver.findElement(By.name("email")), random_for_mail);
        fillText(driver.findElement(By.name("new-password")), "qwer");
        click(driver.findElement(By.cssSelector(".login-button")));

    }
    public boolean verifyButton() {
        if(isElementVisible(driver.findElement(By.cssSelector(".more_wrap")))) {
            return true;
        }
        else
            return false;

    }
}
