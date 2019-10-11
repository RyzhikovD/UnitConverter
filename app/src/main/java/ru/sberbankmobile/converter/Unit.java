package ru.sberbankmobile.converter;

import androidx.annotation.NonNull;

public enum Unit {

    LENGTH("Длина"),
    MASS("Масса"),
    TEMPERATURE("Температура"),
    VOLUME("Объём");

    private final String mUnitName;

    Unit(@NonNull String unitName) {
        this.mUnitName = unitName;
    }

    public String getUnitName() {
        return mUnitName;
    }
}
