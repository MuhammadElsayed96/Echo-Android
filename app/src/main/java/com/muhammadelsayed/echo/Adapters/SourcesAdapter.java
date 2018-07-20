package com.muhammadelsayed.echo.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.model.Source;
import com.zcw.togglebutton.ToggleButton;

import java.util.List;

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.MyViewHolder> {

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
        Source source = sourcesList.get(position);
        holder.SourceTitle.setText(source.getSourceTitle());
        holder.sourceToggle.setToggleOn();
        holder.sourceImage.setImageResource(source.getImageResourceID());
    }

    @Override
    public int getItemCount() {
        return sourcesList.size();
    }

    public SourcesAdapter(List<Source> moviesList) {
        this.sourcesList = moviesList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView SourceTitle;
        public ToggleButton sourceToggle;
        public CircularImageView sourceImage;

        public MyViewHolder(View view) {
            super(view);
            SourceTitle = (TextView) view.findViewById(R.id.source_name_tv);
            sourceToggle = (ToggleButton) view.findViewById(R.id.source_toggle);
            sourceImage = (CircularImageView) view.findViewById(R.id.source_image_iv);
        }
    }
}
