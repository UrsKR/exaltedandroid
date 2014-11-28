package anathema.android.fashion;

public class Fashion {
  public String personal;     //0
  public String possessive;   //1
  public String highlightColor;  //2
  public String primaryColor; //3
  public String primaryPiece;
  public String secondaryPiece;
  public String primaryAccessory;
  public String secondaryAccessory;
  public String hairStyle;

  public String asText() {
    return capitalize(primaryPiece) + " " + capitalize(secondaryPiece) + "\n" + capitalize(primaryAccessory) + " " + capitalize(secondaryAccessory) + "\n" + capitalize(hairStyle);
  }

  private String capitalize(String text) {
    String lowerCase = text.toLowerCase();
    return lowerCase.substring(0, 1).toUpperCase() + lowerCase.substring(1);
  }
}