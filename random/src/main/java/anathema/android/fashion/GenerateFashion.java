package anathema.android.fashion;

import anathema.android.DiceAndCoins;
import anathema.android.Flip;
import anathema.android.util.FileToString;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GenerateFashion {
  private DiceAndCoins diceAndCoins;
  private FileToString fileToString;

  public GenerateFashion(DiceAndCoins diceAndCoins, FileToString fileToString) {
    this.diceAndCoins = diceAndCoins;
    this.fileToString = fileToString;
  }

  public Fashion generate() {
    Fashion fashion = new Fashion();
    //String text = "%primaryPiece% %secondaryPiece%\n%primaryAccessory% %secondaryAccessory%\n%hairstyles%"; 
    rollWearer(fashion);
    fashion.primaryPiece = rollPrimaryPiece();
    fashion.secondaryPiece = rollSecondaryPiece();
    fashion.primaryAccessory = rollPrimaryAccessory();
    fashion.secondaryAccessory = rollSecondaryAccessory();
    fashion.hairStyle = rollHair();
    fashion.primaryColor = rollAnyColor();
    fashion.highlightColor = rollAnyColor();
    return fashion;
  }

  private void rollWearer(Fashion fashion) {
    Flip flip = diceAndCoins.flipACoin();
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
    return pickElementFromJsonArray("primaryPiece");
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
    return pickElementFromJsonArray("secondaryPiece");
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
    return pickElementFromJsonArray("primaryAccessory");
  }

  private String rollSecondaryAccessory() {
    int roll = rollD20();
    if (roll >= 19) {
      return rollPrimaryAccessory();
    }
    if (roll >= 17) {
      String pattern = rollPattern();
      return "{1} hands bear a number of heavy rings, all fashioned after a " + pattern + "''s form.";
    }
    if (roll >= 15) {
      String pattern = rollPattern();
      return "{0} hides {1} face behind a " + pattern + " mask.";
    }
    if (roll >= 13) {
      String pattern = rollPattern();
      return "Deep scars cover {1} skin, formed after " + pattern + "s, no doubt acquired in a series of painful rituals.";
    }
    if (roll >= 11) {
      String pattern = rollPattern();
      return "{1} belt is home to a beautiful brush, the handle finely carved to represent a " + pattern + ".";
    }
    return pickElementFromJsonArray("secondaryAccessory");
  }

  private String rollHair() {
    return pickElementFromJsonArray("hairstyles");
  }

  private String rollPattern() {
    String patternStyle = pickElementFromJsonArray("patterns");
    return pickNameFromJsonArray(patternStyle);
  }

  private String rollPatternAdjective() {
    String patternStyle = pickElementFromJsonArray("patterns");
    return pickAttributeFromJsonArray(patternStyle);
  }

  private String rollAnyColor() {
    String colorStyle = pickElementFromJsonArray("colors");
    return pickElementFromJsonArray(colorStyle);
  }

  private String rollPrismaticColor() {
    return pickElementFromJsonArray("prismaticColors");
  }


  private String rollMaterialColor() {
    return pickElementFromJsonArray("materialColors");
  }

  private int rollD20() {
    return diceAndCoins.rollTwentySidedDie();
  }

  private String pickElementFromJsonArray(String array) {
    String content = fileToString.loadFile(array + ".json");
    try {
      JSONArray entries = new JSONObject(content).getJSONArray(array);
      int roll = diceAndCoins.rollWithSides(entries.length());
      return entries.getString(roll - 1);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  private String pickNameFromJsonArray(String array) {
    return pickPropertyFromJsonArray(array + ".json", array, "name");
  }

  private String pickAttributeFromJsonArray(String array) {
    return pickPropertyFromJsonArray(array + ".json", array, "attribute");
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