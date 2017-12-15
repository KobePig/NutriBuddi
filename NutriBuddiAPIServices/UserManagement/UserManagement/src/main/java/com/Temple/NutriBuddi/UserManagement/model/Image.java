package com.Temple.NutriBuddi.UserManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String foodName;

    private String fileName;

    @Column(name = "transaction_date", columnDefinition="DATETIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    private double latitude;

    private double longitude;

    @JsonBackReference
    @OneToOne(mappedBy = "image")
    private Eats eats;

    public Image(){}

    public Image(String foodName, String fileName) {
        this.foodName = foodName;
        this.fileName = fileName;
        this.transactionDate = new Date();
    }

    public Image(String foodName, String fileName, Eats eats) {
        this.foodName = foodName;
        this.fileName = fileName;
        this.eats = eats;
        this.transactionDate = new Date();
    }

    public Image(String foodName, String fileName, double latitude, double longitude) {
        this.foodName = foodName;
        this.fileName = fileName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.transactionDate = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Eats getEats() {
        return eats;
    }

    public void setEats(Eats eats) {
        this.eats = eats;
    }
}
