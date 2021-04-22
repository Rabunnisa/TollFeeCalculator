package com.toll.refactored.model;

/**
 * This interface  is implemented by other classes such as Car and Motorbike to get the particular vehicle type.
 */
public interface Vehicle {

    VehicleType getType();
}
