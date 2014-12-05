package anathema.android;

import anathema.android.util.JsonTableStrategy;
import anathema.android.util.Randomizer;
import junit.framework.TestCase;

public class JsonTableStrategy_Test extends TestCase {

  private String[] results = new String[3];
  private int numberOfCalls = 0;
  
  public void testDoesNotRollSameResultTwice() {
    results[0] = "result";
    results[1] = "result";
    results[2] = "freshResult";
    Randomizer randomizer = new Randomizer() {
      @Override
      public String pickElementFromJsonArray(String array) {
        return results[numberOfCalls++];
      }

      @Override
      public String pickPropertyFromJsonArray(String array, String property) {
        return results[numberOfCalls++];
      }
    };
    JsonTableStrategy strategy = new JsonTableStrategy(randomizer);
    strategy.findReplacement("test");
    String replacement = strategy.findReplacement("test!noRepetition");
    assertEquals("freshResult", replacement);
  }
}
