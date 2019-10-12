package ru.sberbankmobile.converter;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

import ru.sberbankmobile.converter.adapters.ConverterAdapter;
import ru.sberbankmobile.converter.units.Unit;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
    }

    @Override
    public void onClick(Unit unit) {
        final Intent intent = new Intent(this, ConverterActivity.class);
        intent.putExtra(ConverterActivity.EXTRA_UNIT, unit);
        startActivity(intent);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        ConverterAdapter converterAdapter = new ConverterAdapter(Arrays.asList(Unit.values()), this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(converterAdapter);
    }
}