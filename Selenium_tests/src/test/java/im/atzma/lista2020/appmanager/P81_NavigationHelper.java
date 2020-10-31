package im.atzma.lista2020.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class P81_NavigationHelper extends HelperBase {
    @FindBy(css = ".auth0-lock-error-invalid-hint")
    List<WebElement> login_error_messages_blank_invalid;
    @FindBy(css = "span.animated.fadeInUp")
    List<WebElement> login_error_messages_wrong;

    @FindBy(xpath = "//input[@id='1-email']")
    WebElement email;

    @FindBy(xpath = "//input[@type='password']")
    WebElement pass;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btn_login;

    @FindBy(xpath = "//span[text()='Policies']")
    WebElement btn_policy_in_menu;

    @FindBy(xpath = "//h3[text()='You have no policies yet']")
    List<WebElement> message_noPolicy;

    public P81_NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void goToLoginPage() throws InterruptedException {
        driver.get("https://testcompany4.perimeter81.com/sign-in");
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(driver -> driver.findElement(By.xpath("//input[@id='1-email']")));

    }

    public boolean login(String mail, String password) throws InterruptedException {
        System.out.println(password);
        fillText(email, mail);
        fillText(pass, password);
        click(btn_login);

        new WebDriverWait(driver, Duration.ofSeconds(20)).until(driver -> driver.findElement(By.xpath("//span[text() ='" + mail + "']")));
        if (driver.getCurrentUrl().equals("https://testcompany4.perimeter81.com/team/members")) {
            return true;
        } else {
            System.out.println("CurrentUrl: " + driver.getCurrentUrl());
            return false;
        }
    }

    public boolean goToPolicyPage() throws InterruptedException {
        click(btn_policy_in_menu);

        if (message_noPolicy.size() != 0) {
            click(driver.findElement(By.xpath("//h3[text()='You have no policies yet']")));
        } else {
            System.out.println("The number of policies more than one — no message");
        }

        if (driver.getCurrentUrl().equals("https://testcompany4.perimeter81.com/policies")) {
            return true;
        } else {
            System.out.println("CurrentUrl: " + driver.getCurrentUrl());
            return false;
        }
    }

    public void closePopap() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(driver -> driver.findElement(By.name("intercom-borderless-frame")));
        WebElement ifr = driver.findElement(By.name("intercom-borderless-frame"));
        driver.switchTo().frame(ifr);
        click(driver.findElement(By.cssSelector(".intercom-lr0ri6.es6hgh14 span")));
        driver.switchTo().defaultContent();
    }

    public void goToMembersPage() {
        driver.get("https://testcompany4.perimeter81.com/team/members");
    }

    public List<String> login_negative(String mail, String password) throws InterruptedException {
        List<String> errors = new ArrayList<>();
        errors.clear();
        System.out.println(password);
        fillText(email, mail);
        fillText(pass, password);
        click(btn_login);

        if (isElementPresent2(login_error_messages_blank_invalid) || isElementPresent2(login_error_messages_wrong)) {
            for (int i = 0; i < login_error_messages_blank_invalid.size(); i++) {
                errors.add(login_error_messages_blank_invalid.get(i).getText());
                highlight_blue(login_error_messages_blank_invalid.get(i));
            }
            if (isElementPresent2(login_error_messages_wrong)) {
                for (int i = 0; i < login_error_messages_wrong.size(); i++) {
                    errors.add(login_error_messages_wrong.get(i).getText());
                    highlight_blue(login_error_messages_wrong.get(i));
                }
            }
        }
        return errors;
    }


}
