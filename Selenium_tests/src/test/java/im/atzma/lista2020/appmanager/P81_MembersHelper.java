package im.atzma.lista2020.appmanager;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P81_MembersHelper extends HelperBase {
    @FindBy(xpath = "(//button)[1]")
    WebElement btn_inviteMember;

    @FindBy(xpath = "//input[@type='text']")
    List<WebElement> input_addMemberField;
    @FindBy(xpath = "//input[@type='text']")
    WebElement field_addMemberField;

    @FindBy(xpath = "//button[@disabled]")
    List<WebElement> btn_disabled_addMember;
    @FindBy(css = "div.Footer_base__nT6Qr button:nth-child(1)")
    WebElement btn_enable_addMember;

    @FindBy(xpath = "//div[@class='Popup_popup__3vosY']")
    List<WebElement> popup_addMemebrs;

    @FindBy(xpath = "//textarea")
    List<WebElement> popup_textarea;

    @FindBy(css = ".EntityIcon_small__2z6d9.Entity_icon__3YhQi ")
    List<WebElement> icons_of_unactivated_members;

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    List<String> mails;

    public P81_MembersHelper(WebDriver driver) {
        super(driver);

    }


    public boolean verifyInvitationButton() throws InterruptedException {

        boolean verify_inviteMemberButton = false;
        Boolean isEditable = btn_inviteMember.isEnabled() && btn_inviteMember.getAttribute("readonly") == null;
        if (isEditable) {
            verify_inviteMemberButton = true;
            try {
                click(btn_inviteMember);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            verify_inviteMemberButton = false;
        }
        return verify_inviteMemberButton;
    }

    public boolean verifyPopup() throws InterruptedException {
        Boolean verifyPopup = false;
        if (driver.getCurrentUrl().equals("https://testcompany4.perimeter81.com/team/members/invite")) {
            if (isElementPresent2(popup_addMemebrs) && isElementPresent2(input_addMemberField)
                    && isElementPresent2(btn_disabled_addMember) && isElementPresent2(popup_textarea)) {
                System.out.println("URL and Popup present: ");
                verifyPopup = true;
            } else {
                System.out.println("popup not present");
                verifyPopup = false;
            }
        } else {
            System.out.println("URL or Popup not present");
            verifyPopup = false;
        }
        return verifyPopup;
    }

    public boolean addMail(String m1, String m2, String m3) throws InterruptedException {
        Boolean popup = false;
        mails = Arrays.asList(m1, m2, m3);

        for (int i = 0; i < mails.size(); i++) {
            field_addMemberField.sendKeys(mails.get(i));
            field_addMemberField.sendKeys(Keys.SPACE);
            Thread.sleep(300);
        }
        addTextToInvitationTextarea();
        if (!isElementPresent2(btn_disabled_addMember)) {
            click(btn_enable_addMember);

            wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div.Footer_base__nT6Qr button:nth-child(1)"))));

            if (!isElementPresent2(popup_addMemebrs)) {

                if (driver.getCurrentUrl().equals("https://testcompany4.perimeter81.com/team/members")) {
                    popup = true;
                } else {
                    popup = false;
                }
            }
        } else {
            popup = false;
        }
        return popup;
    }

    public void addTextToInvitationTextarea() throws InterruptedException {
        String heb = "הנה ההזמנה שלך להצטרף אלי ל- פרימטר-81. השירות מאבטח את החיבור";
        String eng = "Here is your invite to join me on perimeter81.";
        String rus = "Приглашение на присоединение ко мне в Perimeter81";
        for (int i = 0; i < popup_textarea.size(); i++) {
            popup_textarea.get(i).sendKeys(Keys.chord(Keys.CONTROL, "a"));
            popup_textarea.get(i).sendKeys(Keys.DELETE);

            popup_textarea.get(i).sendKeys(heb);
            popup_textarea.get(i).sendKeys(Keys.ENTER);
            popup_textarea.get(i).sendKeys(eng);
            popup_textarea.get(i).sendKeys(Keys.ENTER);
            popup_textarea.get(i).sendKeys(rus);

        }
    }

    public boolean verifyNewInvitationsInList() {
        Boolean invitationsInList = false;
        if (icons_of_unactivated_members.size() == mails.size()) {
            for (int i = 0; i < mails.size(); i++) {
                highlight(driver.findElement(By.xpath("//span[text()='" + mails.get(i) + "']")));
            }
            invitationsInList = true;
        } else {
            invitationsInList = false;
        }
        return invitationsInList;
    }
}
