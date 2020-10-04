package com.example.madproject2020gym;

public class Supplements {

    private String supname, supdes ,supprice , image ,supid ;

    public Supplements()
    {

    }

    public Supplements(String supname, String supdes, String supprice, String image, String supid) {
        this.supname = supname;
        this.supdes = supdes;
        this.supprice = supprice;
        this.image = image;
        this.supid = supid;
    }

    public String getSupname() {
        return supname;
    }

    public void setSupname(String supname) {
        this.supname = supname;
    }

    public String getSupdes() {
        return supdes;
    }

    public void setSupdes(String supdes) {
        this.supdes = supdes;
    }

    public String getSupprice() {
        return supprice;
    }

    public void setSupprice(String supprice) {
        this.supprice = supprice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSupid() {
        return supid;
    }

    public void setSupid(String supid) {
        this.supid = supid;
    }
}
