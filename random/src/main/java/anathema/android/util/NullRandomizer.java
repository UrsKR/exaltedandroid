package anathema.android.util;

public class NullRandomizer implements Randomizer {
  @Override
  public String pickElementFromJsonArray(String array) {
    return "";
  }

  @Override
  public String pickNameFromJsonArray(String array) {
    return "";
  }

  @Override
  public String pickAttributeFromJsonArray(String array) {
    return "";
  }
}