package com.yize.miniweather.bean;

import java.io.Serializable;

public class CityDetailBean implements Serializable {
    private long id;
    private String currentName;
    private String province;
    private String city;
    private String country;
    private long lastUpdate;
    private String minTemp;
    private String maxTemp;
    private String weatherDesc;
    private String weatherCode;

    public CityDetailBean(String currentName, String province, String city, String country, String minTemp, String maxTemp, String weatherDesc,String weatherCode, long lastUpdate) {
        this.currentName = currentName;
        this.province = province;
        this.city = city;
        this.country = country;
        this.lastUpdate = lastUpdate;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.weatherDesc = weatherDesc;
        this.weatherCode=weatherCode;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(String weatherCode) {
        this.weatherCode = weatherCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    @Override
    public String toString() {
        return "CityDetailBean{" +
                "id=" + id +
                ", currentName='" + currentName + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", minTemp='" + minTemp + '\'' +
                ", maxTemp='" + maxTemp + '\'' +
                ", weatherDesc='" + weatherDesc + '\'' +
                ", weatherCode='" + weatherCode + '\'' +
                '}';
    }
}
