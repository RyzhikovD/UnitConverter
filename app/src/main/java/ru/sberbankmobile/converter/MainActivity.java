package ru.sberbankmobile.converter;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private DataProvider mProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProvider = new DataProvider(getResources());
        initRecyclerView();
    }

    @Override
    public void onClick(String itemName) {
        final Intent intent = new Intent(this, ConverterActivity.class);
        intent.putExtra(ConverterActivity.EXTRA_ITEM, itemName);
        startActivity(intent);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        ConverterAdapter converterAdapter = new ConverterAdapter(mProvider.provideUnitNames(), this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(converterAdapter);
    }
}