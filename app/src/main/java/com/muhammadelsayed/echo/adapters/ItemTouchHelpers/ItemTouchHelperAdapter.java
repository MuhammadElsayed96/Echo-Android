package com.muhammadelsayed.echo.adapters.ItemTouchHelpers;

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);

}
