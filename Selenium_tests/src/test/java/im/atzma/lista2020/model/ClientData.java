package im.atzma.lista2020.model;

import java.util.Objects;

public class ClientData {
    private final String tempClientName;
    private final String telNumber;
    private final String email;
    private final String address;
    private final String sex;
    private final String birthday;
    private final String debts;
    private final String note;
    private final String status;
    private Integer id;


    public ClientData(String tempClientName, String telNumber, String email, String address, String sex, String birthday, String debts, String note, String status, Integer id) {
        this.tempClientName = tempClientName;
        this.telNumber = telNumber;
        this.email = email;
        this.address = address;
        this.sex = sex;
        this.birthday = birthday;
        this.debts = debts;
        this.note = note;
        this.status = status;
        this.id = id;
    }

    public ClientData(String tempClientName, String telNumber, String email, String address) {
        this.tempClientName = tempClientName;
        this.telNumber = telNumber;
        this.email = email;
        this.address = address;
        this.sex = null;
        this.birthday = null;
        this.debts = null;
        this.note = null;
        this.status = null;
        this.id = 0;
    }

    @Override
    public String toString() {
        return "ClientData{" +
                "tempClientName='" + tempClientName + '\'' +
                ", telNumber='" + telNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", debts='" + debts + '\'' +
                ", note='" + note + '\'' +
                ", status='" + status + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientData that = (ClientData) o;
        return Objects.equals(tempClientName, that.tempClientName) &&
                Objects.equals(telNumber, that.telNumber) &&
                Objects.equals(email, that.email) &&
                Objects.equals(address, that.address) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(debts, that.debts) &&
                Objects.equals(note, that.note) &&
                Objects.equals(status, that.status) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tempClientName, telNumber, email, address, sex, birthday, debts, note, status, id);
    }

    public String getTempClientName() {
        return tempClientName;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() { return address; }

    public String getSex() { return sex;}

    public String getBirthday() { return birthday; }

    public String getDebts() { return debts; }

    public String getNote() { return note; }

    public String getStatus() { return status; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
