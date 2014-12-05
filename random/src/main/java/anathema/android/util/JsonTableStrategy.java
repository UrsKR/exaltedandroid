package anathema.android.util;

import java.util.HashMap;
import java.util.Map;

public class JsonTableStrategy implements ResolutionStrategy {
  private final Randomizer randomizer;
  private final Map<String, String> rolledResults = new HashMap<>();

  public JsonTableStrategy(Randomizer randomizer) {
    this.randomizer = randomizer;
  }

  @Override
  public String findReplacement(String placeholder) {
    if (placeholder.contains(",")) {
      String[] split = placeholder.split(",");
      String result = randomizer.pickPropertyFromJsonArray(split[0], split[1]);
      remember(split[0], result);
      return result;
    } else if (placeholder.contains("!")) {
      String[] split = placeholder.split("!");
      String result = findOriginalResult(split[0]);
      remember(split[0], result);
      return result;
    } else {
      String result = randomizer.pickElementFromJsonArray(placeholder);
      remember(placeholder, result);
      return result;
    }
  }

  private String findOriginalResult(String array) {
    String candidate = randomizer.pickElementFromJsonArray(array);
    while (candidate.equals(rolledResults.get(array))) {
      candidate = randomizer.pickElementFromJsonArray(array);
    }
    return candidate;
  }

  private void remember(String key, String result) {
    rolledResults.put(key, result);
  }
}
