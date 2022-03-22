package ru.dilichev.AccountControl.Models;

public class LegalClient {
    private long id;
    private String name;
    private String form;
    private String tin;

    public LegalClient()
    {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getForm() {
        return form;
    }

    public String getTin() {
        return tin;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }
}
