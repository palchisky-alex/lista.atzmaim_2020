package lista2020.tests;

import org.approvaltests.Approvals;
import org.testng.annotations.Test;

import java.util.HashSet;

import static org.testng.Assert.assertEquals;

public class VerifyServicesSetTests extends TestBase {

    @Test(priority = 1)
    public void getHolisticJSON() {
        Approvals.verify(app.serviceRestHelper().createAccountWithType("[1]"));

    }

    @Test(priority = 2)
    public void getConsultansJSON() {
        Approvals.verify(app.serviceRestHelper().createAccountWithType("[2]"));
    }

    @Test(priority = 3)
    public void getCosmeticsJSON() {
        Approvals.verify(app.serviceRestHelper().createAccountWithType("[3]"));
    }

    @Test(priority = 4)
    public void getHairSalonJSON() {
        Approvals.verify(app.serviceRestHelper().createAccountWithType("[4]"));
    }

    @Test(priority = 5)
    public void getInteriorJSON() {
        Approvals.verify(app.serviceRestHelper().createAccountWithType("[5]"));
    }

    @Test(priority = 6)
    public void getInstallersJSON() {
        Approvals.verify(app.serviceRestHelper().createAccountWithType("[6]"));
    }

    @Test(priority = 7)
    public void getMakeupJSON() {
        Approvals.verify(app.serviceRestHelper().createAccountWithType("[7]"));
    }

    @Test(priority = 8)
    public void getNailJSON() {
        Approvals.verify(app.serviceRestHelper().createAccountWithType("[8]"));
    }

    @Test(priority = 9)
    public void getMassageJSON() {
        Approvals.verify(app.serviceRestHelper().createAccountWithType("[9]"));
    }

    @Test(priority = 10)
    public void getMoversJSON() {
        Approvals.verify(app.serviceRestHelper().createAccountWithType("[10]"));
    }

    @Test(priority = 11)
    public void getNutritionistsJSON() {
        Approvals.verify(app.serviceRestHelper().createAccountWithType("[11]"));
    }

    @Test(priority = 12)
    public void getPestJSON() {
        Approvals.verify(app.serviceRestHelper().createAccountWithType("[12]"));
    }

    @Test(priority = 13)
    public void getTeachersJSON() {
        Approvals.verify(app.serviceRestHelper().createAccountWithType("[13]"));
    }

    @Test(priority = 14)
    public void getSalesJSON() {
        Approvals.verify(app.serviceRestHelper().createAccountWithType("[14]"));
    }

    @Test(priority = 15)
    public void getServiceProvidersJSON() {
        Approvals.verify(app.serviceRestHelper().createAccountWithType("[15]"));
    }

    @Test(priority = 16)
    public void getPersonalJSON() {
        Approvals.verify(app.serviceRestHelper().createAccountWithType("[16]"));
    }

    @Test(priority = 17)
    public void getTattooJSON() {
        Approvals.verify(app.serviceRestHelper().createAccountWithType("[17]"));
    }

    @Test(priority = 18)
    public void getTherapistsJSON() {
        Approvals.verify(app.serviceRestHelper().createAccountWithType("[18]"));
    }

    @Test(priority = 19)
    public void getAllTypeJSON() {
        for (int i = 0; i < 1; i++) {

            Approvals.verify(app.serviceRestHelper().createAccountWithType("[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18]"));
        }
    }

    @Test(priority = 20)
    public void withoutTypeJSON() {
        for (int i = 0; i < 1; i++) {
            Approvals.verify(app.serviceRestHelper().createAccountWithType("[]"));
        }
    }




    @Test(priority = 21)
    public void deleteAccount() {

        assertEquals(app.serviceRestHelper().deleteAccount(), new HashSet<>() );
    }


}