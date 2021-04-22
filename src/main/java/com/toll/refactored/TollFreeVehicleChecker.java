package com.toll.refactored;

import com.toll.refactored.model.Vehicle;
import com.toll.refactored.model.VehicleType;
import com.google.common.collect.Sets;

import java.util.Set;

import static com.toll.refactored.model.VehicleType.*;

final class TollFreeVehicleChecker {
    /**
     * This method is used to check if the given vehicle is a toll free vehicle such as diplomat,Emergency etc.
     */
    public TollFreeVehicleChecker() {
    }

    private static final Set<VehicleType> TOLL_FREE_VEHICLES = Sets.newHashSet(
            MOTORBIKE, TRACTOR, EMERGENCY, DIPLOMAT, FOREIGN, MILITARY);

    static boolean isTollFree(Vehicle vehicle) {
        return TOLL_FREE_VEHICLES.contains(vehicle.getType());
    }

}
