package com.example.massrecord.model;

public class Weight {
    private int id;
    private String date;
    private String weight;

    public Weight(int id, String date, String weight) {
        this.id = id;
        this.date = date;
        this.weight = weight;
    }

    public Weight(String date, String weight) {
        this.date = date;
        this.weight = weight;
    }

    public Weight(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
