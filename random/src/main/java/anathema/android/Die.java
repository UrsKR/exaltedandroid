package anathema.android;

import java.util.Random;

public class Die {
  private final Random die = new Random();

  public int roll() {
    return die.nextInt(10) + 1;
  }
}
