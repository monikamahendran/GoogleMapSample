package com.example.googlemapsample;

public class address {
    String latlong;
    String locname;

    public address(int id, String locanme, String latlong) {
        this.latlong = latlong;
        this.locname = locanme;
    }

    public String getLatlong() {
        return latlong;
    }

    public void setLatlong(String latlong) {
        this.latlong = latlong;
    }

    public String getLocname() {
        return locname;
    }
}
