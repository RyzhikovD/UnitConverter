package ru.sberbankmobile.converter.units;

public enum Unit {

    LENGTH("Длина"),
    MASS("Масса"),
    TEMPERATURE("Температура"),
    VOLUME("Объём");

    private final String mUnitName;

    Unit(String unitName) {
        this.mUnitName = unitName;
    }

    public String getUnitName() {
        return mUnitName;
    }
}
