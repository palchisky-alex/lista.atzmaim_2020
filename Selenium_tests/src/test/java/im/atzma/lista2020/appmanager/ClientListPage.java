package im.atzma.lista2020.appmanager;

import im.atzma.lista2020.model.ClientData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClientListPage extends HelperBase {
    public ClientListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[text()='מאגר לקוחות']")
    WebElement title_on_clienPage;

    @FindBy(xpath = "//span[@class='app-clients-list__header-title count']")
    WebElement number_of_client;

    @FindBy(xpath = "//*[@class='item']")
    List<WebElement> clients_in_List;
    @FindBy(xpath = "//*[@class='item']")
    WebElement client_in_List;

    @FindBy(xpath = "//a[@class='item__tel']")
    List<WebElement> clients_link_in_List;

    @FindBy(xpath = "//*[@class='del-btn']//button")
    WebElement btn_deleteClient;

    @FindBy(xpath = "//button[@class='yes-btn']")
    WebElement btn_deleteClient_confirm;

    @FindBy(xpath = "//*[@class='item__info-name']")
    WebElement clientName_inList;

    @FindBy(xpath = "//div[@class='floating-button standartLeft']")
    WebElement btn_addClient;

    public boolean verifyTitleText() throws InterruptedException {
        waitForElement(title_on_clienPage);
        highlight(title_on_clienPage);
        if (title_on_clienPage.isDisplayed()) {
            return true;
        } else return false;
    }

    public String verifyNumberOfClient() throws InterruptedException {
        highlight(number_of_client);
        System.out.println("number of client: " + number_of_client.getText());
//        if(number_of_client.getText().contentEquals("(1)")) {
//            return true;
//        }
//        else return false;
        String n = number_of_client.getText();
        return n;
    }


    public int getClientCount() {
        return clients_in_List.size();
    }

    public void deleteSelectedClient() {
        click(btn_deleteClient);
        click(btn_deleteClient_confirm);
    }

    public void initAddNewClient() throws InterruptedException {
        click(btn_addClient);
    }



    public void selectClient(int index) {
        highlight(clients_link_in_List.get(index));
        click(clients_link_in_List.get(index));
    }


    public   Set<ClientData> getClientId() {
        Set<ClientData> client_group = new HashSet();
        for (WebElement el : clients_in_List) {

            int id = Integer.parseInt(el.getAttribute("data-id"));
            ClientData clientData = new ClientData(null, null, null, null,
                    null, null, null, null, null, id);
            client_group.add(clientData);
        }
        return  client_group;


    }

    public void deleteClientWithMaxId(int max) {

//        for (int i = 0; i < clients_in_List.size(); i++) {
//            int id = Integer.parseInt(clients_in_List.get(i).getAttribute("data-id"));
//            if(id == max) {
//                System.out.println("max id= " + max);
//                int index = i;
//                System.out.println("index= " + i);
//                selectClient(index);
//                deleteSelectedClient();
//            }
//        }

        WebElement client_in_List = driver.findElement(By.cssSelector(String.format("*[data-id='%s'] a[href]", max)));
        click(client_in_List);
        deleteSelectedClient();
    }
}
