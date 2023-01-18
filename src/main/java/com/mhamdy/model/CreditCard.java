package com.mhamdy.model;

/**
 * Created by: Mahmoud Hamdy on 18/01/2023
 */
public class CreditCard {
    private String no, validThru, ccv, name;

    public CreditCard(String no, String validThru, String ccv, String name) {
        this.no = no;
        this.validThru = validThru;
        this.ccv = ccv;
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getValidThru() {
        return validThru;
    }

    public void setValidThru(String validThru) {
        this.validThru = validThru;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
