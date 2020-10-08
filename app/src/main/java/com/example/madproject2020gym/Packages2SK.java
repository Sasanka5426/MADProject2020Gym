package com.example.madproject2020gym;

public class Packages2SK {
    private String pkgName, description;

    public Packages2SK() {
    }

    public Packages2SK(String pkgName, String description) {
        this.pkgName = pkgName;
        this.description = description;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
