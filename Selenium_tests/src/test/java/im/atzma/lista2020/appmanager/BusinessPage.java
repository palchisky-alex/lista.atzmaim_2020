package im.atzma.lista2020.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class BusinessPage extends HelperBase{ public BusinessPage(WebDriver driver) { super(driver); }

    @FindBy(xpath = "//img[@src='/public/signup/media/ic_back.svg']")
    WebElement arrow_back;

    @FindBy(xpath = "//img[@src='/public/signup/media/business_types_icons/one-on-one_meetings.svg']")
    WebElement img_atzmai;

    @FindBy(xpath = "//div[text()='עצמאי']")
    WebElement text_atzmai;

    @FindBy(xpath = "//div[text()='עצמאי']/..")
    WebElement btn_atzmai;

    @FindBy(xpath = "//img[@src='/public/signup/media/business_types_icons/hair_styling.svg']")
    WebElement img_maspera;

    @FindBy(xpath = "//div[text()='מספרה']")
    WebElement text_maspera;

    @FindBy(xpath = "//div[text()='נא לבחור סוג העסק']")
    WebElement text_title;

    @FindBy(xpath = "//div[text()='אנחנו נתאים את האפליקציה לצרכים שלך']")
    WebElement text_title2;

    @FindBy(xpath = "//div[text()='אתם יכולים לבחור יותר מאחד או']")
    WebElement text_title3;

    @FindBy(xpath = "//button[text()='לדלג הלאה']")
    WebElement btn_ledaleg;

    @FindBy(xpath = "//span[text()='בואו נתחיל!']/..")
    WebElement btn_submit;

    @FindBy(xpath = "//div[@class='bussiness-type']")
    WebElement btn_text_submit;

    @FindBy(xpath = "//div[text()='בחרת ב:']")
    WebElement text_title4;

    @FindBy(xpath = "//span[text()='עצמאי']")
    WebElement text_title5;

    @FindBy(xpath = "//img[@src='/public/signup/media/business_types_icons/violet-one-on-one_meetings.svg']")
    WebElement img_atzmai_checked;

    @FindBy(xpath = "//div[@class='bussiness-type__checkmark']")
    WebElement img_V;

    public List<WebElement> getBusnessPageElemenents() {
        List<WebElement> itemList = new ArrayList<>();
        itemList.add(arrow_back);
        itemList.add(img_atzmai);
        itemList.add(text_atzmai);
        itemList.add(img_maspera);
        itemList.add(text_maspera);
        itemList.add(text_title);
        itemList.add(text_title2);
        itemList.add(text_title3);
        itemList.add(btn_ledaleg);
        itemList.add(btn_submit);
        itemList.add(btn_text_submit);

        for (int i = 0; i < itemList.size(); i++) {
            highlight(itemList.get(i));
        }
        return itemList;
    }

    public  List<String> markBussnessType_button() {
        click(btn_atzmai);
        String boxShadow = btn_atzmai.getCssValue("box-shadow");
        String color = btn_atzmai.getCssValue("color");
        String backgroundColor = btn_atzmai.getCssValue("background-color");

        List<String> itemList = new ArrayList<>();
        itemList.add(backgroundColor);
        itemList.add(boxShadow);
        itemList.add(color);
        return itemList;
    }

    public List<WebElement> markBussnessType_titles() {
        List<WebElement> itemList = new ArrayList<>();
        itemList.add(text_title4);
        itemList.add(text_title5);
        itemList.add(img_atzmai_checked);
        itemList.add(img_V);

        for (int i = 0; i < itemList.size(); i++) {
            highlight(itemList.get(i));
        }

        return itemList;
    }

    public String submitButtonColor() {
        String submitButtonColor = btn_submit.getCssValue("background");
        return submitButtonColor;
    }

}
