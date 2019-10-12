package ru.sberbankmobile.converter.units;

public class Dimensions {

    public interface Dimension {
        double getUnitCoefficient();
    }

    public enum Length implements Dimension {
        MILLIMETRE(1000),
        CENTIMETRE(100),
        METRE(1),
        KILOMETRE(0.001);

        private final double mCoefficient;

        Length(double coefficient) {
            mCoefficient = coefficient;
        }

        public double getUnitCoefficient() {
            return mCoefficient;
        }
    }

    public enum Mass implements Dimension {
        MILLIGRAM(1000000),
        GRAM(1000),
        KILOGRAM(1),
        TON(0.001);

        private final double mCoefficient;

        Mass(double coefficient) {
            mCoefficient = coefficient;
        }

        public double getUnitCoefficient() {
            return mCoefficient;
        }
    }
}
