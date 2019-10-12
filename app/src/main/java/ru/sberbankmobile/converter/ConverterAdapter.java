package ru.sberbankmobile.converter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ConverterAdapter extends RecyclerView.Adapter<ConverterAdapter.UnitHolder> {

    private List<Unit> mUnits;
    private OnItemClickListener mClickListener;

    public ConverterAdapter(List<Unit> units, OnItemClickListener clickListener) {
        mUnits = units;
        mClickListener = clickListener;
    }

    @NonNull
    @Override
    public UnitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unit, parent, false);
        return new UnitHolder(view, mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UnitHolder holder, int position) {
        Unit unit = mUnits.get(position);
        holder.bindView(unit);
    }

    @Override
    public int getItemCount() {
        return mUnits == null ? 0 : mUnits.size();
    }

    public static class UnitHolder extends RecyclerView.ViewHolder {
        private final TextView mUnitNameView;
        private Unit mUnit;

        private UnitHolder(@NonNull View itemView, final OnItemClickListener clickListener) {
            super(itemView);
            mUnitNameView = itemView.findViewById(R.id.unit_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(mUnit);
                }
            });
        }

        private void bindView(final Unit unit) {
            mUnit = unit;
            mUnitNameView.setText(mUnit.getUnitName());
        }
    }
}
