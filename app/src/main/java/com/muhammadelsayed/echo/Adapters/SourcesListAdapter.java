package com.muhammadelsayed.echo.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.model.Source;
import com.squareup.picasso.Picasso;
import com.zcw.togglebutton.ToggleButton;

import java.util.List;

public class SourcesListAdapter extends ArrayAdapter<Source> {

  private Context mContext;
  private List<Source> sourcesList;

  public SourcesListAdapter(@NonNull Context context, List<Source> sources) {
    super(context, 0, sources);
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

    Source source = getItem(position);
    if (convertView == null)
      convertView =
          LayoutInflater.from(getContext()).inflate(R.layout.source_list_row, parent, false);
    TextView title = convertView.findViewById(R.id.source_name_tv);
    ImageView imageView = convertView.findViewById(R.id.source_image_iv);
    Switch toggleButton = convertView.findViewById(R.id.source_switch);

    if (source.isToggleStatus()) {
      toggleButton.setChecked(true);
    } else {
      toggleButton.setChecked(false);
    }
    title.setText(source.getName());
    Picasso.get().load(source.getImageResourceID()).into(imageView);
    return convertView;
  }
}
