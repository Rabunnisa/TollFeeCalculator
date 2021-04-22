package com.toll.refactored;

import com.toll.refactored.model.Motorbike;
import com.google.common.collect.Lists;
import com.toll.refactored.model.Car;
import com.toll.refactored.model.UseCase;

import static org.junit.Assert.*;

import org.junit.Test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TollCalculatorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TollCalculatorTest.class);

    /**
     * This test method is used to if the Date / times provided is for a single or same day
     */
    @Test
    public void testToCheckArgumentDatesOnSameDaySuccess() {
        assertTrue(RefactoredTollCalculator.isOnSameDay(
                toLocalDateTime("2021-10-17T00:00:00"),
                toLocalDateTime("2021-10-17T12:00:00"),
                toLocalDateTime("2021-10-17T23:59:59")
        ));
    }

    /**
     * This test method is used to if one date is provided
     */
    @Test
    public void testToCheckArgumentOneDateSuccess() {
        assertTrue(RefactoredTollCalculator.isOnSameDay(
                toLocalDateTime("2021-10-17T12:30:00")
        ));
    }

    /**
     * This test method validates whether the dates provided are different - Negative case
     */
    @Test(expected = IllegalArgumentException.class)
    public void testToCheckArgumentDatesOnSameDayFailure() {
        RefactoredTollCalculator.calculate(
                new Motorbike(),
                toLocalDateTime("2021-04-21T23:59:01"),
                toLocalDateTime("2021-04-23T00:00:00")
        );
        fail("should not have reached this point");
    }

    /**
     * This test method is used to check and validate the toll charges at a specific time .
     */

    @Test
    public void testToCheckTollfeeForVehiclesAtASpecificTime() {
        Car car = new Car();

        // Test simple intervals
        List<UseCase> simpleCases = Lists.newArrayList(
                new UseCase.Builder().vehicle(car).date("2021-05-18T02:59:59").expect(0).build(),
                new UseCase.Builder().vehicle(car).date("2021-05-08T06:00:00").expect(0).build(),
                new UseCase.Builder().vehicle(car).date("2021-05-18T06:15:01").expect(9).build(),
                new UseCase.Builder().vehicle(car).date("2021-05-18T06:29:59").expect(9).build(),
                new UseCase.Builder().vehicle(car).date("2021-05-18T06:30:00").expect(16).build(),
                new UseCase.Builder().vehicle(car).date("2021-05-13T06:58:59").expect(0).build(),
                new UseCase.Builder().vehicle(car).date("2021-05-18T06:59:00").expect(16).build(),
                new UseCase.Builder().vehicle(car).date("2021-05-18T06:59:59").expect(16).build(),
                new UseCase.Builder().vehicle(car).date("2021-05-18T07:00:00").expect(22).build(),
                new UseCase.Builder().vehicle(car).date("2021-05-18T07:00:01").expect(22).build(),
                new UseCase.Builder().vehicle(car).date("2021-05-18T08:28:59").expect(16).build(),
                new UseCase.Builder().vehicle(car).date("2021-05-18T08:29:00").expect(16).build(),
                new UseCase.Builder().vehicle(car).date("2021-05-18T08:29:01").expect(16).build(),
                new UseCase.Builder().vehicle(car).date("2021-05-18T08:29:59").expect(16).build(),
                new UseCase.Builder().vehicle(car).date("2021-05-18T08:30:00").expect(9).build(),
                new UseCase.Builder().vehicle(car).date("2021-05-18T08:30:01").expect(9).build(),
                new UseCase.Builder().vehicle(car).date("2021-05-18T12:31:00").expect(9).build()
        );

        for (UseCase useCase : simpleCases) {


            int feeNew = RefactoredTollCalculator.calculate(useCase.getVehicle(), toLocalDateTimes(useCase.getDates()));

            LOGGER.info(String.format("%s,  feeNew=%d", useCase, feeNew));


            assertEquals(useCase.getExpected(), feeNew);
        }
    }

    /**
     * This test method is used to check and validate toll charges for a
     * particular vehicle(multiple trips on same day).
     */

    @Test
    public void testMultipleTrips() {

        testMulipleTripsOnSameDay(
                0,
                new String[]{
                        "2021-05-17T05:59:59",
                        "2021-05-17T18:31:00",

                });

        testMulipleTripsOnSameDay(
                16,
                new String[]{
                        "2021-05-17T17:50:00",
                        "2021-05-17T18:10:00",
                        "2021-05-17T18:10:01",
                        "2021-05-17T18:10:02"
                });

        testMulipleTripsOnSameDay(
                31,
                new String[]{
                        "2021-05-17T16:10:00",
                        "2021-05-17T17:05:00",
                        "2021-05-17T18:25:00"
                });

        testMulipleTripsOnSameDay(
                25,
                new String[]{
                        "2021-05-17T09:30:00",
                        "2021-05-17T15:01:00"
                });

        testMulipleTripsOnSameDay(
                31,
                new String[]{
                        "2021-05-17T06:01:00",
                        "2021-05-17T06:29:00",
                        "2021-05-17T07:57:00"
                });

        testMulipleTripsOnSameDay(
                60,
                new String[]{

                        "2021-05-17T07:10:00",
                        "2021-05-17T10:30:00",
                        "2021-05-17T13:30:00",
                        "2021-05-17T16:45:00"
                });

        testMulipleTripsOnSameDay(
                32,
                new String[]{
                        "2021-05-17T05:59:00",
                        "2021-05-17T08:01:00",
                        "2021-05-17T15:29:00",
                        "2021-05-17T18:59:00"
                });


    }

    private void testMulipleTripsOnSameDay(int expected, String... dates) {

        int fee = RefactoredTollCalculator.calculate(new Car(), toLocalDateTimes(dates));
        LOGGER.info("Fee={}, dates={}", fee, dates);
        assertEquals(expected, fee);
    }

    private LocalDateTime toLocalDateTime(String date) {
        return LocalDateTime.parse(date);
    }

    private LocalDateTime[] toLocalDateTimes(String... dates) {
        List<LocalDateTime> results = new ArrayList<>();
        for (String date : dates) {
            results.add(LocalDateTime.parse(date));
        }
        return results.toArray(new LocalDateTime[results.size()]);
    }

    private Date[] toDates(String... dates) {
        List<Date> results = new ArrayList<>();
        for (String date : dates) {
            LocalDateTime dateTime = LocalDateTime.parse(date);
            results.add(Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()));
        }
        return results.toArray(new Date[results.size()]);
    }

}
