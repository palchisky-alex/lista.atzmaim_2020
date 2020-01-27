package im.atzma.lista2020.appmanager;

import im.atzma.lista2020.model.ClientData;
import im.atzma.lista2020.model.ModifyClientData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ClientHelper extends HelperBase {

    @FindBy(xpath = "//input[@placeholder=\"הזן שם של לקוח\"]")
    WebElement input_clientName;

    @FindBy(xpath = "//input[@class='input-active']")
    WebElement text_telephon;

    @FindBy(xpath = "//input[@placeholder=\"הזן אימייל של לקוח\"]")
    WebElement text_email;

    @FindBy(xpath = "//input[@placeholder=\"הזן כתובת של לקוח\"]")
    WebElement text_address;

    @FindBy(xpath = "//select[1]")
    WebElement select_year;

    @FindBy(xpath = "//select[2]")
    WebElement select_month;

    @FindBy(xpath = "//select[3]")
    WebElement select_day;

    @FindBy(xpath = "//input[@value='male']")
    WebElement radio_sex_male;

    @FindBy(xpath = "//input[@value='female']")
    WebElement radio_sex_female;

    @FindBy(css = "#notes label")
    WebElement btn_addNote;

    @FindBy(css = "#notes textarea")
    WebElement area_note;

    @FindBy(css = "#notes button.save")
    WebElement btn_saveNote;

    @FindBy(css = "#debts .debt-footer img")
    WebElement btn_addDebt;

    @FindBy(xpath = "//img[@src='/public/adding-client/plus.svg']")
    WebElement btn_plus_debt;

    @FindBy(css = "#debts input.description-input")
    WebElement area_debt;

    @FindBy(css = "#debts .button-apply p")
    WebElement btn_saveDebtNote;

    @FindBy(css = "#save .bot-button")
    WebElement btn_saveClientForm;

    @FindBy(xpath = "//*[@class='client-name']/h1")
    WebElement title_newClientName;

    @FindBy(xpath = "//span[text()='חזור']")
    WebElement btn_back;

    @FindBy(xpath = "//h1[text()='לקוח חדש']")
    WebElement title_newclient;

    @FindBy(xpath = "//*[@class='top-button']")
    WebElement btn_top_save;

    @FindBy(xpath = "//span[text()='יום הולדת:']")
    WebElement title_birthday;

    @FindBy(xpath = "//span[text()='מין:']")
    WebElement title_sex;

    @FindBy(xpath = "//input[@placeholder=\"הזן שם של לקוח\"]")
    WebElement input_name;

    @FindBy(xpath = "//input[@class=\"input-active\"]")
    WebElement input_telephon;

    @FindBy(xpath = "//input[@placeholder=\"הזן אימייל של לקוח\"]")
    WebElement input_email;

    @FindBy(xpath = "//input[@placeholder=\"הזן כתובת של לקוח\"]")
    WebElement input_address;

    @FindBy(xpath = "//img[@src=\"/public/adding-client/group2.svg\"]")
    WebElement icon_1;
    @FindBy(xpath = "//img[@src=\"/public/adding-client/group3.svg\"]")
    WebElement icon_2;
    @FindBy(xpath = "//img[@src=\"/public/adding-client/group5.svg\"]")
    WebElement icon_3;
    @FindBy(xpath = "//img[@src=\"/public/adding-client/group6.svg\"]")
    WebElement icon_4;

    @FindBy(xpath = "(//img[@src=\"/public/adding-client/c_add_stroke.svg\"])[1]")
    WebElement btn_plus_1;
    @FindBy(xpath = "(//img[@src=\"/public/adding-client/c_add_stroke.svg\"])[2]")
    WebElement btn_plus_2;

    @FindBy(xpath = "//*[@id='name']//*[@class='block-content']")
    WebElement text_clientName;
    @FindBy(xpath = "//*[@class='text']")
    WebElement text_clientAddress;
    @FindBy(xpath = "//*[@class='phone-labels']")
    WebElement text_clientPhone;
    @FindBy(xpath = "//*[@class='gmailcom']")
    WebElement text_clientMail;
    @FindBy(xpath = "//*[@class='sex-label']")
    WebElement text_sex;
    @FindBy(xpath = "//*[@class='ymd']")
    WebElement text_clientBirthday;
    @FindBy(xpath = "//*[@class='debt-list-name']")
    WebElement text_clientDebts;
    @FindBy(xpath = "//*[@class='notes-list-desc rem_false']")
    WebElement text_clientNote;
    @FindBy(xpath = "//*[@class='status-config']")
    WebElement text_clientStatus;

    @FindBy(xpath = "//*[@class='edit-profile']")
    WebElement btn_edit_profile;
    @FindBy(css = "#name-input")
    WebElement input_new_ClientName;
    @FindBy(xpath = "//*[@class='phone-edit']//input")
    WebElement input_new_ClientTel;
    @FindBy(css = "#email-input")
    WebElement input_new_ClientMail;
    @FindBy(css = "#pac-input")
    WebElement input_new_ClientAddress;
    @FindBy(css = "#sex")
    WebElement input_new_ClientSex;
    @FindBy(css = "#radioFemale")
    WebElement btn_new_ClientSexFemale;
    @FindBy(xpath = "//*[@class='save-btn']")
    WebElement btn_new_ClientSave;
    @FindBy(xpath = "//*[@class='del-wrap']")
    List<WebElement> btn_new_ClientCleanInputs;

    @FindBy(css = "#debts > div .right-side")
    WebElement btn_new_ClientEditDebts;
    @FindBy(xpath = "//*[@class='del-debts']")
    WebElement btn_new_ClientDeleteDebt;
    @FindBy(xpath = "//img[@src= '/public/clients-details/plus.svg']")
    WebElement btn_new_ClientAddDebtPlus;
    @FindBy(xpath = "//*[@class='main-button']")
    WebElement btn_new_ClientOpenAllInputs;

    @FindBy(css = "#notes > div .right-side")
    WebElement btn_new_ClientEditNote;
    @FindBy(css = "#notes .delete")
    WebElement btn_new_ClientDeleteNote;
    @FindBy(xpath = "//*[@data-id]")
    List<WebElement> clients_in_List;

    @FindBy(xpath = "//button[@class='back']")
    WebElement message_same_number;
    @FindBy(css = ".main-button button")
    WebElement btn_openAllsettings;
    @FindBy(css = ".gallery-footer img")
    WebElement btn_addPhoto;
    @FindBy(css = ".gallery-modal-footer button")
    WebElement btn_addPhoto2;


    public ClientHelper(WebDriver driver) {
        super(driver);
    }

    int count = 0;

    public void fillClientForm(ClientData clientData) throws InterruptedException {
        fillText(input_clientName, clientData.getTempClientName());
        fillText(text_telephon, clientData.getTelNumber());

        click(text_email);
        try {
            if (message_same_number.isDisplayed()) {
                click(message_same_number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        fillText(text_email, clientData.getEmail());
        fillText(text_address, clientData.getAddress());

        select(select_year, "1950");
        select(select_month, "10");
        select(select_day, "10");
        click(radio_sex_male);
        click(btn_addNote);
        waitForElement(area_note);
        fillText(area_note, "הערות של לקוח זמני");
        click(btn_saveNote);
        click(btn_addDebt);
        click(btn_plus_debt);
        fillText(area_debt, "חוב  של לקוח זמני");
        click(btn_saveDebtNote);

        click(btn_saveClientForm);
        Thread.sleep(2000);
    }

    public String verifyNewClientCreation() {
        highlight(title_newClientName);
        System.out.println("Name of ew client: " + title_newClientName);
        return title_newClientName.getText();
    }

    public Integer verifyNewClientForm() {
        List<WebElement> itemList = new ArrayList<>();
        itemList.add(btn_back);
        itemList.add(btn_top_save);
        itemList.add(title_newclient);
        itemList.add(title_birthday);
        itemList.add(title_sex);
        itemList.add(input_name);
        itemList.add(input_address);
        itemList.add(input_email);
        itemList.add(input_telephon);
        itemList.add(radio_sex_male);
        itemList.add(radio_sex_female);
        itemList.add(icon_1);
        itemList.add(icon_2);
        itemList.add(icon_3);
        itemList.add(icon_4);
        itemList.add(btn_addNote);
        itemList.add(btn_addDebt);
        itemList.add(btn_plus_1);
        itemList.add(btn_plus_2);
        itemList.add(btn_saveClientForm);

        for (int i = 0; i < itemList.size(); i++) {
            highlight(itemList.get(i));
            if (itemList.get(i).isDisplayed()) {
                count++;
            }
        }
        return count;
    }

    public List<ClientData> getClientList() {
        List<ClientData> data = new ArrayList<ClientData>();

        String name = text_clientName.getText();
        String phone = text_clientPhone.getText();
        String mail = text_clientMail.getText();
        String address = text_clientAddress.getText();
        String sex = text_sex.getText();
        String birthday = text_clientBirthday.getText();
        String debts = text_clientDebts.getText();
        String note = text_clientNote.getText();
        String status = text_clientStatus.getText();

        ClientData clientData = new ClientData(name, phone, mail, address, sex, birthday, debts, note, status, 0);

        data.add(clientData);

        return data;
    }

    public void initClientModification() {
        click(btn_edit_profile);
        for (int i = 0; i < btn_new_ClientCleanInputs.size(); i++) {
            click(btn_new_ClientCleanInputs.get(i));
        }
    }

    public void modify(ModifyClientData modifyClientData) throws InterruptedException {
        fillText(input_new_ClientName, modifyClientData.getClientName());
        fillText(input_new_ClientTel, modifyClientData.getClientTel());
        fillText(input_new_ClientMail, modifyClientData.getClientMail());
        fillText(input_new_ClientAddress, modifyClientData.getClientAddress());
        click(btn_new_ClientSexFemale);
        select(select_year, modifyClientData.getBirthdayYear());
        select(select_month, modifyClientData.getBirthdayMonth());
        select(select_day, modifyClientData.getBirthdayDay());

        modifyClientDebt(modifyClientData.getDebtAmount());
        modifyClientNote(modifyClientData.getNoteText());

//        click(btn_openAllsettings);
//        attach(btn_addPhoto, modifyClientData.getPhoto());

        click(btn_new_ClientSave);

    }

    public void modifyClientDebt(int addDebtPlus) throws InterruptedException {
        click(btn_new_ClientEditDebts);
        click(btn_new_ClientDeleteDebt);
        click(btn_new_ClientOpenAllInputs);
        click(btn_addDebt);
        fillText(area_debt, "New debt");

        for (int i = 0; i < addDebtPlus; i++) {
            click(btn_new_ClientAddDebtPlus);
        }
        click(btn_saveDebtNote);
    }

    public void modifyClientNote(String newNote) throws InterruptedException {
        click(btn_new_ClientEditNote);
        click(btn_new_ClientDeleteNote);
        click(btn_addNote);
        waitForElement(area_note);
        fillText(area_note, newNote);
        click(btn_saveNote);
    }

}
