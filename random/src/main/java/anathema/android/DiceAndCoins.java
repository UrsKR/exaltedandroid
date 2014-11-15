package anathema.android;

import java.util.Random;

public class DiceAndCoins {
  private final Random random = new Random();

  public Flip flipACoin() {
    boolean flip = random.nextBoolean();
    if (flip) {
      return Flip.Head;
    }
    return Flip.Tails;
  }

  public int rollTenSidedDie() {
    return rollWithSides(10);
  }

  public int rollTwentySidedDie() {
    return rollWithSides(20);
  }

  public int rollWithSides(int sides) {
    return random.nextInt(sides) + 1;
  }
}