package ru.dilichev.AccountControl.Models;

public class Client {
    private long id;
    private String type;
    private String phone;
    private String address;

    public Client()
    {}

    public void setId(long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
