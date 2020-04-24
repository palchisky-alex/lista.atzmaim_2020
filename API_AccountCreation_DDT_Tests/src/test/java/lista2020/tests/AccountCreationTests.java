package lista2020.tests;

import lista2020.appmanager.Excel;
import org.approvaltests.Approvals;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AccountCreationTests extends TestBase {

    @Test(priority = 1)
    public void getBusinessTypeJSON() {

        Approvals.verify(app.accountCreationHelper().businessTypeResponse());

    }

    @Test(priority = 2, dataProvider="getDataFromExcel")
    public void createAccount(String mail, String password, String city, String country, String timezone, String tel) {
        Approvals.verify(app.accountCreationHelper().createAccount(mail, password, city, country, timezone, tel));
    }

    @Test(priority = 3, dataProvider="getDataFromExcel2")
    public void deleteAccount(String mail, String password) {

        Assert.assertEquals(app.accountCreationHelper().deleteAccount(mail, password), null);
    }


    @DataProvider
    public Object[][] getDataFromExcel(){
        Excel excel = new Excel();
        return excel.getTableArray(System.getProperty("user.dir") + "/src/test/resources/input.xlsx", "TimeZone");
    }

    @DataProvider
    public Object[][] getDataFromExcel2(){
        Excel excel = new Excel();
        return excel.getTableArray(System.getProperty("user.dir") + "/src/test/resources/input.xlsx", "List_accounts");
    }
}