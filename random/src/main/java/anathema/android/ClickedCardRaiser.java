package anathema.android;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

public class ClickedCardRaiser implements RecyclerView.OnItemTouchListener {
  private static final int ADDITIONAL_ELEVATION = 6;
  private CardView lastRaised;

  @Override
  public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
    View view = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
    if (view == null) {
      drop();
      return false;
    }
    CardView card = (CardView) view;
    if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
      raise(card);
    }
    if (motionEvent.getActionMasked() == MotionEvent.ACTION_UP) {
      drop();
    }
    return false;
  }

  private void raise(CardView card) {
    if (lastRaised != null) {
      return;
    }
    card.setCardElevation(card.getCardElevation() + ADDITIONAL_ELEVATION);
    lastRaised = card;
  }

  private void drop() {
    if (lastRaised == null) {
      return;
    }
    lastRaised.setCardElevation(lastRaised.getCardElevation() - ADDITIONAL_ELEVATION);
    lastRaised = null;
  }

  @Override
  public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
    //nothing to do
  }
}
