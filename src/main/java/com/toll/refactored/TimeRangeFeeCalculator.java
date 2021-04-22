package com.toll.refactored;

import com.google.common.collect.Range;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

final class TimeRangeFeeCalculator {

    /**
     * This method is used to pass the toll rates for a particular time interval/range.
     * Currency is assumed to be SEK
     */


    // Ideally make immutable and pull rules from file or so, e.g. json - this is much likely config and could change
    private static final Map<Range<LocalTime>, Integer> RANGES = new LinkedHashMap<>();

    static {

        RANGES.put(timeRange("06:00", "06:30"), 9);
        RANGES.put(timeRange("06:30", "07:00"), 16);
        RANGES.put(timeRange("07:00", "08:00"), 22);
        RANGES.put(timeRange("08:00", "08:30"), 16);
        RANGES.put(timeRange("08:30", "15:00"), 9);
        RANGES.put(timeRange("15:00", "15:30"), 16);
        RANGES.put(timeRange("15:30", "17:00"), 22);
        RANGES.put(timeRange("17:00", "18:00"), 16);
        RANGES.put(timeRange("18:00", "18:30"), 9);

    }

    /**
     * This method is used to get the time interval between higher and lower range .
     */
    private static final Range<LocalTime> timeRange(String lower, String upper) {

        return Range.closedOpen(LocalTime.parse(lower), LocalTime.parse(upper));
    }

    private TimeRangeFeeCalculator() {
    }

    /**
     * This method is used to calculate/set the amount of toll on particular time range.
     */
    public static int calculate(LocalTime localTime) {
        for (Map.Entry<Range<LocalTime>, Integer> range : RANGES.entrySet()) {
            if (range.getKey().contains(localTime)) {
                return range.getValue();
            }
        }

        return 0;
    }

}
