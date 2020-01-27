package im.atzma.lista2020.model;

import java.io.File;

public class ModifyClientData {
    private final String clientName;
    private final String clientTel;
    private final String clientMail;
    private final String clientAddress;
    private final String birthdayYear;
    private final String birthdayMonth;
    private final String birthdayDay;
    private final int debtAmount;
    private final String noteText;
    private File photo;

    public ModifyClientData(String clientName, String clientTel, String clientMail, String clientAddress, String birthdayYear, String birthdayMonth, String birthdayDay, int debtAmount, String noteText) {
        this.clientName = clientName;
        this.clientTel = clientTel;
        this.clientMail = clientMail;
        this.clientAddress = clientAddress;
        this.birthdayYear = birthdayYear;
        this.birthdayMonth = birthdayMonth;
        this.birthdayDay = birthdayDay;
        this.debtAmount = debtAmount;
        this.noteText = noteText;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientTel() {
        return clientTel;
    }

    public String getClientMail() {
        return clientMail;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public String getBirthdayYear() {
        return birthdayYear;
    }

    public String getBirthdayMonth() {
        return birthdayMonth;
    }

    public String getBirthdayDay() {
        return birthdayDay;
    }

    public int getDebtAmount() {
        return debtAmount;
    }

    public String getNoteText() {
        return noteText;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }
}
