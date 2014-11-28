package anathema.android.util;

public interface Randomizer {
  String pickElementFromJsonArray(String array);

  String pickPropertyFromJsonArray(String array, String property);
}