package anathema.android.lifepath;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class Lifepath {

  public List<String> suggestedTraits = new ArrayList<>();
  public StringBuilder lifepath = new StringBuilder();

  public String asText() {
    StringBuilder text = new StringBuilder(lifepath.toString());
    text.append("\n");
    text.append("\n");
    text.append("Suggested traits to reflect this background: ");
    String merits = TextUtils.join(", ", suggestedTraits);
    text.append(merits);
    return text.toString();
  }
}
