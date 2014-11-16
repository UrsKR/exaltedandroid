package anathema.android.fashion;

import anathema.android.DiceAndCoins;
import anathema.android.Flip;
import anathema.android.util.FileToString;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static anathema.android.Flip.Tails;

public class GenerateFashion {
  private DiceAndCoins diceAndCoins;
  private FileToString fileToString;

  public GenerateFashion(DiceAndCoins diceAndCoins, FileToString fileToString) {
    this.diceAndCoins = diceAndCoins;
    this.fileToString = fileToString;
  }

  public Fashion generate() {
    Fashion fashion = new Fashion();
    rollWearer(fashion);
    fashion.primaryPiece = rollPrimaryPiece();
    fashion.secondaryPiece = rollSecondaryPiece();
    fashion.primaryAccessory = rollPrimaryAccessory();
    //rollSecondaryAccessory();
    fashion.hairStyle = rollHair();
    fashion.primaryColor = rollAnyColor();
    fashion.highlightColor = rollAnyColor();
    return fashion;
  }

  private void rollWearer(Fashion fashion) {
    Flip flip = flipCoin();
    if (flip == Flip.Head) {
      fashion.personal = "he";
      fashion.possessive = "his";
    } else {
      fashion.personal = "she";
      fashion.possessive = "her";
    }
  }

  private String rollPrimaryPiece() {
    int roll = rollD20();
    if (roll >= 19) {
      return rollSecondaryPiece();
    }
    if (roll >= 17) {
      String unitSymbol = rollPattern();
      return "{0} presents a uniform with a {2} " + unitSymbol + " on its high collar.";
    }
    return pickElementFromJsonArray("primaryPiece.json", "primaryPiece");
  }

  private String rollSecondaryPiece() {
    int roll = rollD20();
    if (roll >= 19) {
      return rollPrimaryPiece();
    }
    if (roll >= 17) {
      String pattern = rollPatternAdjective();
      return pattern + " symbols give {1} appearance a spiritual air.";
    }
    return pickElementFromJsonArray("secondaryPiece.json", "secondaryPiece");
  }

  private String rollPrimaryAccessory() {
    int roll = rollD20();
    if (roll >= 19) {
      return rollSecondaryAccessory();
    }
    if (roll >= 17) {
      String pattern = rollPattern();
      String material = rollMaterialColor();
      return "{1} body is adorned with " + material + " jewelry, showing " + pattern + "s in various shapes and stances.";
    }
    if (roll >= 15) {
      String pattern = rollPattern();
      String color = rollPrismaticColor();
      return "Much of {1} body is covered with an intricate " + color + " " + pattern + " tattoo.";
    }
    if (roll >= 13) {
      String pattern = rollPattern();
      String color = rollAnyColor();
      return "On second look, you notice subtle " + color + " " + pattern + "s crafted into {1} dress.";
    }
    if (roll >= 12) {
      String pattern = rollPattern();
      String color = rollPrismaticColor();
      return "On top of this, a {2} tabard shows {1} family's mon, a " + color + " " + pattern + ".";
    }
    if (roll >= 11) {
      String pattern = rollPattern();
      String color = rollPrismaticColor();
      return "On top of this, a {2} tabard shows a " + color + " " + pattern + ", undoubtedly a symbol of religious significance.";
    }
    return pickElementFromJsonArray("primaryAccessory.json", "primaryAccessory");
  }

  private String rollSecondaryAccessory() {
    return "";
  }

  private String rollHair() {
    return pickElementFromJsonArray("hairstyles.json", "hairstyles");
  }

  private String rollPattern() {
    int roll = rollD10();
    if (roll <= 5) {
      return pickNameFromJsonArray("creatures.json", "creatures");
    }
    if (roll <= 8) {
      return pickNameFromJsonArray("astrology.json", "astrology");
    }
    if (roll == 9) {
      return pickNameFromJsonArray("geometry.json", "geometry");
    }
    return pickNameFromJsonArray("nature.json", "nature");
  }

  private String rollPatternAdjective() {
    int roll = rollD10();
    if (roll <= 5) {
      return pickAttributeFromJsonArray("creatures.json", "creatures");
    }
    if (roll <= 8) {
      return pickAttributeFromJsonArray("astrology.json", "astrology");
    }
    if (roll == 9) {
      return pickAttributeFromJsonArray("geometry.json", "geometry");
    }
    return pickAttributeFromJsonArray("nature.json", "nature");
  }

  private String rollAnyColor() {
    Flip flipCoin = flipCoin();
    if (flipCoin == Tails) {
      return rollPrismaticColor();
    }
    return rollMaterialColor();
  }

  private String rollPrismaticColor() {
    return pickElementFromJsonArray("prismaticColors.json", "colors");
  }


  private String rollMaterialColor() {
    return pickElementFromJsonArray("materialColors.json", "colors");
  }

  private Flip flipCoin() {
    return diceAndCoins.flipACoin();
  }

  private int rollD10() {
    return diceAndCoins.rollTenSidedDie();
  }

  private int rollD20() {
    return diceAndCoins.rollTwentySidedDie();
  }

  private String pickElementFromJsonArray(String filename, String array) {
    String content = fileToString.loadFile(filename);
    try {
      JSONArray entries = new JSONObject(content).getJSONArray(array);
      int roll = diceAndCoins.rollWithSides(entries.length());
      return entries.getString(roll - 1);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  private String pickNameFromJsonArray(String filename, String array) {
    return pickPropertyFromJsonArray(filename, array, "name");
  }

  private String pickAttributeFromJsonArray(String filename, String array) {
    return pickPropertyFromJsonArray(filename, array, "attribute");
  }

  private String pickPropertyFromJsonArray(String filename, String array, String property) {
    String content = fileToString.loadFile(filename);
    try {
      JSONArray entries = new JSONObject(content).getJSONArray(array);
      int roll = diceAndCoins.rollWithSides(entries.length());
      return entries.getJSONObject(roll - 1).getString(property);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }
}