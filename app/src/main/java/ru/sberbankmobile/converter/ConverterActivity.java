package ru.sberbankmobile.converter;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.sberbankmobile.converter.adapters.DimensionSpinnerAdapter;
import ru.sberbankmobile.converter.providers.DimensionsProvider;
import ru.sberbankmobile.converter.units.Dimensions;
import ru.sberbankmobile.converter.units.Unit;

public class ConverterActivity extends AppCompatActivity {

    public static final String EXTRA_UNIT = "EXTRA_UNIT";

    private boolean mHasValueChangedManually = true;

    private double mDenominator = 1;
    private double mMultiplier = 1;

    private Unit mUnit;
    private DimensionsProvider mProvider;

    private List<Dimensions.Dimension> mDimensions = new ArrayList<>();

    private EditText mValue;
    private EditText mConvertedValue;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.converter_activity);

        mProvider = new DimensionsProvider(getResources());

        defineUnitName();
        initEditText();
        initSpinners();
    }

    private void defineUnitName() {
        Intent intent = getIntent();
        mUnit = (Unit) intent.getSerializableExtra(EXTRA_UNIT);
        if (mUnit != null) {
            switch (mUnit) {
                case MASS:
                    mDimensions.addAll(Arrays.asList(Dimensions.Mass.values()));
                    break;
                case LENGTH:
                    mDimensions.addAll(Arrays.asList(Dimensions.Length.values()));
                    break;
            }
        } else {
            throw new IllegalStateException(getResources().getString(R.string.unsupported_unit) + mUnit);
        }
    }

    private void initSpinners() {
        final List<String> unitDimensions = mProvider.provideDimensions(mUnit);
        final DimensionSpinnerAdapter spinnerAdapter = new DimensionSpinnerAdapter(unitDimensions);
        Spinner fromSpinner = findViewById(R.id.from_spinner);
        fromSpinner.setAdapter(spinnerAdapter);

        Spinner toSpinner = findViewById(R.id.to_spinner);
        toSpinner.setAdapter(spinnerAdapter);

        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Dimensions.Dimension dimension = mDimensions.get(position);
                mDenominator = calculateCoefficient(dimension);
                convert(mValue.getText(), false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Dimensions.Dimension dimension = mDimensions.get(position);
                mMultiplier = calculateCoefficient(dimension);
                convert(mValue.getText(), false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void initEditText() {
        mValue = findViewById(R.id.value);
        mConvertedValue = findViewById(R.id.converted_value);

        mValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (mHasValueChangedManually) {
                    convert(charSequence, false);
                }
                mHasValueChangedManually = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        mConvertedValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mHasValueChangedManually) {
                    convert(s, true);
                }
                mHasValueChangedManually = true;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private double calculateCoefficient(Dimensions.Dimension dimension) {
        if (dimension instanceof Dimensions.Length) {
            switch ((Dimensions.Length) dimension) {
                case MILLIMETRE:
                    return Dimensions.Length.MILLIMETRE.getUnitCoefficient();
                case CENTIMETRE:
                    return Dimensions.Length.CENTIMETRE.getUnitCoefficient();
                case METRE:
                    return Dimensions.Length.METRE.getUnitCoefficient();
                case KILOMETRE:
                    return Dimensions.Length.KILOMETRE.getUnitCoefficient();
            }
        } else if (dimension instanceof Dimensions.Mass) {
            switch ((Dimensions.Mass) dimension) {
                case MILLIGRAM:
                    return Dimensions.Mass.MILLIGRAM.getUnitCoefficient();
                case GRAM:
                    return Dimensions.Mass.GRAM.getUnitCoefficient();
                case KILOGRAM:
                    return Dimensions.Mass.KILOGRAM.getUnitCoefficient();
                case TON:
                    return Dimensions.Mass.TON.getUnitCoefficient();
            }
        }
        throw new IllegalStateException(getResources().getString(R.string.unsupported_unit) + mUnit);
    }

    private void convert(CharSequence value, boolean isConvertedValue) {
        if (!value.toString().isEmpty()) {
            double convertedValue = Double.parseDouble(value.toString()) * mMultiplier / mDenominator;
            if (isConvertedValue) {
                mHasValueChangedManually = false;
                mValue.setText(String.valueOf(convertedValue));
            } else {
                mHasValueChangedManually = false;
                mConvertedValue.setText(String.valueOf(convertedValue));
            }
        }
    }
}
