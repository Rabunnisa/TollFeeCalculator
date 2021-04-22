package com.toll.refactored.model;

/**
 * This Class is mainly implemented for testing which has basically getters for various attributes.
 */
public class UseCase {
    private String[] dates;
    private Vehicle vehicle;
    private int expected;

    UseCase(Builder builder) {
        this.dates = builder.dates;
        this.vehicle = builder.vehicle;
        this.expected = builder.expected;
    }

    @Override
    public String toString() {
        return String.format("Use case[vehicle=%s, date=%s, expected=%d]", vehicle == null ? null : vehicle.getType(), dates, expected);
    }

    public String[] getDates() {
        return dates;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }


    public int getExpected() {
        return expected;
    }

    public static class Builder {
        private String[] dates;
        private Vehicle vehicle;
        private int expected;

        public Builder date(String... dates) {
            this.dates = dates;
            return this;
        }

        public Builder vehicle(Vehicle vehicle) {
            this.vehicle = vehicle;

            return this;
        }

        public Builder expect(int expected) {
            this.expected = expected;
            return this;
        }

        public UseCase build() {
            return new UseCase(this);
        }
    }
}