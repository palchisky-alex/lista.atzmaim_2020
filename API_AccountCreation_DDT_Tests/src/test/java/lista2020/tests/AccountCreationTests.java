package lista2020.tests;

import lista2020.appmanager.Excel;
import org.approvaltests.Approvals;
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

    @Test(priority = 3)
    public void deleteAccount() {
        app.accountCreationHelper().deleteAccount();
    }


    @DataProvider //D:\Devel_lista2020\lista.atzmaim_2020\API_AccountCreation_DDT_Tests\src\test\resources
    public Object[][] getDataFromExcel(){
        Excel excel = new Excel();
        return excel.getTableArray(System.getProperty("user.dir") + "/src/test/resources/input.xlsx", "TimeZone");
    }
}