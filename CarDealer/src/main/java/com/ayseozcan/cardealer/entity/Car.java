
package com.ayseozcan.cardealer.entity;

public class Car {

    private long id;
    private String brand;
    private String modelYear;
    private String carModel;
    private long delaerShipId;

    public Car() {

    }

    public Car(long id, String brand, String carModel, String modelYear, long delaerShipId) {
        this.id = id;
        this.brand = brand;
        this.modelYear = modelYear;
        this.carModel = carModel;
        this.delaerShipId = delaerShipId;
    }

    public Car(String brand, String carModel, String modelYear, long delaerShipId) {
        this.brand = brand;
        this.modelYear = modelYear;
        this.carModel = carModel;
        this.delaerShipId = delaerShipId;
    }
    public Car(String brand, String carModel, String modelYear ) {
        this.brand = brand;
        this.modelYear = modelYear;
        this.carModel = carModel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public long getDelaerShipId() {
        return delaerShipId;
    }

    public void setDelaerShipId(long delaerShipId) {
        this.delaerShipId = delaerShipId;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", brand=" + brand + ", modelYear=" + modelYear + ", carModel=" + carModel + ", delaerShipId=" + delaerShipId + '}';
    }
    
    

}
