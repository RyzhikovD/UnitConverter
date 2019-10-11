package ru.sberbankmobile.converter;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataProvider {

    private Resources mResources;

    public DataProvider(Resources resources) {
        mResources = resources;
    }

    public List<String> provideUnitNames() {
        Unit[] units = Unit.values();
        List<String> unitNames = new ArrayList<>();
        for (Unit unit : units) {
            unitNames.add(unit.getUnitName());
        }
        return unitNames;
    }

    public List<String> provideDimensions(Unit unit) {
        switch (unit) {
            case LENGTH:
                return Arrays.asList(mResources.getStringArray(R.array.length));
            case MASS:
                return Arrays.asList(mResources.getStringArray(R.array.mass));
            case TEMPERATURE:
                return Arrays.asList(mResources.getStringArray(R.array.temperature));
            case VOLUME:
                return Arrays.asList(mResources.getStringArray(R.array.volume));
            default:
                throw new IllegalArgumentException(mResources.getString(R.string.unsupported_unit) + unit.getUnitName());
        }
    }
}
