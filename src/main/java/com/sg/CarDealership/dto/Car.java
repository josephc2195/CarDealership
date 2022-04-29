/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.CarDealership.dto;

import java.util.Objects;

/**
 *
 * @author chica
 */
public class Car {
    private int id, year, mileage;
    private String make, model, type, bodyStyle, interior, color, transmission, vin, description, picture;
    private double msrp, salesPrice;
    private boolean available;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
        hash = 79 * hash + this.year;
        hash = 79 * hash + this.mileage;
        hash = 79 * hash + Objects.hashCode(this.make);
        hash = 79 * hash + Objects.hashCode(this.model);
        hash = 79 * hash + Objects.hashCode(this.type);
        hash = 79 * hash + Objects.hashCode(this.bodyStyle);
        hash = 79 * hash + Objects.hashCode(this.interior);
        hash = 79 * hash + Objects.hashCode(this.color);
        hash = 79 * hash + Objects.hashCode(this.transmission);
        hash = 79 * hash + Objects.hashCode(this.vin);
        hash = 79 * hash + Objects.hashCode(this.description);
        hash = 79 * hash + Objects.hashCode(this.picture);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.msrp) ^ (Double.doubleToLongBits(this.msrp) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.salesPrice) ^ (Double.doubleToLongBits(this.salesPrice) >>> 32));
        hash = 79 * hash + (this.available ? 1 : 0);
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
        final Car other = (Car) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        if (this.mileage != other.mileage) {
            return false;
        }
        if (Double.doubleToLongBits(this.msrp) != Double.doubleToLongBits(other.msrp)) {
            return false;
        }
        if (Double.doubleToLongBits(this.salesPrice) != Double.doubleToLongBits(other.salesPrice)) {
            return false;
        }
        if (this.available != other.available) {
            return false;
        }
        if (!Objects.equals(this.make, other.make)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.bodyStyle, other.bodyStyle)) {
            return false;
        }
        if (!Objects.equals(this.interior, other.interior)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (!Objects.equals(this.transmission, other.transmission)) {
            return false;
        }
        if (!Objects.equals(this.vin, other.vin)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return Objects.equals(this.picture, other.picture);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public double getMsrp() {
        return msrp;
    }

    public void setMsrp(double msrp) {
        this.msrp = msrp;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    
}
