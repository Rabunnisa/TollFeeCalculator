package com.toll.refactored;

import com.toll.refactored.model.Car;
import com.toll.refactored.model.Motorbike;
import com.toll.refactored.model.Vehicle;
import com.toll.refactored.model.VehicleType;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.toll.refactored.TollFreeVehicleChecker.isTollFree;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TollFreeVehicleCheckerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TollFreeVehicleCheckerTest.class);

    /**
     * This Test method is used to validate toll on weekends for a particular vehicle.
     */
    @Test
    public void testToCheckTollOnWeekendsForParticularVehicle() {
        assertFalse(isTollFree(new Car()));
        assertTrue(isTollFree(new Motorbike()));
    }

    /**
     * This Test method is used to check whether toll is free for Special vehicles.
     */

    @Test
    public void testToCheckTollIsFreeForSpecialVehicle() {
        assertFalse(isTollFree(new Vehicle() {
            @Override
            public VehicleType getType() {
                return null;
            }
        }));

    }
}
