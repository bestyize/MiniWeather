package com.yize.miniweather.bean;

import java.io.Serializable;

public class CityShip implements Serializable {
    public String currentName;
    public String province;
    public String city;
    public String country;

    public CityShip(String province, String city, String country) {
        this.province = province;
        this.city = city;
        this.country = country;
    }
    @Override
    public String toString() {
        return "{" +
                "\"province\":\"" + province + "\"" +
                ", \"city\":\"" + city + "\"" +
                ", \"country\":\"" + country + "\"" +
                "}";
    }

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}