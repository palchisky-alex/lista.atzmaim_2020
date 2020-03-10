package im.atzma.lista2020.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentHelper extends HelperBase {
    public AppointmentHelper(WebDriver driver) {
        super(driver);
    }

    @FindBy(css="tr[data-time='15:00:00']>td:nth-child(1)")
    WebElement time_15;

    @FindBy(css = "tr[data-time] > :first-child")
    List<WebElement> all_time_slots;

    @FindBy(xpath = "//a[@data-appointment_id]")
    List<WebElement> btn_existing_appointment;

    @FindBy(xpath = "//input[@placeholder=\"חפש שם, טלפון או הזן לקוח חדש\"]")
    WebElement input_findClient;

    @FindBy(xpath = "//span[@class='all-clients__item-name']")
    WebElement tempClient;

    @FindBy(xpath = "//p[@class='name-services']")
    WebElement tempService;

    @FindBy(xpath = "//span[@class='procedures-item__add']")
    WebElement btn_procedures_item__add;

    @FindBy(xpath = "//div[text()='Temp services_katalon']")
    WebElement temp_service;

    @FindBy(xpath = "//input[@placeholder='חפש טיפול או הכנס חדש']")
    WebElement input_findService;

    @FindBy(xpath = "//span[text()='הבא']/..")
    WebElement btn_next;

    @FindBy(xpath = "//img[@src='/public/creating-appointment/save-white.svg']")
    WebElement btn_save;

    @FindBy(xpath = "//p[@class='event-start']")
    WebElement appointmentTime;

    @FindBy(xpath = "//*[@class= 'event-duration']")
    WebElement appointmentDuration;
    @FindBy(xpath = "//*[@class= 'full-dur']")
    WebElement appointmentDuration_min;

    @FindBy(xpath = "//*[@class='client-name']")
    WebElement appointmentClientName;

    @FindBy(xpath = "//*[@class='service-item']")
    WebElement appointmentServiceName;

    @FindBy(xpath = "//button[@class=\"btn-styl delete\"]")
    WebElement btn_deleteAppointment;
    @FindBy(xpath = "//button[@class='btn-styl edite']")
    WebElement btn_modifyAppointment;

    @FindBy(xpath = "//button[@class=\"yes-btn\"]")
    WebElement btn_confirm_AppointmentDeletion;

    @FindBy(xpath = "//p[@class=\"floating-button standartLeft\"]")
    WebElement btn_addNewAppointment;

    @FindBy(css = ".fc-business-container")
    WebElement nonbusiness;

    @FindBy(xpath = "//div[@class='prev_button_wrap common']")
    WebElement back_arrow;

    @FindBy(xpath = "//div[@class='next_button_wrap common']")
    WebElement next_arrow;

    @FindBy(xpath = "//*[@class='strip-name']/p")
    WebElement strip_name;
    @FindBy(xpath = "//*[@class='wrap-tel']//span")
    WebElement wrap_tel;
    @FindBy(xpath = "//*[@class='time-dur']/p")
    WebElement time_dur;
    @FindBy(xpath = "//*[@class='duration']/div")
    WebElement duration;
    @FindBy(xpath = "//*[@class='service-name']//p")
    WebElement service_name;
    @FindBy(xpath = "//*[@class='price']/span")
    WebElement price;

    @FindBy(xpath = "//*[@class='duration__pretty-value']")
    WebElement duration_form;
    @FindBy(xpath = "//*[@class='price__pretty-value']")
    WebElement price_form;
    @FindBy(xpath = "//*[@class='price-step__procedure']")
    WebElement service_name_form;
    @FindBy(xpath = "//*[@class='date-step__time']")
    WebElement time_dur_form;
    @FindBy(xpath = "//*[@class='header__user-name']")
    WebElement appointmentClientName_form;
    @FindBy(xpath = "(//*[@class='text'])[1]")
    WebElement btn_back_form;
    @FindBy(xpath = "(//*[@class='text'])[2]")
    WebElement btn_save_form;
    @FindBy(xpath = "//span[@class='bottomnav__bottom bottomnav__bottom--next']")
    WebElement btn_save_form_2;
    @FindBy(xpath = "//input[@placeholder='הזן שם של קטגוריה חדשה']")
    WebElement inputBox_placeholder;
    @FindBy(xpath = "//button")
    WebElement btn_add_category;
    @FindBy(xpath = "//*[@class='category__list-add-procedure category__list-add-procedure--active']")
    WebElement btn_add_newCategory;
    @FindBy(xpath = "//span[text()='הוסף טיפול']/..")
    WebElement btn_add_Service;
    @FindBy(css = ".add-button.add-rtl")
    WebElement icon_plus_addSerice;
    @FindBy(xpath = "//img[@class='search-inner__img--close']")
    WebElement btn_delete_search_result;

    @FindBy(xpath = "(//*[@class='regulation-menu-plus'])[2]")
    WebElement btn_duration_plus;
    @FindBy(xpath = "(//*[@class='regulation-menu-plus'])[4]")
    WebElement btn_price_plus;

    @FindBy(xpath = "//span[@class='popup-cross']")
    WebElement btn_x;
    @FindBy(xpath = "//*[@class='favorites-procedures__x']")
    WebElement btn_delete_old_service;

    @FindBy(css = "#dateInput")
    WebElement dateArea;
    @FindBy(css = "#timeInput")
    WebElement timeArea;
    @FindBy(xpath = "(//div[@class='regulation-menu-plus'])[1]")
    WebElement btn_remove_service;

    @FindBy(xpath = "//span[@class='login-err__text']")
    WebElement msg_error;
    @FindBy(xpath = "//*[@style='max-height: 0px; overflow: hidden;']")
    WebElement service_area;

    @FindBy(xpath = "//a[@href='/he/settings/business_settings_group']")
    WebElement btn_buisness_settings;
    @FindBy(css = ".button-delete")
    WebElement btn_deleteAccount;

    public void test() {
        click(next_arrow);
    }


    public void create(String clientName) throws InterruptedException {
        // verifyNonbusinessDay();
        chooseAppointmentHour();
        fillNewAppointment(clientName);
    }

    public void fillNewAppointment(String name) throws InterruptedException {
        waitForElement(input_findClient);
        click(input_findClient);
        fillText(input_findClient, name);
        waitForElement(tempClient);
        click(tempClient);


    }

    public void addServiceCategory(String service, String notExistCategory) throws InterruptedException {
        click(input_findService);
        waitForElement(input_findService);
        fillText(input_findService, " ");
        click(input_findService);
        fillText(input_findService, service);
        waitForElement(btn_add_Service);
        click(btn_add_Service);
        waitForElement(inputBox_placeholder);
        fillText(inputBox_placeholder, notExistCategory);
        waitForElement(btn_add_newCategory);
        click(btn_add_newCategory);

        waitForElement(btn_add_Service);
        System.out.println("Button text 3 : " + btn_add_Service.getText());
        click(btn_add_Service);


        waitForElement(btn_next);
        System.out.println("Button text 4 : " + btn_next.getText());
        click(btn_next);

        Thread.sleep(500);

    }


    public List<String> verifyAppointmentCreation() throws InterruptedException {
        // verifyNonbusinessDay();
        List<String> itemList = new ArrayList<>();
        itemList.add(appointmentTime.getText());
        itemList.add(appointmentClientName.getText());
        itemList.add(appointmentServiceName.getText());
        itemList.add(appointmentDuration.getText());

        System.out.println(itemList);

        return itemList;
    }

    public List<String> verifyAppointmentCreation2() throws InterruptedException {

        List<String> itemList = new ArrayList<>();
        itemList.add(appointmentTime.getText());
        itemList.add(appointmentClientName.getText());
        itemList.add(appointmentServiceName.getText());
        itemList.add(appointmentDuration_min.getText());

        for (int i = 0; i < itemList.size(); i++) {
            System.out.println(itemList.get(i));
        }

        return itemList;
    }

    public void deleteAppointment() throws InterruptedException {
//        verifyNonbusinessDay();

        clickOnExistsAppointment();
        waitForElement(btn_deleteAppointment);
        click(btn_deleteAppointment);
        waitForElement(btn_confirm_AppointmentDeletion);
        click(btn_confirm_AppointmentDeletion);

    }


    public Integer appointmentList() throws InterruptedException {

        return btn_existing_appointment.size();
    }

    public void chooseAppointmentHour() throws InterruptedException {
      //  wait.until(ExpectedConditions.elementToBeClickable(time_15));
        click(time_15);
    }

    public void clickOnExistsAppointment() throws InterruptedException {
        //  verifyNonbusinessDay();
        System.out.println(btn_existing_appointment.size());

        if (btn_existing_appointment.size() < 1) {
            click(back_arrow);
            waitForElement(appointmentTime);
        }
        for (int i = 0; i < btn_existing_appointment.size(); i++) {
            click(btn_existing_appointment.get(i));
        }
    }

    public void clickOnExistsAppointment2() throws InterruptedException {
        driver.navigate().refresh();
        for (int i = 0; i < btn_existing_appointment.size(); i++) {
            click(btn_existing_appointment.get(i));
        }
    }


    private void verifyAppointmentExistens() {
        if (!isElementPresent(appointmentTime)) {
            click(back_arrow);
        }
    }

    public void verifyNonbusinessDay() {
        if (isElementPresent(nonbusiness)) {
            click(back_arrow);
        }
    }

    public List<String> verifyAppointmentElements() {
        List<String> itemList = new ArrayList<>();
        itemList.add(strip_name.getText());
        itemList.add(wrap_tel.getText());
        itemList.add(time_dur.getText());
        itemList.add(duration.getText());
        itemList.add(service_name.getText());
        itemList.add(price.getText());
        itemList.add(btn_deleteAppointment.getText());
        itemList.add(btn_modifyAppointment.getText());
        itemList.add(btn_x.getText());

        List<WebElement> itemList2 = new ArrayList<>();
        itemList2.add(strip_name);
        itemList2.add(wrap_tel);
        itemList2.add(time_dur);
        itemList2.add(duration);
        itemList2.add(service_name);
        itemList2.add(price);
        itemList2.add(btn_deleteAppointment);
        itemList2.add(btn_modifyAppointment);
        itemList2.add(btn_x);

        for (int i = 0; i < itemList2.size(); i++) {
            highlight(itemList2.get(i));
        }

        return itemList;
    }

    public List<String> verifyForm() throws InterruptedException {
        List<String> itemList = new ArrayList<>();
        itemList.add(duration_form.getText());
        itemList.add(price_form.getText());
        itemList.add(service_name_form.getText());
        itemList.add(time_dur_form.getText());
        itemList.add(appointmentClientName_form.getText());
        itemList.add(btn_back_form.getText());
        itemList.add(btn_save_form.getText());

        for (int i = 0; i < itemList.size(); i++) {
            System.out.println("Created appointment elements: " + itemList.get(i));
        }

        List<WebElement> itemList2 = new ArrayList<>();
        itemList2.add(duration_form);
        itemList2.add(price_form);
        itemList2.add(service_name_form);
        itemList2.add(time_dur_form);
        itemList2.add(appointmentClientName_form);
        itemList2.add(btn_back_form);
        itemList2.add(btn_save_form);

        for (int i = 0; i < itemList2.size(); i++) {
            highlight(itemList2.get(i));
        }

        return itemList;
    }

    public void initAppModification() throws InterruptedException {
        click(btn_modifyAppointment);
    }

    public void modifyService() {
        click(service_name_form);
        click(btn_delete_old_service);
    }

    public void modifyAppTime() {
        click(time_dur_form);
        dateArea.sendKeys(Keys.ARROW_RIGHT, Keys.ARROW_UP);
        timeArea.sendKeys(Keys.ARROW_UP);
        click(btn_save_form_2);
    }

    public void modifyServiceDuration() {
//        click(duration_form);
//        for (int i = 0; i < 10; i++) {
//            duration_form.sendKeys(Keys.ARROW_UP);
//        }
        for (int i = 0; i < 5; i++) {
            click(btn_duration_plus);
        }
    }

    public void modifyServicePrice() {
//        for (int i = 0; i < 5; i++) {
//            price_form.sendKeys(Keys.ARROW_UP);
//            click(btn_save_form_2);
//        }
        for (int i = 0; i < 5; i++) {
            click(btn_price_plus);
        }
    }

    public void modifyAppService(String tempServiceName) throws InterruptedException {


        fillText(input_findService, tempServiceName);
        Thread.sleep(1000);
        click(temp_service);
        click(btn_save_form_2);
        click(btn_save_form_2);
    }

    public void modifyClient() {
        click(appointmentClientName_form);
    }

    public void saveForm() throws InterruptedException {
        driver.navigate().refresh();
        waitForElement(btn_save);
        System.out.println("Button text 5 : " + btn_save.getText());
        click(btn_save);
    }

    public void deleteAccount() throws InterruptedException, IOException {
        driver.get(propertiesList("web.settings"));
        waitForElement(btn_buisness_settings);
        click(btn_buisness_settings);
        highlight(btn_deleteAccount);
        click(btn_deleteAccount);
        driver.findElement(By.xpath("//button[@class='yes-btn']")).click();

    }

    public String verifyAccountDeletion() throws InterruptedException {
        String error = msg_error.getText();
        System.out.println(msg_error.getText());
        highlight(msg_error);

        return error;
    }


}
