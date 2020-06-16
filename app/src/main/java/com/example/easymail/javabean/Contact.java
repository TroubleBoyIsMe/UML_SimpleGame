package com.example.easymail.javabean;

import org.litepal.crud.LitePalSupport;

public class Contact extends LitePalSupport {
    private String name;
    private String number;
    private String explan;

    public Contact() {
        super();
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getExplan() {
        return explan;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setExplan(String explan) {
        this.explan = explan;
    }

    public long getFatherBaseObjId(){
        return super.getBaseObjId();
    }
}
