package com.sg.CarDealership.models;

import com.sg.CarDealership.dto.CarModel;

/**
 * @author Ronald Gedeon; email: gedemarcel0002@hotmail.com;  
 * gitRepo: https://github.com/gedegithub/C223-JavaDev.git 
 * Design of a class ... on month day, year
 */
public class MakeIdModel {
    private int makeId;
    private CarModel model;

    public int getMakeId() {
        return makeId;
    }

    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }

    public CarModel getModel() {
        return model;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }
    
}
