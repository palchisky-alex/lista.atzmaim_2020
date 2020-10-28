package im.atzma.lista2020.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class P81_PolicyHelper extends HelperBase {
    @FindBy(xpath = "//button")
    WebElement btn_addPolicy;

    @FindBy(xpath = "//input[@name='name']")
    WebElement input_policyName;
    @FindBy(css = "span.TruncatedText_container__8UkCa")
    List<WebElement>  policyNames_onPage;
    @FindBy(css = "span.TruncatedText_container__8UkCa")
    WebElement policyName_onPage;

    @FindBy(css = ".CreatePolicy_createPolicyButtons__1tpfG  button:nth-child(2)")
    WebElement btn_save;

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

    public int verifyNewPolicy() throws InterruptedException {
        System.out.println("Number of Policy on the page: " + policyNames_onPage.size());
        for (int i = 0; i < policyNames_onPage.size(); i++) {
            System.out.println(i + ". Policy on the page: " + policyName_onPage.getText());
        }
        return policyNames_onPage.size();

    }

    public void initiatePolicyDeletion() {
    }
}
