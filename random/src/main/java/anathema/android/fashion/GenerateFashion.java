package anathema.android.fashion;

import anathema.android.DiceAndCoins;
import anathema.android.Flip;
import anathema.android.util.FileToString;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    String primaryPiece = pickElementFromJsonArray("primaryPiece");
    return resolvePlaceHolders(primaryPiece);
  }

  private String rollSecondaryPiece() {
    String secondaryPiece = pickElementFromJsonArray("secondaryPiece");
    return resolvePlaceHolders(secondaryPiece);
  }

  private String resolvePlaceHolders(String primaryPiece) {
    Pattern pattern = Pattern.compile("%(.+?)%");
    Matcher matcher = pattern.matcher(primaryPiece);
    String resolvedPiece = primaryPiece;
    while (matcher.find()) {
      try {
        String group = matcher.group(1);
        String element = (String) GenerateFashion.class.getDeclaredMethod(group).invoke(this);
        resolvedPiece = resolvedPiece.replaceAll("%" + group + "%", element);
      } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
        throw new RuntimeException(e);
      }
    }
    return resolvedPiece;
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
    String secondaryAccessory = pickElementFromJsonArray("secondaryAccessory");
    return resolvePlaceHolders(secondaryAccessory);
  }

  private String rollHair() {
    return pickElementFromJsonArray("hairstyles");
  }

  private String rollPattern() {
    String patternStyle = pickElementFromJsonArray("patterns");
    return pickNameFromJsonArray(patternStyle);
  }

  @SuppressWarnings("UnusedDeclaration")//From Random Tables
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
    return pickFromJsonArray(array, "name");
  }

  private String pickAttributeFromJsonArray(String array) {
    return pickFromJsonArray(array, "attribute");
  }

  private String pickFromJsonArray(String array, String property) {
    return pickPropertyFromJsonArray(array + ".json", array, property);
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