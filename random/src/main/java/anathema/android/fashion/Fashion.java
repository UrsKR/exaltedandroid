package anathema.android.fashion;

import java.text.MessageFormat;

public class Fashion {
  public String personal;     //0
  public String possessive;   //1
  public String highlightColor;  //2
  public String primaryColor; //3
  public String primaryPiece;
  public String secondaryPiece;
  public String primaryAccessory;

  public String asText() {
    StringBuilder text = new StringBuilder();
    String primary = MessageFormat.format(primaryPiece, personal, possessive, highlightColor);
    text.append(capitalize(primary));
    text.append(" ");
    String secondary = MessageFormat.format(secondaryPiece, personal, possessive, highlightColor);
    text.append(capitalize(secondary));
    return text.toString();
  }

  private String capitalize(String text) {
    String lowerCase = text.toLowerCase();
    return lowerCase.substring(0, 1).toUpperCase() + lowerCase.substring(1);
  }
}
