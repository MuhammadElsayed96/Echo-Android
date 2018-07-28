package com.muhammadelsayed.echo.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.model.Source;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.MyViewHolder> {
  private static final String TAG = SourcesAdapter.class.getSimpleName();
  private Context mContext;
  private List<Source> sourcesList;
  SharedPreferences sharedPref;

  public SourcesAdapter(Context mContext, List<Source> sourcesList) {
    this.mContext = mContext;
    this.sourcesList = sourcesList;

    sharedPref = mContext.getSharedPreferences("SOURCES_SETTINGS", Context.MODE_PRIVATE);
    Log.d(TAG, "onBindViewHolder: SOURCES LIST = " + sourcesList);
    Log.d(TAG, "onBindViewHolder: SHARED PREF = " + sharedPref);
  }

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    Log.wtf(TAG, "onCreateViewHolder(): has been instantiated");

    View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.source_list_row, parent, false);
    return new MyViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    Log.wtf(TAG, "onBindViewHolder(): has been instantiated");

    Source source = sourcesList.get(position);
    holder.SourceTitle.setText(source.getName());
    if (source.getImageResourceID() != 0)
        Picasso.get().load(source.getImageResourceID()).fit().centerCrop().into(holder.sourceImage);
    //    holder.sourceImage.setImageResource(source.getImageResourceID());


    sourcesList.get(position).setToggleStatus(sharedPref.getBoolean(sourcesList.get(position).getId(), true));

    holder.sourceToggle.setChecked(sourcesList.get(position).isToggleStatus());

    final int index = position;
    holder.sourceToggle.setOnCheckedChangeListener(
        new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            sourcesList.get(index).setToggleStatus(b);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(
                sourcesList.get(index).getId(), sourcesList.get(index).isToggleStatus());
            editor.apply();
            Log.d(TAG, "onCheckedChanged: " + sourcesList.get(index).getName() + " STATUS = " + b);
          }
        });
  }
  @Override
  public int getItemViewType(int position) {
    return position;
  }
  @Override
  public int getItemCount() {
    return sourcesList.size();
  }

  class MyViewHolder extends RecyclerView.ViewHolder {
    private TextView SourceTitle;
    private Switch sourceToggle;
    private ImageView sourceImage;

    private MyViewHolder(View view) {
      super(view);
      SourceTitle = view.findViewById(R.id.source_name_tv);
      sourceToggle = view.findViewById(R.id.source_switch);
      sourceImage = view.findViewById(R.id.source_image_iv);
    }
  }
}
