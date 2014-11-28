package anathema.android.fashion;

import static java.lang.Character.isWhitespace;
import static java.lang.Character.toUpperCase;

public class Fashion {
  public String primaryPiece;
  public String secondaryPiece;
  public String primaryAccessory;
  public String secondaryAccessory;
  public String hairStyle;

  public String asText() {
    String fashion = primaryPiece + " " + secondaryPiece + "\n" + primaryAccessory + " " + secondaryAccessory + "\n" + hairStyle;
    return capitalizeSentences(fashion.toLowerCase());
  }

  //By Vitali, http://stackoverflow.com/a/16078741/25141
  private String capitalizeSentences(String text) {
    int pos = 0;
    boolean capitalize = true;
    StringBuilder builder = new StringBuilder(text);
    while (pos < builder.length()) {
      if (builder.charAt(pos) == '.') {
        capitalize = true;
      } else if (capitalize && !isWhitespace(builder.charAt(pos))) {
        builder.setCharAt(pos, toUpperCase(builder.charAt(pos)));
        capitalize = false;
      }
      pos++;
    }
    return builder.toString();
  }
}