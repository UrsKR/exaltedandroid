package anathema.android.util;

public class JsonTableStrategy implements ResolutionStrategy {
  private final Randomizer randomizer;

  public JsonTableStrategy(Randomizer randomizer) {
    this.randomizer = randomizer;
  }

  @Override
  public String findReplacement(String placeholder) {
    if (placeholder.contains(",")) {
      String[] split = placeholder.split(",");
      return randomizer.pickPropertyFromJsonArray(split[0], split[1]);
    } else {
      return randomizer.pickElementFromJsonArray(placeholder);
    }
  }
}
