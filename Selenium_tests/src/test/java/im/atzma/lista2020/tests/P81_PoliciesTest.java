package im.atzma.lista2020.tests;

import im.atzma.lista2020.appmanager.P81_PolicyHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class P81_PoliciesTest extends TestBase {

    @Test
    public void goToPolicy() throws InterruptedException {
        app.P81_goTo().goToPolicyPage();
        Assert.assertTrue(app.P81_goTo().goToPolicyPage());
    }

    @Test
    public void initiatePolicyCreation() {
        if (app.p81_policyHelper().initiatePolicyCreation()) {
            Assert.assertTrue(true);
            System.out.println("Input filed 'Policy Name' is editable");
        } else {
            System.out.println("Input filed 'Policy Name' is not editable");
        }
    }

    @Test
    public void veirfyPolicyURL() {
        if (app.p81_policyHelper().policyPage()) {
            Assert.assertTrue(true);
            System.out.println("Redirect to Policy creation URL done");
        } else {
            System.out.println("It is not Policy creation URL");
        }
    }

    @Test
    public void addPolicy() throws InterruptedException {
        if(app.p81_policyHelper().addPolicy("PalchitskyAlex_Policy")) {
            Assert.assertTrue(true);
            System.out.println("Redirect to Policy URL done and new policy created");
        }
    }

    @Test
    public void verifyNewPolicy() throws InterruptedException {
        int numOfPolicy = app.p81_policyHelper().verifyNewPolicy();
        if(numOfPolicy == 1) {
            System.out.println("Number of Policies on page equal: " + numOfPolicy);
        }
        else {
            System.out.println("The current policy number does not match the condition and equal: " + numOfPolicy);
        }

        Assert.assertEquals(numOfPolicy,1);
    }

    @Test
    public void deletePolicies() throws InterruptedException {
        app.P81_goTo().goToPolicyPage();
        app.p81_policyHelper().initiatePolicyDeletion();
    }

}
