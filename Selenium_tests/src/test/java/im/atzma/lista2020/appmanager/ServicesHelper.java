package im.atzma.lista2020.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ServicesHelper extends HelperBase {
    public ServicesHelper(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='input-search-block']")
    WebElement service_searchBox;

    @FindBy(xpath = "//p[text()='פגישה 30 דקות']")
    WebElement default_service_name_30min;

    @FindBy(xpath = "//p[text()='פגישה 45 דקות']")
    WebElement default_service_name_45min;

    @FindBy(xpath = "//p[text()='פגישה 60 דקות']")
    WebElement default_service_name_60min;

    @FindBy(xpath = "(//span[text()='כללית'])[1]")
    WebElement default_service_title;

    @FindBy(xpath = "(//span[text()='כללית'])[2]")
    WebElement default_service_title2;

    @FindBy(xpath = "(//span[text()='כללית'])[3]")
    WebElement default_service_title3;

    @FindBy(xpath = "(//img[@src='/public/services/ic-time-copy.svg'])[1]")
    WebElement default_img_clock;

    @FindBy(xpath = "(//img[@src='/public/services/ic-time-copy.svg'])[2]")
    WebElement default_img_clock2;

    @FindBy(xpath = "(//img[@src='/public/services/ic-time-copy.svg'])[3]")
    WebElement default_img_clock3;

    @FindBy(xpath = "(//span[text()='דקות'])[1]")
    WebElement default_text;

    @FindBy(xpath = "(//span[text()='דקות'])[2]")
    WebElement default_text2;

    @FindBy(xpath = "(//span[text()='דקות'])[3]")
    WebElement default_text3;

    @FindBy(xpath = "//span[text()='30']")
    WebElement default_service_duration_30min;

    @FindBy(xpath = "//span[text()='45']")
    WebElement default_service_duration_45min;

    @FindBy(xpath = "//span[text()='60']")
    WebElement default_service_duration_60min;

    @FindBy(xpath = "(//span[text()='₪'])[1]")
    WebElement default_text_shekel;

    @FindBy(xpath = "(//span[text()='₪'])[2]")
    WebElement default_text_shekel2;

    @FindBy(xpath = "(//span[text()='₪'])[3]")
    WebElement default_text_shekel3;

    @FindBy(xpath = "(//span[text()='0'])[1]")
    WebElement default_service_cost;

    @FindBy(xpath = "(//span[text()='0'])[2]")
    WebElement default_service_cost2;

    @FindBy(xpath = "(//span[text()='0'])[3]")
    WebElement default_service_cost3;

    @FindBy(xpath = "//label[@for='styled-checkbox-1']")
    WebElement checkbox_trash1;

    @FindBy(xpath = "//label[@for='styled-checkbox-2']")
    WebElement checkbox_trash2;

    @FindBy(xpath = "//label[@for='styled-checkbox-3']")
    WebElement checkbox_trash3;

    @FindBy(xpath = "//img[@class='trash']")
    WebElement icon_trash;

    @FindBy(css = ".add-button__icon")
    WebElement btn_addService;

    @FindBy(xpath = "//*[@name='checkClientsList']")
    WebElement input_serviceName;

    @FindBy(css = ".category-name__title")
    WebElement btn_addCategory;

    @FindBy(xpath = "//*[@name='search']")
    WebElement input_categoryName;

    @FindBy(css = ".create-button")
    WebElement btn_saveCategory;

    @FindBy(css = ".enabled")
    WebElement btn_saveService_enabled;

    @FindBy(xpath = "//button[@class='bottom disabled']")
    WebElement btn_saveService_disabled;

    @FindBy(xpath = "//input[@placeholder='הזינו שם של טיפול']")
    WebElement inputBox_placeholder;

    @FindBy(xpath = "//p[@class='name-services']")
    WebElement btn_procedures_item__add;

    @FindBy(xpath = "//p[@class='name-services']")
    List<WebElement> btn_procedures_item__add_list;

    @FindBy(xpath = "//img[@src=\"/public/services/ic-search.svg\"]")
    WebElement icon_magnifier;

    @FindBy(xpath = "//button[@class='more_wrap']")
    WebElement btn_menu;

    @FindBy(xpath = "//span[text()='רשימת טיפולים']")
    WebElement pageTitle;

    @FindBy(xpath = "//img[@src='/public/services/ic_arrow_back.svg']")
    WebElement back_arrow;

    @FindBy(xpath = "//span[text()='שם של טיפול']")
    WebElement form_title_1;

    @FindBy(xpath = "//span[text()='שייך לקטגוריה']")
    WebElement form_title_2;

    @FindBy(xpath = "//span[text()='משך']")
    WebElement form_title_3;

    @FindBy(xpath = "//span[text()='משך']")
    WebElement form_title_4;

    @FindBy(xpath = "//span[text()='מחיר']")
    WebElement form_title_5;

    @FindBy(xpath = "//div[@class='circle-container']")
    WebElement color_container;

    @FindBy(xpath = "//button[text()='הוסף טיפול חדש']")
    WebElement btn_saveService_text;

    @FindBy(xpath = "//button[text()='לבטל']")
    WebElement btn_cancelService_text;

    @FindBy(xpath = "(//img[@src='/public/services/minus.svg'])[1]/..")
    WebElement btn_minus_1;

    @FindBy(xpath = "(//img[@src='/public/services/minus.svg'])[2]/..")
    WebElement btn_minus_2;

    @FindBy(xpath = "(//img[@src='/public/services/plus.svg'])[1]/..")
    WebElement btn_plus_1;

    @FindBy(xpath = "(//img[@src='/public/services/plus.svg'])[2]/..")
    WebElement btn_plus_2;

    @FindBy(xpath = "//*[text()='30 דקות']")
    WebElement serviceProp_30min;

    @FindBy(xpath = "//*[text()='₪ 50']")
    WebElement serviceProp_50shek;

    @FindBy(xpath = "//img[@class='arrow-back']")
    WebElement arrow_back;

    @FindBy(xpath = "//img[@src='/public/services/chevron-right.svg']")
    WebElement arrow_back_2;

    @FindBy(xpath = "//p[contains(text(), 'Temp')]")
    List<WebElement> temp_service_inList;
    @FindBy(xpath = "//p[contains(text(), 'Temp')]")
    WebElement temp_service_inList_one;

    @FindBy(xpath = "//button[@class='bottom delete']")
    WebElement btn_delete_service;

    @FindBy(xpath = "//button[@class='yes-btn']")
    WebElement btn_comfirm_service_deletion;


    public List<WebElement> verifyDefaultService_30min() {
        List<WebElement> itemList = new ArrayList<>();
        itemList.add(default_service_name_30min);
        itemList.add(default_service_title);
        itemList.add(default_img_clock);
        itemList.add(default_text);
        itemList.add(default_service_duration_30min);
        itemList.add(default_text_shekel);
        itemList.add(default_service_cost);
        itemList.add(checkbox_trash1);

        for (int i = 0; i < itemList.size(); i++) {
            highlight(itemList.get(i));
            System.out.println("Default service elements - 30 min: " + itemList.get(i).getText());
        }
        return itemList;
    }

    public List<WebElement> verifyDefaultService_45min() {
        List<WebElement> itemList = new ArrayList<>();
        itemList.add(default_service_name_45min);
        itemList.add(default_service_title2);
        itemList.add(default_img_clock2);
        itemList.add(default_text2);
        itemList.add(default_service_duration_45min);
        itemList.add(default_text_shekel2);
        itemList.add(default_service_cost2);
        itemList.add(checkbox_trash2);

        for (int i = 0; i < itemList.size(); i++) {
            highlight(itemList.get(i));
            System.out.println("Default service element - 45min: " + itemList.get(i).getText());
        }
        return itemList;
    }

    public List<WebElement> verifyDefaultService_60min() {
        List<WebElement> itemList = new ArrayList<>();
        itemList.add(default_service_name_60min);
        itemList.add(default_service_title3);
        itemList.add(default_img_clock3);
        itemList.add(default_text3);
        itemList.add(default_service_duration_60min);
        itemList.add(default_text_shekel3);
        itemList.add(default_service_cost3);
        itemList.add(checkbox_trash3);

        for (int i = 0; i < itemList.size(); i++) {
            highlight(itemList.get(i));
            System.out.println("Default service element - 60min: " + itemList.get(i).getText());
        }
        return itemList;
    }

    public boolean verifyTrashIcon() throws InterruptedException {
        if (isElementPresent(icon_trash)) {
            return true;
        } else return false;
    }

    public boolean verifyTrashIconPresent_1() throws InterruptedException {
        click(checkbox_trash1);
        if (isElementPresent(icon_trash)) {
            click(checkbox_trash1);
            return true;
        } else return false;
    }

    public boolean verifyTrashIconPresent_2() throws InterruptedException {
        click(checkbox_trash2);
        if (isElementPresent(icon_trash)) {
            click(checkbox_trash2);
            return true;
        } else return false;
    }

    public boolean verifyTrashIconPresent_3() throws InterruptedException {
        click(checkbox_trash3);
        if (isElementPresent(icon_trash)) {
            click(checkbox_trash3);
            return true;
        } else return false;
    }

    public void initAddNewService() {
        click(btn_addService);
    }

    public void fillServiceFrom(String serviceName, String categoryName) throws InterruptedException {
        click(btn_addService);
        fillText(input_serviceName, serviceName);
        click(btn_addCategory);
        fillText(input_categoryName, categoryName);
        click(btn_saveCategory);
        waitForElement(btn_saveService_enabled);
        driver.findElement(By.cssSelector(".enabled")).click();
//        System.out.println(btn_saveService_enabled.getAttribute("background-color"));
//        if(isElementVisible( driver.findElement(By.cssSelector(".enabled")))) {
//        }




//        driver.findElement(By.cssSelector(".more_wrap")).click();

    }

    public void saveServiceFrom() throws InterruptedException {
        click(btn_saveService_enabled);


    }

    public String verifyTempService(String tempServiceName) throws InterruptedException {
        fillText(inputBox_placeholder, tempServiceName);
        highlight( btn_procedures_item__add);
        return btn_procedures_item__add.getText();
    }

    public List<WebElement> verifyServicePageElements() {
        List<WebElement> itemList = new ArrayList<>();
        itemList.add(service_searchBox);
        itemList.add(inputBox_placeholder);
        itemList.add(icon_magnifier);
        itemList.add(btn_addService);
        itemList.add(btn_menu);
        itemList.add(pageTitle);
        itemList.add(back_arrow);

        for (int i = 0; i < itemList.size(); i++) {
            highlight(itemList.get(i));
        }
        return itemList;

    }

    public List<WebElement> verifyDefaultServiceFormElements() {
        List<WebElement> itemList = new ArrayList<>();
        itemList.add(form_title_1);
        itemList.add(form_title_2);
        itemList.add(form_title_3);
        itemList.add(form_title_4);
        itemList.add(form_title_5);
        itemList.add(color_container);
        itemList.add(btn_saveService_text);
        itemList.add(btn_cancelService_text);
        itemList.add(btn_minus_1);
        itemList.add(btn_minus_2);
        itemList.add(btn_plus_1);
        itemList.add(btn_plus_2);
        itemList.add(serviceProp_30min);
        itemList.add(serviceProp_50shek);
        itemList.add(arrow_back);
        itemList.add(arrow_back_2);

        for (int i = 0; i < itemList.size(); i++) {
            highlight(itemList.get(i));
        }
        return itemList;
    }

    public String verifyDisabledButtonColor() {
        String actualButton = btn_saveService_disabled.getCssValue("background-color");
        return actualButton;
    }

    public String verifyEnableddButtonColor() {
        for (int i = 0; i < 10; i++) {
            click(btn_plus_1);
        }
        for (int i = 0; i < 10; i++) {
            click(btn_plus_2);
        }
        for (int i = 0; i < 10; i++) {
            click(btn_minus_1);
        }
        for (int i = 0; i < 10; i++) {
            click(btn_minus_2);
        }

        String actualButton = btn_saveService_enabled.getCssValue("background-color");
        return actualButton;
    }


    public void deleteTempService() throws InterruptedException {

            for (int i = 0; i < temp_service_inList.size(); i++) {

                click(temp_service_inList.get(i));

                click(btn_delete_service);

                click(btn_comfirm_service_deletion);

            }

//        driver.navigate().refresh();

    }

//    public List<String> verifyServiceDeletion() {
//        driver.navigate().refresh();
//        List<String> itemList = new ArrayList<>();
//        for (int i = 0; i < btn_procedures_item__add_list.size(); i++) {
//            itemList.add(btn_procedures_item__add_list.get(i).getText());
//            System.out.println(btn_procedures_item__add_list.get(i).getText());
//            highlight(btn_procedures_item__add_list.get(i));
//        }
//        return itemList;
//    }
}
