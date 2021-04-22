package com.toll.refactored;

import com.google.common.collect.Sets;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Set;

final class TollFreeDateChecker {
    /**
     * This method is used to ensure that there should not be any toll
     * for RED days  (Röd dagar) in Göteborg
     */
    // Have hardcoded Röd dagar also here ,Can be implemented if there is an API available.
    private static final Set<LocalDate> WHITELIST_FREE_DAYS = Sets.newHashSet(
            LocalDate.of(2021, 1, 1),
            LocalDate.of(2021, 1, 5),
            LocalDate.of(2021, 1, 6),
            LocalDate.of(2021, 4, 1),
            LocalDate.of(2021, 4, 2),
            LocalDate.of(2021, 4, 5),
            LocalDate.of(2021, 4, 30),
            LocalDate.of(2021, 5, 12),
            LocalDate.of(2021, 5, 13),
            LocalDate.of(2021, 6, 25),
            LocalDate.of(2021, 11, 5),
            LocalDate.of(2021, 12, 24),
            LocalDate.of(2021, 12, 31)
    );

    /**
     * This method is used to ensure that there should not be any toll
     * for Free month - in this case the value is Jul-2021
     */
    private static final Set<YearMonth> WHITELIST_FREE_YEAR_MONTHS = Sets.newHashSet(
            YearMonth.of(2021, 7));

    private TollFreeDateChecker() {
    }

    /**
     * These methods  (isTollFree,isWeekend,isWhitelistedFree) are  used to check whether the
     * given date is weekend or listed as holiday.
     *
     * @param date it supplies all times of passes on that particular single day
     */
    static boolean isTollFree(LocalDate date) {
        return isWeekend(date) || isWhitelistedFree(date);
    }

    private static boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private static boolean isWhitelistedFree(LocalDate date) {
        return WHITELIST_FREE_DAYS.contains(date) || WHITELIST_FREE_YEAR_MONTHS.contains(YearMonth.from(date));
    }

}
