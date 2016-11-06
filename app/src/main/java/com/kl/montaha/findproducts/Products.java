package com.kl.montaha.findproducts;

/**
 * Created by user on 10/27/2016.
 */
public class Products {
    private String name;
    private String code;
    private String place;

    public Products(String name, String code, String place) {
        this.name = name;
        this.code = code;
        this.place = place;
    }

    public Products() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Products{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", place='" + place + '\'' +
                '}';
    }
}
