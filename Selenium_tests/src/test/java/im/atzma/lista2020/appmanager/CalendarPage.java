package im.atzma.lista2020.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CalendarPage extends HelperBase {
    public CalendarPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='more_wrap']")
    WebElement menu_gamburger;

    @FindBy(css = "p.business_name")
    WebElement username_in_menu;

    @FindBy(xpath = "//a[@href='/he/logout']")
    WebElement btn_logout;

    @FindBy(xpath = "//p[@class='business_name']")
    WebElement text_businessName;

    @FindBy(xpath = "//p[@class='business_address']")
    WebElement text_businessAddress;

    @FindBy(xpath = "//a[@href=\"/he/calendar\"]")
    WebElement text_calendar;

    @FindBy(xpath = "//a[@href=\"/he/clients\"]")
    WebElement text_clientDB;

    @FindBy(xpath = "//div[@class='menu']//a")
    List<WebElement> links_in_menu;


    public boolean logout() {
        highlight(menu_gamburger);
        click(menu_gamburger);
        if (btn_logout.isDisplayed()) {
            highlight(btn_logout);
            click(btn_logout);
            return true;
        } else return false;
    }

    public String verifyUserinMenu() {
        highlight(menu_gamburger);
        click(menu_gamburger);
        System.out.println("user name in menu: " + username_in_menu.getText());
        highlight(username_in_menu);
        return username_in_menu.getText();
    }

    public List<String> verifyMenuLinks() {
        click(menu_gamburger);
        List<String> itemList = new ArrayList<>();

        for (int i = 0; i < links_in_menu.size(); i++) {
            itemList.add(links_in_menu.get(i).getText());
            System.out.println("MenuTest item " + i + "-" + links_in_menu.get(i).getText());
            highlight(links_in_menu.get(i));
        }

        return itemList;
    }
}
