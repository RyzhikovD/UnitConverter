package ru.sberbankmobile.converter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class DimensionSpinnerAdapter extends BaseAdapter {
    private final List<String> mDimensions;

    public DimensionSpinnerAdapter(@NonNull List<String> dimensions) {
        mDimensions = dimensions;
    }

    @Override
    public int getCount() {
        return mDimensions.size();
    }

    @Override
    public String getItem(int position) {
        return mDimensions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            ViewHolder viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.mDimension.setText(getItem(position));
        return convertView;
    }

    private class ViewHolder {
        private final TextView mDimension;

        private ViewHolder(View view) {
            mDimension = view.findViewById(android.R.id.text1);
        }
    }
}
