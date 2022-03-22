package ru.dilichev.AccountControl.Models;

public class PhysicalClient {
    private long id;
    private String fullName;
    private String passport;
    private String tin;

    public PhysicalClient()
    {}

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassport() {
        return passport;
    }

    public String getTin() {
        return tin;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }
}
