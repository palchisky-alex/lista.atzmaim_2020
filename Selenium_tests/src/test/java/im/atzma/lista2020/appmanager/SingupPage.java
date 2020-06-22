package im.atzma.lista2020.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SingupPage extends HelperBase {
    public SingupPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//li[text()='ru']")
    WebElement lang_switch_ru;

    @FindBy(xpath = "//li[text()='en']")
    WebElement lang_switch_en;

    @FindBy(xpath = "//li[text()='he']")
    WebElement lang_switch_he;

    @FindBy(xpath = "//img[@src='/public/signup/media/logo.svg']")
    WebElement atzmaim_logo;

    @FindBy(xpath = "//div[text()='מלא פרטים כדי לצור חשבון:']")
    WebElement page_text;

    @FindBy(xpath = "//input[@placeholder='הזן את האימיייל שלך']")
    WebElement email_placeholderText;

    @FindBy(xpath = "//input[@placeholder='הזן את הססמא שלך']")
    WebElement password_placeholderText;

    @FindBy(xpath = "//button[text()='המשך']")
    WebElement submit_btnText;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submit_btn;

    @FindBy(xpath = "//img[@src='/public/signup/media/mail.svg']")
    WebElement email_icon;

    @FindBy(xpath = "//img[@src='/public/signup/media/lock.svg']")
    WebElement lock_icon;

    @FindBy(xpath = "//img[@src='/public/signup/media/eye.svg']")
    WebElement pass_eye_icon;

    @FindBy(xpath = "//img[@src='/public/signup/media/eye-off.svg']")
    WebElement pass_eye_icon_off;

    @FindBy(xpath = "//div[@class='group password ']//input[@type='text']")
    WebElement visiblePassword;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btn_submit;

    public List<WebElement> getSignupPageElements() throws InterruptedException {
        List<WebElement> itemList = new ArrayList<>();
        itemList.add(lang_switch_ru);
        itemList.add(lang_switch_en);
        itemList.add(lang_switch_he);
        itemList.add(atzmaim_logo);
        itemList.add(page_text);
        itemList.add(email_placeholderText);
        itemList.add(password_placeholderText);
        itemList.add(submit_btnText);
        itemList.add(submit_btn);
        itemList.add(email_icon);
        itemList.add(lock_icon);

        for (int i = 0; i < itemList.size(); i++) {
            highlight(itemList.get(i));
        }
        return itemList;
    }

    public boolean eye_icon() {
        click(pass_eye_icon_off);
        if (pass_eye_icon.isDisplayed()) {
            highlight(pass_eye_icon);
            return true;
        } else {
            return false;
        }
    }

    public boolean eye_icon_off() {
        if (pass_eye_icon_off.isDisplayed()) {
            highlight(pass_eye_icon_off);
            return true;
        } else {
            return false;
        }
    }

    public boolean passwordVisible() {
        click(pass_eye_icon);
        if (visiblePassword.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

}
