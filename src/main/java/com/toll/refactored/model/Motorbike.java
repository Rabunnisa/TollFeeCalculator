package com.toll.refactored.model;

import static com.toll.refactored.model.VehicleType.MOTORBIKE;
/**
 * This class is used to get a particular vehicle type in this case Motorbike.
 * It also implements a interface called vehicle .
 */
public class Motorbike implements Vehicle {
    /**
     * This method is used to get the type of vehicle.
     * @return vehicle type ,in this case motorbike.
     */
    public VehicleType getType() {
        return MOTORBIKE;
    }

}
