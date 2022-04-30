package com.sg.CarDealership.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Ronald Gedeon; email: gedemarcel0002@hotmail.com;  
 * gitRepo: https://github.com/gedegithub/C223-JavaDev.git 
 * Design of a class ... on month day, year
 */
public class Sales {
    private int id;
    private double purchasePrice;
    private String purchaseType;
    private LocalDate purchaseDate;
    private Car car;
    private Customer customer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.purchasePrice) ^ (Double.doubleToLongBits(this.purchasePrice) >>> 32));
        hash = 47 * hash + Objects.hashCode(this.purchaseType);
        hash = 47 * hash + Objects.hashCode(this.purchaseDate);
        hash = 47 * hash + Objects.hashCode(this.car);
        hash = 47 * hash + Objects.hashCode(this.customer);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sales other = (Sales) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.purchasePrice) != Double.doubleToLongBits(other.purchasePrice)) {
            return false;
        }
        if (!Objects.equals(this.purchaseType, other.purchaseType)) {
            return false;
        }
        if (!Objects.equals(this.purchaseDate, other.purchaseDate)) {
            return false;
        }
        if (!Objects.equals(this.car, other.car)) {
            return false;
        }
        return Objects.equals(this.customer, other.customer);
    }
    
}
