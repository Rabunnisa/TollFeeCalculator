package com.toll.refactored;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;

public class TollFreeDateCheckerTest {
/**
 * This Test method is used to check whether toll is free on weekend .
 */

    @Test
    public void testToCheckIfTollIsFreeOnWeekends() {
        /**
         * weekends
         */

        assertTrue(TollFreeDateChecker.isTollFree(toDate("2021-05-08")));
        assertTrue(TollFreeDateChecker.isTollFree(toDate("2021-05-15")));
        assertTrue(TollFreeDateChecker.isTollFree(toDate("2021-05-22")));
        assertTrue(TollFreeDateChecker.isTollFree(toDate("2021-05-29")));

        /**
         * Non weekends
         */
        assertFalse(TollFreeDateChecker.isTollFree(toDate("2021-10-18")));
        assertFalse(TollFreeDateChecker.isTollFree(toDate("2021-10-19")));
        assertFalse(TollFreeDateChecker.isTollFree(toDate("2021-10-20")));
        assertFalse(TollFreeDateChecker.isTollFree(toDate("2021-10-21")));
        assertFalse(TollFreeDateChecker.isTollFree(toDate("2021-10-22")));
    }

    /**
     * This Test method is used to check whether toll is free for july 2021.
     */

    @Test
    public void testToValidateTollFreeMonthForAParticularYear() {
        // Different dates in 2021/07
        assertTrue(TollFreeDateChecker.isTollFree(toDate("2021-07-01")));
        assertTrue(TollFreeDateChecker.isTollFree(toDate("2021-07-15")));
        assertTrue(TollFreeDateChecker.isTollFree(toDate("2021-07-31")));

        /**
         * Wrong year, Same date.
         */


        assertFalse(TollFreeDateChecker.isTollFree(toDate("2016-07-01")));
        assertFalse(TollFreeDateChecker.isTollFree(toDate("2014-07-01")));
        assertFalse(TollFreeDateChecker.isTollFree(toDate("2020-07-01")));
        assertFalse(TollFreeDateChecker.isTollFree(toDate("2019-07-01")));

        /**
         * Wrong year ,different date.
         */
        assertFalse(TollFreeDateChecker.isTollFree(toDate("2011-07-15")));
        assertFalse(TollFreeDateChecker.isTollFree(toDate("2012-07-16")));
        assertFalse(TollFreeDateChecker.isTollFree(toDate("2014-07-15")));
        assertFalse(TollFreeDateChecker.isTollFree(toDate("2015-07-15")));
        assertFalse(TollFreeDateChecker.isTollFree(toDate("2016-07-15")));
    }

    /**
     * This Test method is used to check whether toll is free for Holidays(Röd dagar).
     */
    @Test
    public void testToCheckTollForSpecificDays() {
        assertTrue(TollFreeDateChecker.isTollFree(toDate("2021-04-01")));
        assertTrue(TollFreeDateChecker.isTollFree(toDate("2021-04-05")));
        assertTrue(TollFreeDateChecker.isTollFree(toDate("2021-05-13")));

        /**
         * Wrong day ,not a weekend
         */
        assertFalse(TollFreeDateChecker.isTollFree(toDate("2021-10-22")));
        assertFalse(TollFreeDateChecker.isTollFree(toDate("2021-11-08")));
        assertFalse(TollFreeDateChecker.isTollFree(toDate("2021-12-23")));

        /**
         * Wrong year
         */
        assertFalse(TollFreeDateChecker.isTollFree(toDate("2018-03-28")));
        assertFalse(TollFreeDateChecker.isTollFree(toDate("2019-11-05")));
        assertFalse(TollFreeDateChecker.isTollFree(toDate("2020-12-25")));
    }

    private LocalDate toDate(String date) {
        return LocalDate.parse(date);
    }

}
