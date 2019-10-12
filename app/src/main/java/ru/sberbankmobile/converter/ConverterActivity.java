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

public class ConverterActivity extends AppCompatActivity {

    public static final String EXTRA_UNIT = "EXTRA_UNIT";

    private double denominator = 1;
    private double multiplier = 1;

    private Unit mUnit;
    private DimensionsProvider mProvider;

    private Spinner toSpinner;
    private Spinner fromSpinner;

    private List<Dimensions.Dimension> mDimensions = new ArrayList<>();

    private EditText mValue;
    private EditText mConvertedValue;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.converter_activity);

        mProvider = new DimensionsProvider(getResources());
        setUnitName();
        initEditText();
        initSpinners();
    }

    private void setUnitName() {
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
        fromSpinner = findViewById(R.id.from_spinner);
        fromSpinner.setAdapter(spinnerAdapter);

        toSpinner = findViewById(R.id.to_spinner);
        toSpinner.setAdapter(spinnerAdapter);

        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Dimensions.Dimension dimension = mDimensions.get(position);
                if (dimension instanceof Dimensions.Length) {
                    switch ((Dimensions.Length) dimension) {
                        case MILLIMETRE:
                            denominator = 1000;
                            break;
                        case CENTIMETRE:
                            denominator = 100;
                            break;
                        case METRE:
                            denominator = 1;
                            break;
                        case KILOMETRE:
                            denominator = 0.001;
                            break;
                    }
                } else if (dimension instanceof Dimensions.Mass) {
                    switch ((Dimensions.Mass) dimension) {
                        case MILLIGRAM:
                            denominator = 1000000;
                            break;
                        case GRAM:
                            denominator = 1000;
                            break;
                        case KILOGRAM:
                            denominator = 1;
                            break;
                        case TON:
                            denominator = 0.001;
                            break;
                    }
                }
                convert(mValue.getText());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Dimensions.Dimension dimension = mDimensions.get(position);
                if (dimension instanceof Dimensions.Length) {
                    switch ((Dimensions.Length) dimension) {
                        case MILLIMETRE:
                            multiplier = 1000;
                            break;
                        case CENTIMETRE:
                            multiplier = 100;
                            break;
                        case METRE:
                            multiplier = 1;
                            break;
                        case KILOMETRE:
                            multiplier = 0.001;
                            break;
                    }
                } else if (dimension instanceof Dimensions.Mass) {
                    switch ((Dimensions.Mass) dimension) {
                        case MILLIGRAM:
                            multiplier = 1000000;
                            break;
                        case GRAM:
                            multiplier = 1000;
                            break;
                        case KILOGRAM:
                            multiplier = 1;
                            break;
                        case TON:
                            multiplier = 0.001;
                            break;
                    }
                }
                convert(mValue.getText());
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
                convert(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

//        mConvertedValue.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                double result = Double.parseDouble(s.toString()) * multiplier / denominator;
//                mConvertedValue.setText(String.valueOf(result));
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
    }

    private void defineCoefficient(Dimensions.Dimension dimension) {
        if (dimension instanceof Dimensions.Length) {
            switch ((Dimensions.Length) dimension) {
                case MILLIMETRE:
                    multiplier = 1000;
                    break;
                case CENTIMETRE:
                    multiplier = 100;
                    break;
                case METRE:
                    multiplier = 1;
                    break;
                case KILOMETRE:
                    multiplier = 0.001;
                    break;
            }
        } else if (dimension instanceof Dimensions.Mass) {
            switch ((Dimensions.Mass) dimension) {
                case MILLIGRAM:
                    multiplier = 1000000;
                    break;
                case GRAM:
                    multiplier = 1000;
                    break;
                case KILOGRAM:
                    multiplier = 1;
                    break;
                case TON:
                    multiplier = 0.001;
                    break;
            }
        }
    }

    private void convert(CharSequence value) {
        if (!value.toString().isEmpty()) {
            double convertedValue = Double.parseDouble(value.toString()) * multiplier / denominator;
            mConvertedValue.setText(String.valueOf(convertedValue));
        }
    }
}
