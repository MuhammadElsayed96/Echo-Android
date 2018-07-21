package com.muhammadelsayed.echo.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.model.Source;
import com.zcw.togglebutton.ToggleButton;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.MyViewHolder> {

    private Context mContext;
    private List<Source> sourcesList;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.source_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final int index = position;
        Source source = sourcesList.get(position);
        holder.SourceTitle.setText(source.getName());
        holder.sourceImage.setImageResource(source.getImageResourceID());

        if (source.isToggleStatus()) {
            holder.sourceToggle.setToggleOn();
        } else {
            holder.sourceToggle.setToggleOff();
        }
        holder.sourceToggle.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
//                SharedPreferences.Editor editor = mContext.getSharedPreferences("SOURCES_SETTINGS", MODE_PRIVATE).edit();
//                editor.putBoolean(sourcesList.get(index).getId(), on);
//                editor.apply();
                sourcesList.get(index).setToggleStatus(on);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sourcesList.size();
    }

    public SourcesAdapter(Context mContext, List<Source> moviesList) {
        this.mContext = mContext;
        this.sourcesList = moviesList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView SourceTitle;
        private ToggleButton sourceToggle;
        private ImageView sourceImage;

        private MyViewHolder(View view) {
            super(view);
            SourceTitle = view.findViewById(R.id.source_name_tv);
            sourceToggle = view.findViewById(R.id.source_toggle);
            sourceImage = view.findViewById(R.id.source_image_iv);
        }
    }
}
