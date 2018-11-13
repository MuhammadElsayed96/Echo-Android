package com.muhammadelsayed.echo.widgets.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.muhammadelsayed.echo.R;

import java.util.List;

public class WidgetNewsAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private List<String> sections;

    public WidgetNewsAdapter(@NonNull Context context, List<String> sections) {
        super(context, 0, sections);
        mContext = context;
        this.sections = sections;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(mContext).inflate(R.layout.widget_configure_list_item, parent, false);

        String currentSection = getItem(position);
        TextView sectionName = convertView.findViewById(R.id.configure_name);

        try {
            sectionName.setText(currentSection);
        } catch (Exception ex) {
            sectionName.setText(mContext.getString(R.string.empty));
        }

        return convertView;
    }
}
