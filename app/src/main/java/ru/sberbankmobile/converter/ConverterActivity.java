package ru.sberbankmobile.converter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ConverterActivity extends AppCompatActivity {

    public static final String EXTRA_ITEM = "EXTRA_ITEM_KEY";
    private String unitName;

    private DataProvider mProvider;
    private Spinner toSpinner;
    private Spinner fromSpinner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.converter_activity);
        mProvider = new DataProvider(getResources());
        setUnitName();
        initSpinner();
    }

    private void initSpinner() {
        fromSpinner = findViewById(R.id.from_spinner);
        final List<String> unitNames = mProvider.provideDimensions(Unit.valueOf(unitName));
        fromSpinner.setAdapter(new DimensionSpinnerAdapter(unitNames));

        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String lecturer = lecturers.get(position);
//                List<Lecture> filteredLectures;
//                if (position == POSITION_ALL) {
//                    filteredLectures = mLearningProgramProvider.provideLectures();
//                } else {
//                    filteredLectures = mLearningProgramProvider.filterBy(lecturer);
//                }
//                mAdapter.setLectures(filteredLectures);
//                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setUnitName() {
        Intent intent = getIntent();
        unitName = intent.getStringExtra(EXTRA_ITEM);
    }
}
