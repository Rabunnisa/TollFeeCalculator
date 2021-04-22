package com.toll.refactored.model;

/**
 * This class is used to get a particular vehicle type like car .
 * It also implements a interface called Vehicle .
 */
public class Car implements Vehicle {
    /**
     * This method is used to get the type of vehicle.
     * @return vehicle type ,in this case car.
     */
    public VehicleType getType() {
        return VehicleType.CAR;
    }

}
