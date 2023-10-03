package com.example.personalstylist.model;

public class Measurements {
    String id,uid,Neckwidth, shoulderlength, armlength,  armwidth, shirtwidth, shirtlength, paintlength;

    public Measurements() {
    }

    public Measurements(String id,String uid, String neckwidth, String shoulderlength, String armlength, String armwidth, String shirtwidth, String shirtlength, String paintlength) {
        this.id = id;
        this.uid=uid;
        Neckwidth = neckwidth;
        this.shoulderlength = shoulderlength;
        this.armlength = armlength;
        this.armwidth = armwidth;
        this.shirtwidth = shirtwidth;
        this.shirtlength = shirtlength;
        this.paintlength = paintlength;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNeckwidth() {
        return Neckwidth;
    }

    public void setNeckwidth(String neckwidth) {
        Neckwidth = neckwidth;
    }

    public String getShoulderlength() {
        return shoulderlength;
    }

    public void setShoulderlength(String shoulderlength) {
        this.shoulderlength = shoulderlength;
    }

    public String getArmlength() {
        return armlength;
    }

    public void setArmlength(String armlength) {
        this.armlength = armlength;
    }

    public String getArmwidth() {
        return armwidth;
    }

    public void setArmwidth(String armwidth) {
        this.armwidth = armwidth;
    }

    public String getShirtwidth() {
        return shirtwidth;
    }

    public void setShirtwidth(String shirtwidth) {
        this.shirtwidth = shirtwidth;
    }

    public String getShirtlength() {
        return shirtlength;
    }

    public void setShirtlength(String shirtlength) {
        this.shirtlength = shirtlength;
    }

    public String getPaintlength() {
        return paintlength;
    }

    public void setPaintlength(String paintlength) {
        this.paintlength = paintlength;
    }
}
