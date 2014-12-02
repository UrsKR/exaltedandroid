package anathema.android;

import android.view.GestureDetector;
import android.view.MotionEvent;


//By Mirek Rusin, http://stackoverflow.com/questions/4139288/android-how-to-handle-right-to-left-swipe-gestures
public class SwipeListener extends GestureDetector.SimpleOnGestureListener {
  private static final int SWIPE_THRESHOLD = 100;
  private static final int SWIPE_VELOCITY_THRESHOLD = 100;

  @Override
  public boolean onDown(MotionEvent e) {
    return true;
  }

  @Override
  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    boolean result = false;
    try {
      float diffY = e2.getY() - e1.getY();
      float diffX = e2.getX() - e1.getX();
      if (Math.abs(diffX) > Math.abs(diffY)) {
        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
          if (diffX > 0) {
            onFlingRight(diffX);
          } else {
            onSwipeLeft();
          }
        }
        result = true;
      } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
        if (diffY > 0) {
          onSwipeBottom();
        } else {
          onSwipeTop();
        }
      }
      result = true;

    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return result;
  }

  public void onFlingRight(float diffX) {
  }

  public void onSwipeLeft() {
  }

  public void onSwipeTop() {
  }

  public void onSwipeBottom() {
  }
}
