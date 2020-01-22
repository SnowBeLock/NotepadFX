package lv.itlat.karina;

import javafx.beans.property.SimpleStringProperty;

public class Record {
    private SimpleStringProperty name = new SimpleStringProperty(this, "name");
    private SimpleStringProperty phone = new SimpleStringProperty(this, "phone");
    private SimpleStringProperty email = new SimpleStringProperty(this, "email");
    public String getName() {
        return name.get();
    }
    public SimpleStringProperty nameProperty() {
        return name;
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public String getPhone() {
        return phone.get();
    }
    public SimpleStringProperty phoneProperty() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone.set(phone);
    }
    public String getEmail() {
        return email.get();
    }
    public SimpleStringProperty emailProperty() {
        return email;
    }
    public void setEmail(String email) {
        this.email.set(email);
    }
}
