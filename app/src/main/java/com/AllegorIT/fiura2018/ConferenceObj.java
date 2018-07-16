package com.AllegorIT.fiura2018;

import android.widget.ImageView;

import com.google.android.gms.maps.model.LatLng;

public class ConferenceObj {
    private int speakerImg;
    private String title, descrip, place, schedule, speakerName, rol;
    private LatLng latLng;

    public ConferenceObj(int speakerImg, String title, String descrip, String place, String schedule, String speakerName, String rol, LatLng latLng) {
        this.speakerImg = speakerImg;
        this.title = title;
        this.descrip = descrip;
        this.place = place;
        this.schedule = schedule;
        this.speakerName = speakerName;
        this.latLng = latLng;
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getSpeakerImg() {
        return speakerImg;
    }

    public void setSpeakerImg(int speakerImg) {
        this.speakerImg = speakerImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
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

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
