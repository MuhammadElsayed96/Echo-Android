package com.muhammadelsayed.echo.Adapters.ItemTouchHelpers;

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);

}
