package ru.sberbankmobile.converter;

public class Dimensions {

    public interface Dimension {
        String getUnitName();
    }

    public enum Length  implements Dimension{
        MILLIMETRE("Миллиметр"),
        CENTIMETRE("Сантиметр"),
        METRE("Метр"),
        KILOMETRE("Километр");

        private final String mUnitName;

        Length(String unitName) {
            this.mUnitName = unitName;
        }

        public String getUnitName() {
            return mUnitName;
        }
    }

    public enum Mass implements Dimension{
        MILLIGRAM("Миллиграмм"),
        GRAM("Грамм"),
        KILOGRAM("Килограмм"),
        TON("Тонна");

        private final String mUnitName;

        Mass(String unitName) {
            this.mUnitName = unitName;
        }

        public String getUnitName() {
            return mUnitName;
        }
    }
}
