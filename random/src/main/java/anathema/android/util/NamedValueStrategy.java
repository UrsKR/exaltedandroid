package anathema.android.util;

import java.util.Map;

public class NamedValueStrategy implements ResolutionStrategy {
  private Map<String, String> namedValues;

  public NamedValueStrategy(Map<String, String> namedValues) {
    this.namedValues = namedValues;
  }

  @Override
  public String findReplacement(String placeholder) {
    return namedValues.get(placeholder);
  }
}