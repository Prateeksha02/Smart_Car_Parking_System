package com.bnmit.smartparking;

public class Book {
    String accessId, places;

    public Book(String accessId) {
        this.accessId = accessId;
    }

    public Book(String accessId, String places) {
        this.accessId = accessId;
        this.places = places;
    }

    public Book() {

    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }
}
