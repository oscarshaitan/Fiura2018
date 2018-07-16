package com.AllegorIT.fiura2018;

import com.google.android.gms.maps.model.LatLng;

public class OffersObj {
    private int offers_img, offers_sponsor;
    private LatLng pos;
    private String sponsor_name;


    public OffersObj(int offers_img, int offers_sponsor, LatLng pos, String sponsor_name) {
        this.offers_img = offers_img;
        this.offers_sponsor = offers_sponsor;
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

    public int getOffers_img() {
        return offers_img;
    }

    public void setOffers_img(int offers_img) {
        this.offers_img = offers_img;
    }

    public int getOffers_sponsor() {
        return offers_sponsor;
    }

    public void setOffers_sponsor(int offers_sponsor) {
        this.offers_sponsor = offers_sponsor;
    }
}
