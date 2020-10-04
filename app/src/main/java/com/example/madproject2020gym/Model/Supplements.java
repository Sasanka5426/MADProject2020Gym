package com.example.madproject2020gym.Model;

public class Supplements {
    String supid, supname, supdes, image;
    int supprice;

    public Supplements(String supid, String supname, int supprice, String supdes, String image) {
        this.supid = supid;
        this.supname = supname;
        this.supprice = supprice;
        this.supdes = supdes;
        this.image = image;
    }

    public Supplements() {
    }

    public String getSupid() {
        return supid;
    }

    public void setSupid(String supid) {
        this.supid = supid;
    }

    public String getSupname() {
        return supname;
    }

    public void setSupname(String supname) {
        this.supname = supname;
    }

    public int getSupprice() {
        return supprice;
    }

    public void setSupprice(int supprice) {
        this.supprice = supprice;
    }

    public String getSupdes() {
        return supdes;
    }

    public void setSupdes(String supdes) {
        this.supdes = supdes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
