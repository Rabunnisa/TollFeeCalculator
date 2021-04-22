package com.toll.refactored;

import com.toll.refactored.model.Vehicle;
import com.google.common.base.Preconditions;

import java.time.*;


public final class RefactoredTollCalculator {

    private static final int DAY_MAXIMUM_TOTAL_FEE = 60;

    private RefactoredTollCalculator() {
    }

    /**
     * Calculate the total toll fee for one day
     *
     * @param vehicle - the vehicle
     * @param dates   - date and time of all passes on one day
     * @return - the  toll fee for that day
     */
    public static int calculate(Vehicle vehicle, LocalDateTime... dates) {

        Preconditions.checkArgument(vehicle != null, "vehicle cannot be null");
        Preconditions.checkArgument(dates != null, "dates cannot be null - at least one needs to be specified");
        Preconditions.checkArgument(isOnSameDay(dates), "dates must be for the same day");

        return calculateAll(vehicle, dates);
    }

    /**
     * Calculate the total toll fee for one vehicle for that day.
     *
     * @param vehicle - the vehicle
     * @param dates   - date and time of all passes on one day
     * @return - the total toll fee for that day
     */
    private static int calculateAll(Vehicle vehicle, LocalDateTime... dates) {
        LocalDateTime intervalStart = dates[0];
        int feeFirstTrip = calculateOne(vehicle, intervalStart);

        int total = 0;
        for (LocalDateTime date : dates) {
            int feeThisTrip = calculateOne(vehicle, date);
            long minutesSinceFirstTrip = Duration.between(intervalStart, date).toMinutes();
            if (minutesSinceFirstTrip <= 60) {
                if (total > 0) {
                    total = total - feeFirstTrip;
                }
                total = total + Math.max(feeFirstTrip, feeThisTrip);
            } else {
                total = total + feeThisTrip;
            }
        }
        return total > DAY_MAXIMUM_TOTAL_FEE ? DAY_MAXIMUM_TOTAL_FEE : total;
    }

    /**
     * This method is used to check if the vehicle is toll free and
     * also if the day/date is free of toll,referring to range
     *
     * @param vehicle - the vehicle
     * @param date    - date and time of all passes on one day
     * @return - the total toll fee for that day
     */
    private static int calculateOne(Vehicle vehicle, LocalDateTime date) {
        if (TollFreeVehicleChecker.isTollFree(vehicle) || TollFreeDateChecker.isTollFree(date.toLocalDate())) {
            return 0;
        }
        return TimeRangeFeeCalculator.calculate(date.toLocalTime());
    }

    /**
     * This method is used to check the total toll on the vehicle for the same day .
     *
     * @param dates - date and time of all passes on one day
     * @return - the total toll fee for that day
     */
    static boolean isOnSameDay(LocalDateTime... dates) {
        if (dates.length == 1) return true;

        LocalDate day = dates[0].toLocalDate();
        for (LocalDateTime date : dates) {
            Preconditions.checkArgument(date != null, "date cannot be null");
            if (!day.isEqual(date.toLocalDate())) return false;
        }
        return true;
    }


}
