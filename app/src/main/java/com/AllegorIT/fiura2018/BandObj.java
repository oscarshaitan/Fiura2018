package com.AllegorIT.fiura2018;

import com.google.android.gms.maps.model.LatLng;

public class BandObj {
    private int bandImg;
    private String background, country, gender, place, schedule, bandName;
    private LatLng latLng;

    public BandObj(int bandImg, String background, String country, String gender, String place, String schedule, String bandName, LatLng latLng) {
        this.bandImg = bandImg;
        this.background = background;
        this.country = country;
        this.gender = gender;
        this.place = place;
        this.schedule = schedule;
        this.bandName = bandName;
        this.latLng = latLng;
    }

    public int getBandImg() {
        return bandImg;
    }

    public void setBandImg(int bandImg) {
        this.bandImg = bandImg;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
