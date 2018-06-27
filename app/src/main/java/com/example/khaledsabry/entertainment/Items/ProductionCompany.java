package com.example.khaledsabry.entertainment.Items;

/**
 * Created by KhALeD SaBrY on 27-Jun-18.
 */

public class ProductionCompany {

    private int id;
    private String companyImage;
    private String name;
    private String country;

    public ProductionCompany(int id, String companyImage, String name, String country) {
        this.id = id;
        this.companyImage = companyImage;
        this.name = name;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getCompanyImage() {
        return companyImage;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
}
