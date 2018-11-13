package com.muhammadelsayed.echo.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.muhammadelsayed.echo.R;
import com.muhammadelsayed.echo.adapters.ItemTouchHelpers.ItemTouchHelperAdapter;
import com.muhammadelsayed.echo.adapters.ItemTouchHelpers.ItemTouchHelperViewHolder;
import com.muhammadelsayed.echo.adapters.ItemTouchHelpers.OnSectionsListChangedListener;
import com.muhammadelsayed.echo.adapters.ItemTouchHelpers.OnStartDragListener;
import com.muhammadelsayed.echo.model.Section;

import java.util.Collections;
import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ItemViewHolder> implements ItemTouchHelperAdapter {

    private List<Section> mSections;
    private Context mContext;
    private OnStartDragListener mDragStartListener;
    private OnSectionsListChangedListener mListChangedListener;

    public SectionAdapter(Context context, List<Section> sections,
                          OnStartDragListener dragLlistener,
                          OnSectionsListChangedListener listChangedListener) {
        mSections = sections;
        mContext = context;
        mDragStartListener = dragLlistener;
        mListChangedListener = listChangedListener;
    }

    @NonNull
    @Override
    public SectionAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sections_item, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {

        final Section section = mSections.get(position);

        holder.sectionName.setText(section.getTitle());

        holder.imgDrag.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        return mSections.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mSections, fromPosition, toPosition);
        mListChangedListener.onNoteListChanged(mSections);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {
        final TextView sectionName;
        final ImageView imgDrag;

        ItemViewHolder(View itemView) {
            super(itemView);
            sectionName = itemView.findViewById(R.id.section_name);
            imgDrag = itemView.findViewById(R.id.img_drag);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}