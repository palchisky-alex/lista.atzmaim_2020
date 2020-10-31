package im.atzma.lista2020.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P81_MembersHelper extends HelperBase {
    @FindBy(xpath = "(//button)[1]")
    WebElement btn_inviteMember;
    @FindBy(css = "div.Footer_base__nT6Qr button:nth-child(1)")
    WebElement btn_delete_member;
    @FindBy(css = "div.Footer_base__nT6Qr button:nth-child(2)")
    WebElement btn_cancel_delete_member;
    @FindBy(css = "div.Footer_buttons__1Bvuj button")
    WebElement btn_copy_link_done;

    @FindBy(xpath = "//input[@type='text']")
    List<WebElement> input_addMemberField;
    @FindBy(xpath = "//input[@type='text']")
    WebElement field_addMemberField;

    @FindBy(xpath = "//button[@disabled]")
    List<WebElement> btn_disabled_addMember;
    @FindBy(css = "div.Footer_base__nT6Qr button:nth-child(1)")
    WebElement btn_enable_addMember;

    @FindBy(css = "div.Footer_base__nT6Qr button:nth-child(3)")
    WebElement btn_apply_role_change;

    @FindBy(xpath = "//div[@class='Popup_popup__3vosY']")
    List<WebElement> popup_addMemebrs;

    @FindBy(xpath = "//textarea")
    List<WebElement> popup_textarea;

    @FindBy(css = ".EntityIcon_small__2z6d9.Entity_icon__3YhQi")
    List<WebElement> icons_of_unactivated_members;
    @FindBy(xpath = "//figure")
    List<WebElement> icons_of_activated_members;

    @FindBy(xpath = "//div[contains(@class, 'EntityIcon_small__2z6d9') and contains(@class,'Entity_icon__3YhQi')]/../../../td[6]/button")
    List<WebElement> btn_menu_unavtive_member;
    @FindBy(xpath = "//tbody/tr[1]/td[6]/button")
    List<WebElement> btn_menu_acvtive_member;
    @FindBy(css = "#dropdown-tooltip li")
    List<WebElement> menu_member_items;
    @FindBy(xpath = "//li/button[text()='Change role']")
    WebElement btn_change_role;

    @FindBy(css = "div.Popup_popup__3vosY")
    WebElement popup_with_link;
    @FindBy(css = "div.Popup_popup__3vosY .Input_container__2DhZL input")
    WebElement link;

    @FindBy(xpath = "//input[@name='firstName']")
    WebElement input_firstName;
    @FindBy(xpath = "//input[@name='lastName']")
    WebElement input_lastName;
    @FindBy(xpath = "//input[@name='password']")
    WebElement input_password;

    @FindBy(xpath = "//button[@disabled]")
    List<WebElement> btn_disabled_submit_activation;

    @FindBy(css = "tbody.Body_base__1ulvl tr")
    List<WebElement> row_of_mebers;

    @FindBy(css = "tr button:nth-child(1)")
    List<WebElement> btn_trash;

    @FindBy(css = "span.TruncatedText_container__8UkCa")
    List<WebElement> names_in_membersList;

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    List<String> mails;
    HashMap<String, String> map_links;


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
        mails = Arrays.asList(m3, m2, m1);


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
        try {
            for (int i = 0; i < mails.size(); i++) {
                System.out.println("in List: " + driver.findElement(By.xpath("//span[contains(text(),'" + mails.get(i) + "')]")).getText());
                System.out.println("in mail: " + mails.get(i));
                if (mails.get(i).equals(driver.findElement(By.xpath("//span[contains(text(),'" + mails.get(i) + "')]")).getText())) {
                    invitationsInList = true;
                } else {
                    invitationsInList = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invitationsInList;
    }

    public boolean verifyRoles(String role) {
        Boolean rolesInList = false;

        for (int i = 0; i < mails.size(); i++) {
            List<WebElement> roleInList = driver.findElements(By.xpath("//div[contains(@class, 'EntityIcon_small__2z6d9') and contains(@class,'Entity_icon__3YhQi')]/../../../td[3]"));
            highlight(roleInList.get(i));
            if (role.equals(roleInList.get(i).getText())) {
                System.out.println(i + ". Role of new member: " + roleInList.get(i).getText());
                rolesInList = true;
            } else {
                rolesInList = false;
            }
        }
        return rolesInList;
    }

    public boolean verifyRolesAfterActivation(String role) {
        Boolean rolesInList = false;


        for (int i = 0; i < 8 - countLicenses(); i++) {
            int num_licenses = 8 - countLicenses();
            String new_mail = mails.get(i);
            WebElement newMembrRoleInList = driver.findElement(By.xpath("(//figure/../../..//span[text()]/../../../../../..//td[3])[" + num_licenses + "]"));
            if (role.equals(newMembrRoleInList.getText())) {
                highlight(newMembrRoleInList);
                System.out.println(i + ". Role of new member: " + newMembrRoleInList.getText());
                rolesInList = true;
            } else {
                rolesInList = false;
            }
        }
        return rolesInList;
    }

    public boolean verifyMenuOfMember() throws InterruptedException {
        Boolean verifyMenuVisible = false;
        if (btn_menu_unavtive_member.size() == 0) {
            verifyMenuVisible = true;
        } else {
            for (int i = 0; i < btn_menu_unavtive_member.size(); i++) {
                click(btn_menu_unavtive_member.get(i));
                if (isElementVisible(driver.findElement(By.id("dropdown-tooltip")))) {
                    for (int j = 0; j < menu_member_items.size(); j++) {
                        highlight_blue(menu_member_items.get(j));
                        Thread.sleep(200);
                    }
                    click(btn_menu_unavtive_member.get(i));
                    verifyMenuVisible = true;
                } else {
                    verifyMenuVisible = false;
                }
            }
        }
        return verifyMenuVisible;
    }

    public boolean copyLink(String m1, String m2, String m3) throws InterruptedException {
        Boolean links_list_size = false;

        if (btn_menu_unavtive_member.size() == 0) {
            links_list_size = true;
        } else {
            map_links = new HashMap<>();
            mails = Arrays.asList(m3, m2, m1);
            String mail_for_map = null;

            for (int j = 0; j < btn_menu_unavtive_member.size(); j++) {                // iterate over all menu buttons
                click(btn_menu_unavtive_member.get(j));                                  // click on menu button (i)
                click(menu_member_items.get(1));                              // click on "Show Link" button
                if (isElementVisible(popup_with_link)) {                        // if popup with link opened do next....
                    Thread.sleep(500);
                    String copied_links = link.getAttribute("value");       // copy link
                    Collections.reverse(mails);
                    Thread.sleep(500);
                    mail_for_map = mails.get(j);
                    map_links.put(mail_for_map, copied_links);
                    click(btn_copy_link_done);                                   // click on Done
                    Thread.sleep(1000);
                }
            }
            map_links.forEach((k, v) -> System.out.println("map : " + k + " Value : " + v));
            if (map_links.size() > 0) {
                links_list_size = true;
            } else {
                links_list_size = false;
            }
        }
        return links_list_size;
    }

    public boolean activateMember() throws InterruptedException {
        String get_email = null;
        String get_link = null;
        boolean if_activation_end = false;

        if (btn_menu_unavtive_member.size() == 0) {
            if_activation_end = true;
        } else {
            for (Map.Entry<String, String> entry : map_links.entrySet()) {
                get_email = entry.getKey();
                get_link = entry.getValue();
                System.out.println("GET MAIL: " + get_email);

                Pattern p = Pattern.compile("^([a-z]+)_([a-z]+)@(.*)$");
                Matcher m = p.matcher(get_email);
                m.matches();
                String first_name = m.group(1);
                String last_name = m.group(2);

                System.out.println("fName: " + first_name);
                System.out.println("lastName: " + last_name);

                driver.get(get_link);
                new WebDriverWait(driver, Duration.ofSeconds(20)).until(driver -> driver.findElement(By.xpath("//h2[text()='Activate Your Account']")));
                fillText(input_firstName, first_name);
                fillText(input_lastName, last_name);
                fillText(input_password, "123$%^qweQWE");
                if (!isElementPresent2(btn_disabled_submit_activation)) {
                    click(driver.findElement(By.xpath("//button")));
                    wait.until(ExpectedConditions
                            .invisibilityOf(driver.findElement(By.xpath("(//button)[1]"))));
                }
            }
            driver.get("https://testcompany4.perimeter81.com/team/members");
            if (driver.getCurrentUrl().equals("https://testcompany4.perimeter81.com/team/members")) {
                if_activation_end = true;
            } else {
                if_activation_end = false;
            }
        }
        return if_activation_end;
    }

    public boolean verifyNew_activeMembers() {
        boolean verify_active_member = false;
        if (icons_of_unactivated_members.size() == 0) {
            for (int i = 0; i < icons_of_activated_members.size(); i++) {
                highlight(icons_of_activated_members.get(i));
            }
            verify_active_member = true;
        } else {
            verify_active_member = false;
        }
        return verify_active_member;
    }


    public boolean initiateChangeOfRole() throws InterruptedException {
        Boolean verifyMenuVisible = false;

        click(btn_menu_acvtive_member.get(0));
        click(btn_change_role);
        Thread.sleep(500);
        if (isElementVisible(driver.findElement(By.xpath("//div[@role='presentation']//form")))) {
            verifyMenuVisible = true;
            highlight_blue(driver.findElement(By.xpath("//div[@role='presentation']//form")));
        } else {
            verifyMenuVisible = false;
        }
        return verifyMenuVisible;
    }

    public boolean changeMemberRole(String role) throws InterruptedException {
        click(driver.findElement(By.xpath("(//div[@tabindex='0'])[2]")));
        click(btn_apply_role_change);

        Thread.sleep(5000);
        WebElement newMembrRoleInList = driver.findElement(By.xpath("//figure/../../..//span[text()='jon_snow@gmail.com']/../../../../../..//td[3]"));
        System.out.println("ROLE: " + newMembrRoleInList.getText());
        if (role.equals(newMembrRoleInList.getText())) {
            return true;
        } else return false;
    }

    public boolean initiateMemberDeletion() throws InterruptedException {
        boolean verify_delete_button = false;
        if (row_of_mebers.size() == 2) {
            verify_delete_button = true;
        } else {
            for (int i = 0; i < btn_trash.size(); i++) {
                click((btn_trash).get(i));
                Thread.sleep(500);
                if (isElementVisible(btn_delete_member)) {
                    verify_delete_button = true;
                    click(btn_cancel_delete_member);
                } else verify_delete_button = false;
            }
        }
        return verify_delete_button;
    }

    public boolean deleteMembersTest() throws InterruptedException {
        boolean verify_deletion = false;
        System.out.println("TRESH: " + btn_trash.size());
        while (countLicenses() != 8) {
            for (int i = 0; i < btn_trash.size(); i++) {
                System.out.println("Available member licenses: " + countLicenses());
                click((btn_trash).get(i));
                Thread.sleep(500);
                click(btn_delete_member);
                Thread.sleep(3000);
            }
        }
        int num_licenses = countLicenses();
        System.out.println("Num of trashes after deletion: " + (btn_trash.size()));
        System.out.println("Num of rows after deletion: " + row_of_mebers.size());
        System.out.println("Num of licenses after deletion: " + num_licenses);
        if (btn_trash.size() == 0 && row_of_mebers.size() == row_of_mebers.size() - btn_trash.size() && num_licenses == 8) {
            verify_deletion = true;
        } else verify_deletion = false;
        return verify_deletion;
    }

    public int countLicenses() {
        String licenses_count = driver.findElement(By.cssSelector(".LicensesRemaining_count__1m4vH")).getText();
        Pattern p = Pattern.compile("^([0-9]+)[/]([0-9]+)(.*)$");
        Matcher m = p.matcher(licenses_count);
        m.matches();
        String first_num = m.group(1);
        int licenses_count_to_integer = Integer.parseInt(first_num);
        return licenses_count_to_integer;
    }

}