package im.atzma.lista2020.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllSetPage extends HelperBase{
    public AllSetPage(WebDriver driver) {super(driver);}

    @FindBy(xpath = "//img[@src='/public/signup/media/ic_back.svg']")
    WebElement arrow_back;

    @FindBy(xpath = "//img[@src='/public/signup/media/sing-up-img.png']")
    WebElement img_background;

    @FindBy(xpath = "//div[text()='אנחנו סיימנו כאן!']")
    WebElement text_title1;

    @FindBy(xpath = "//span[text()='תודה על שיתוף הפעולה']")
    WebElement text_title2;

    @FindBy(xpath = "//span[text()='עכשיו אתם יכולים להתחיל ליהנות משימוש באפליקציה']")
    WebElement text_title3;

    @FindBy(xpath = "//span[text()='אנחנו הולכים לשלוח מידע חשוב וטיפים על שימוש באפליקציה, אתם מסכימים לזה?']")
    WebElement text_label1;

    @FindBy(xpath = "//span[text()='אני מסכים לתנאי הסכם שימוש של אפליקציה Atzma.im CRM']")
    WebElement text_label2;

    @FindBy(xpath = "//label[@for='first']")
    WebElement label1;

    @FindBy(xpath = "//label[@for='twice']")
    WebElement label2;

    @FindBy(xpath = "//span[text()='בואו נתחיל!']")
    WebElement btn_text;

    @FindBy(xpath = "//button")
    WebElement btn_submit;


    public List<WebElement>getAllSetPageElements() {
        List<WebElement> itemList = new ArrayList<>();
        itemList.add(arrow_back);
        itemList.add(img_background);
        itemList.add(text_title1);
        itemList.add(text_title2);
        itemList.add(text_title3);
        itemList.add(text_label1);
        itemList.add(text_label2);
        itemList.add(label1);
        itemList.add(label2);
        itemList.add(btn_text);
        itemList.add(btn_submit);
        for (int i = 0; i < itemList.size(); i++) {
            highlight(itemList.get(i));
        }
        return itemList;
    }

    public  List<String> verifySubmitButton_default() {
        String fontSize = btn_submit.getCssValue("font-size");
        String color = btn_submit.getCssValue("color");
        String backgroundColor = btn_submit.getCssValue("background");

        List<String> itemList =  Arrays.asList(fontSize, color, backgroundColor);
        return itemList;
    }

    public List<String> clickFirstCheckbox() {
        click(label1); //------- ----------------------------------------mark first checkbox
        List<String> itemList = verifySubmitButton_default();
        return  itemList;
    }

    public void clickSecondCheckbox() {
        click(label1); //------- ----------------------------------------uncheck optional first checkbox
        click(label2); //------- ----------------------------------------mark second checkbox
    }
}
