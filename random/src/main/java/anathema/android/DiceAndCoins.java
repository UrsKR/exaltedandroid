package anathema.android;

import java.util.Random;

public class DiceAndCoins {
  private final Random random = new Random();

  public int rollTenSidedDie() {
    return random.nextInt(10) + 1;
  }

  public Flip flipACoin() {
    boolean flip = random.nextBoolean();
    if (flip) {
      return Flip.Head;
    }
    return Flip.Tails;
  }

  public int rollTwentySidedDie() {
    return random.nextInt(20) + 1;
  }
}