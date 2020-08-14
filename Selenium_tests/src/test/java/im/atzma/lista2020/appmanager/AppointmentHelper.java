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

    @FindBy(xpath = "//tr[5]/td[1]")
    WebElement time_10;

    @FindBy(xpath = "//a[@data-appointment_id]")
    List<WebElement> btn_existing_appointment;

    @FindBy(xpath = "//input[@placeholder]")
    WebElement input_findClient;

    @FindBy(xpath = "//span[@class='all-clients__item-name']")
    WebElement tempClient;

    @FindBy(css = ".procedures-container .procedures-item")
    WebElement tempService;

    @FindBy(xpath = "//span[@class='procedures-item__add']")
    WebElement btn_procedures_item__add;

    @FindBy(xpath = "//div[text()='Temp services_katalon']")
    WebElement temp_service;

    @FindBy(css = "input.search-inner__input")
    WebElement input_findService;

    @FindBy(css = ".bottomnav__bottom.bottomnav__bottom--next")
    WebElement btn_save;

    @FindBy(xpath = "//p[@class='event-start']")
    WebElement appointmentTime;

    @FindBy(xpath = "//*[@class= 'event-duration']")
    WebElement appointmentDuration;
    @FindBy(xpath = "//*[@class= 'full-dur']")
    WebElement appointmentDuration_min;

    @FindBy(xpath = "//*[@class='client-name']")
    WebElement appointmentClientName;

    @FindBy(css = ".client-address")
    WebElement appointmentAddress;
    @FindBy(css = ".client-note")
    WebElement appointmentNote;

    @FindBy(xpath = "//*[@class='service-item']")
    WebElement appointmentServiceName;

    @FindBy(xpath = "//button[@class='btn-styl delete']")
    WebElement btn_deleteAppointment;
    @FindBy(xpath = "//button[@class='btn-styl edite']")
    WebElement btn_modifyAppointment;

    @FindBy(css = ".deleteModal .yes-btn")
    WebElement btn_confirm_AppointmentDeletion;

    @FindBy(xpath = "//p[@class='floating-button standartLeft']")
    WebElement btn_addNewAppointment;

    @FindBy(xpath = "//div[@class='prev_button_wrap common']")
    WebElement back_arrow;

    @FindBy(xpath = "//div[@class='next_button_wrap common']")
    WebElement next_arrow;

    @FindBy(css = "#refresh_button")
    WebElement btn_refresh;


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
    @FindBy(css = ".category__list-search > [type=search]")
    WebElement inputBox_placeholder;

    @FindBy(xpath = "//*[@class='category__list-add-procedure category__list-add-procedure--active']")
    WebElement btn_add_newCategory;

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

    @FindBy(xpath = "//*[@href='/he/settings/business']/p")
    WebElement btn_buisness_settings;
    @FindBy(css = ".button-delete")
    WebElement btn_deleteAccount;
    @FindBy(css = ".yes-btn")
    WebElement btn_yes;

    public void test() {
        click(next_arrow);
    }

    public void create_A() throws InterruptedException {
        chooseAppointmentHour();
        click(btn_save);
        waitForElement(input_findService);
        click(btn_save);
    }

    public void create_B(String clientName, String tel, String service, String category) throws InterruptedException {
        chooseAppointmentHour();
        click(driver.findElement(By.cssSelector("button.create_btn")));     // add client
        fillText(driver.findElement(By.id("text")), clientName);            // add client
        fillText(driver.findElement(By.cssSelector("[name=phone]")), tel);  // add client
        click(btn_save);                                                    // save client
        Thread.sleep(2000);
        addServiceCategory(service, category);
//        click(driver.findElement(By.cssSelector("button.create_btn")));                     // add service
//        waitForElement(driver.findElement(By.cssSelector(".add-procedure > .add-procedure__main-content")));
//        fillText(driver.findElement(By.cssSelector("input[name=checkClientsList]")), service);   // add service
//
//        click(driver.findElement(By.cssSelector(".category__wrap > .category")));                           // add category
//        fillText(driver.findElement(By.cssSelector(".category__list-search > [type=search]")), category);   // add category
//        click(driver.findElement(By.cssSelector("button.category__list-add-procedure")));                   // save category
//

//        click(btn_save); // save service for queue
//        waitForElement(driver.findElement(By.cssSelector("span.favorites-procedures-name__span")));
//        click(btn_save); // save service for queue
    }

    public void create_C(String clientName, String serviceName) throws InterruptedException {
        chooseAppointmentHour();
        fillNewAppointment(clientName);
        chooseService(serviceName);
    }

    public void create_D(String clientName) throws InterruptedException {
        chooseAppointmentHour();
        fillNewAppointment(clientName);
        click(btn_save);

    }

    public void create_E(String service) throws InterruptedException {
        chooseAppointmentHour();
        click(btn_save); // next
        chooseService(service);
    }

    public void create_F(String clientName) throws InterruptedException {
        chooseAppointmentHour();
        fillNewAppointment(clientName);
    }

    public void fillNewAppointment(String clientName) throws InterruptedException {
        waitForElement(input_findClient);
        click(input_findClient);
        fillText(input_findClient, clientName);
        waitForElement(tempClient);
        click(tempClient);
    }


    private void chooseService(String serviceName) throws InterruptedException {
        fillText(input_findClient, serviceName);
        click(tempService);
        click(btn_save);
    }

    public void addQueueNote(String my_note) throws InterruptedException {
        clickJScript(driver.findElement(By.cssSelector("form:nth-child(1) .switch-label")));
        fillText(driver.findElement(By.cssSelector("textarea.subject-textarea-wrap__text")), my_note);
    }

    public void addQueueAdress() {
        clickJScript(driver.findElement(By.cssSelector("form.switch-form")));
    }

    public void addNewQueueAdress() throws InterruptedException {
        clickJScript(driver.findElement(By.cssSelector("form.switch-form")));
        fillText(driver.findElement(By.cssSelector("input#pac-input")), "רוקח 18, רמת גן, ישראל");
        click(driver.findElement(By.cssSelector(".center.save-button")));
    }

    public void addDurationAndPriceToService() {
        for (int i = 0; i < 3; i++) {
            click(driver.findElement(By.cssSelector(".round-button:nth-child(5)")));
            click(driver.findElement(By.cssSelector(".round-button:nth-child(4)")));
        }
    }

    public void addServiceCategory(String service, String category) throws InterruptedException {
        click(driver.findElement(By.cssSelector("button.create_btn")));
        fillText(input_findService, service);
        click(driver.findElement(By.cssSelector(".category__wrap > .category")));
        fillText(driver.findElement(By.cssSelector(".category__list-search > [type=search]")), category);
        click(driver.findElement(By.cssSelector("button.category__list-add-procedure")));
        click(btn_save);
        click(btn_save);

    }


    public List<String> verifyAppointmentCreation() throws InterruptedException {
        List<String> itemList = new ArrayList<>();
        itemList.add("◀ Duration: " + appointmentDuration.getText() + " ▶");
        itemList.add("◀ Time: " + appointmentTime.getText() + " ▶");
        itemList.add("◀ Client name: " + appointmentClientName.getText() + " ▶");
        itemList.add("◀ Service: " + appointmentServiceName.getText() + " ▶");
        itemList.add("◀ Address: " + appointmentAddress.getText() + " ▶");
        itemList.add("◀ Note: " + appointmentNote.getText() + " ▶");

        System.out.println(itemList);

        return itemList;
    }

    public List<String> verifyAppointmentCreation_A() {
        List<String> itemList = new ArrayList<>();
        itemList.add(appointmentTime.getText());
        itemList.add(appointmentClientName.getText());
        itemList.add(appointmentServiceName.getText());
        itemList.add(appointmentDuration.getText());
        itemList.add(appointmentNote.getText());
        itemList.add(appointmentAddress.getText());

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
        while (btn_existing_appointment.size() > 0) {
            clickOnExistsAppointment();
            driver.navigate().refresh();
            Thread.sleep(200);
        }
//        waitForElement(btn_deleteAppointment);
//        click(btn_deleteAppointment);
//        waitForElement(btn_confirm_AppointmentDeletion);
//        click(btn_confirm_AppointmentDeletion);

    }


    public int appointmentList() throws InterruptedException {
        //click(btn_refresh);
        return btn_existing_appointment.size();
    }

    public void chooseAppointmentHour() throws InterruptedException {

        if (isElementPresent2(driver.findElements(By.cssSelector(".fc-nonbusiness")))) {
            clickJS(driver.findElement(By.cssSelector(".fc-nonbusiness")));
            Thread.sleep(500);
        } else System.out.println("nonbusiness day not present");
click(driver.findElement(By.cssSelector("[data-time='12:00:00'] td:nth-child(1)")));
//        driver.findElement(By.xpath("//tr[5]/td[1]")).click(); // click on empty slot hour
    }

    public void clickOnExistsAppointment() throws InterruptedException {
        System.out.println(btn_existing_appointment.size());

        click(btn_existing_appointment.get(0));
        click(btn_deleteAppointment);
        click(btn_confirm_AppointmentDeletion);
        System.out.println(btn_existing_appointment.size());

    }

    public void clickOnExistsAppointment2() throws InterruptedException {
        driver.navigate().refresh();
        for (int i = 0; i < btn_existing_appointment.size(); i++) {
            click(btn_existing_appointment.get(i));
        }
    }


    private void verifyAppointmentExistens() throws InterruptedException {
        if (!isElementPresent2(driver.findElements(By.cssSelector("//p[@class='event-start']")))) {
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
        addDurationAndPriceToService();
        click(btn_save);
    }

    public void deleteAccount() throws InterruptedException, IOException {
        driver.get(propertiesList("web.settings"));
        click(btn_buisness_settings);
        click(btn_deleteAccount);
        if (isElementPresent2(driver.findElements(By.cssSelector(".yes-btn")))) {
            click(btn_yes);
        } else {
            new RuntimeException("yes button not present");
        }

    }

    public String verifyAccountDeletion() throws InterruptedException {
        String error = msg_error.getText();
        System.out.println(msg_error.getText());
        highlight(msg_error);

        return error;
    }

}