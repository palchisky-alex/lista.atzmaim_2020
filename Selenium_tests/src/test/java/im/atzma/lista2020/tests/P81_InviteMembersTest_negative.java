package im.atzma.lista2020.tests;

import im.atzma.lista2020.appmanager.P81_Excel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class P81_InviteMembersTest_negative extends TestBase {

    @Test(priority = 1)
    public void addNewMailTest_noError() throws InterruptedException {

        String my_error = null;
        Assert.assertEquals(app.p81_membersHelper().addMail_noError(), my_error);

    }

    @Test(priority = 2, dataProvider="getDataFromExcel")
    public void addNewMailTest_negative(String mail) throws InterruptedException {

        String my_error = "Email address is not valid";
        Assert.assertEquals(app.p81_membersHelper().addMail_negative(mail), my_error);

    }

    //================== @DataProvider ===============================//
    @DataProvider
    public Object[][] getDataFromExcel(){
        P81_Excel excel = new P81_Excel();
        return excel.getTableArray(System.getProperty("user.dir") + "/src/test/resources/P81_input.xlsx", "p81_mails");
    }
}

