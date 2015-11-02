package com.akavrt.rotten;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MarginDecoration extends RecyclerView.ItemDecoration {
    private final int mHorizontalMargin;
    private final int mVerticalMargin;

    public MarginDecoration(int horizontalMargin, int verticalMargin) {
        this.mHorizontalMargin = horizontalMargin;
        this.mVerticalMargin = verticalMargin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(mHorizontalMargin, mVerticalMargin, mHorizontalMargin, mVerticalMargin);
    }
}
