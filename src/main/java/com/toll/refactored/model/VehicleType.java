package com.toll.refactored.model;
/**
 * This enum is used to get a particular vehicle types in which most of them are toll free .
 * Special vehicles such as Emergency,Diplomat ,MILITARY are toll free.
 */
public enum VehicleType {

    CAR("Car"),
    MOTORBIKE("Motorbike"),
    TRACTOR("Tractor"),
    EMERGENCY("Emergency"),
    DIPLOMAT("Diplomat"),
    FOREIGN("Foreign"),
    MILITARY("Military");

    private final String type;

    VehicleType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
