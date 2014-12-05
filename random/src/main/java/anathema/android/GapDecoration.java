package anathema.android;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GapDecoration extends RecyclerView.ItemDecoration {
  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    outRect.top += 3;
    outRect.bottom += 3;
  }
}