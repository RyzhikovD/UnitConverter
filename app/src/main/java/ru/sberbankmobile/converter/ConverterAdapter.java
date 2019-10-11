package ru.sberbankmobile.converter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ConverterAdapter extends RecyclerView.Adapter<ConverterAdapter.UnitHolder> {

    private List<String> mUnits;
    private OnItemClickListener mClickListener;

    public ConverterAdapter(List<String> mUnits, OnItemClickListener clickListener) {
        this.mUnits = mUnits;
        this.mClickListener = clickListener;
    }

    @NonNull
    @Override
    public UnitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unit, parent, false);
        return new UnitHolder(view, mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UnitHolder holder, int position) {
        String unitName = mUnits.get(position);
        holder.bindView(unitName);
    }

    @Override
    public int getItemCount() {
        return mUnits == null ? 0 : mUnits.size();
    }

    public static class UnitHolder extends RecyclerView.ViewHolder {
        private final TextView mUnitName;
        private String unitString;

        private UnitHolder(@NonNull View itemView, final OnItemClickListener clickListener) {
            super(itemView);
            mUnitName = itemView.findViewById(R.id.unit_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(unitString);
                }
            });
        }

        private void bindView(final String unitName) {
            mUnitName.setText(unitName);
            unitString = unitName;
        }
    }
}
