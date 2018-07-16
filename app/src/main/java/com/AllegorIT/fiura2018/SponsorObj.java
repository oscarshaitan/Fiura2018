package com.AllegorIT.fiura2018;

import com.google.android.gms.maps.model.LatLng;

public class SponsorObj {

    private int sponsor_img;
    private LatLng pos;
    private String sponsor_name;


    public SponsorObj(int sponsor_img, LatLng pos, String sponsor_name) {
        this.sponsor_img = sponsor_img;
        this.pos = pos;
        this.sponsor_name = sponsor_name;
    }

    public LatLng getPos() {
        return pos;
    }

    public void setPos(LatLng pos) {
        this.pos = pos;
    }

    public String getSponsor_name() {
        return sponsor_name;
    }

    public void setSponsor_name(String sponsor_name) {
        this.sponsor_name = sponsor_name;
    }

    public int getSponsor_img() {
        return sponsor_img;
    }

    public void setSponsor_img(int sponsor_img) {
        this.sponsor_img = sponsor_img;
    }
}
