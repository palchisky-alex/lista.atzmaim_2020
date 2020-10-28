package im.atzma.lista2020.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.Futures.withTimeout;
import static java.util.concurrent.TimeUnit.SECONDS;

public class P81_PolicyHelper extends HelperBase {
    @FindBy(xpath = "(//button)[1]")
    WebElement btn_addPolicy;

    @FindBy(xpath = "//h3[text()='You have no policies yet']")
    List<WebElement> message_noPolicy;

    @FindBy(xpath = "//tr[@class='Row_withBorder__QkRNl']//button")
    List<WebElement> btn_policyMenu;
    @FindBy(xpath = "//ul[@class='DropdownMenu_base__2Ec6d']/li")
    List<WebElement> policy_menu_items;

    @FindBy(xpath = "//tbody[@class='Body_base__1ulvl']/tr")
    List<WebElement> policy_rows;
    @FindBy(xpath = "//input[@name='name']")
    WebElement input_policyName;
    @FindBy(css = "span.TruncatedText_container__8UkCa")
    List<WebElement> policyNames_onPage;
    @FindBy(css = "span.TruncatedText_container__8UkCa")
    WebElement policyName_onPage;

    @FindBy(css = ".CreatePolicy_createPolicyButtons__1tpfG  button:nth-child(2)")
    WebElement btn_save;
    @FindBy(css = ".Footer_buttons__1Bvuj button:nth-child(1)")
    WebElement btn_delete;


    public P81_PolicyHelper(WebDriver driver) {
        super(driver);
    }

    public boolean initiatePolicyCreation() {
        click(btn_addPolicy);
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(driver -> driver.findElement(By.xpath("//input[@name='name']")));

        Boolean isEditable = input_policyName.isEnabled() && input_policyName.getAttribute("readonly") == null;
        return isEditable;
    }

    public void goToLoginPage() throws InterruptedException {
        driver.get("https://testcompany4.perimeter81.com/sign-in");
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(driver -> driver.findElement(By.xpath("//input[@id='1-email']")));

    }

    public boolean policyPage() {
        if (driver.getCurrentUrl().equals("https://testcompany4.perimeter81.com/policies/create")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addPolicy(String policy) throws InterruptedException {
        fillText(input_policyName, policy);
        click(btn_save);

        if (driver.getCurrentUrl().equals("https://testcompany4.perimeter81.com/policies")) {
            return true;
        } else {
            return false;
        }
    }

    public int verifyNumberOfPolicy() {
        System.out.println("Number of Policy on the page: " + policyNames_onPage.size());
        return policyNames_onPage.size();
    }

    public boolean initiatePolicyDeletion() throws InterruptedException {
        boolean verify_menuItems = false;

        click(btn_policyMenu.get(0));
        for (int g = 0; g < policy_menu_items.size(); g++) {
            System.out.println(g + ". Policy menu items: " + policy_menu_items.get(g).getText());
            policy_menu_items.size();
            Boolean isEditable = policy_menu_items.get(0).isEnabled() && policy_menu_items.get(0).getAttribute("readonly") == null && policy_menu_items.size() == 2;
            if (isEditable) {
                verify_menuItems = true;
            } else {
                verify_menuItems = false;
            }
        }

        return verify_menuItems;
    }

    public void deletePolicies() throws InterruptedException {
        System.out.println("Number of policies to deletion: " + policyNames_onPage.size());
        String policyNames = null;
        int numPolicyRows_onPage = policy_rows.size();

        while (numPolicyRows_onPage > 0) {
            for (int i = 0; i < numPolicyRows_onPage; i++) {

                policyNames = policyNames_onPage.get(i).getText();
                System.out.println("Policy to delete: " + driver.findElement(By.xpath("//span[@class='TruncatedText_container__8UkCa'][text()='" + policyNames + "']")).getText());
                click(btn_policyMenu.get(i));
                click(policy_menu_items.get(1));
                click(btn_delete);
                Thread.sleep(1000);
                numPolicyRows_onPage += -1;
                System.out.println("Number of policies after deletion: " + numPolicyRows_onPage);
            }
        }
    }


}
