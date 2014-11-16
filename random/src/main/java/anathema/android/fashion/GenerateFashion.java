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
    return resolvePlaceholders(primaryPiece);
  }

  private String rollSecondaryPiece() {
    String secondaryPiece = pickElementFromJsonArray("secondaryPiece");
    return resolvePlaceholders(secondaryPiece);
  }

  private String resolvePlaceholders(String textWithPlaceholders) {
    Pattern pattern = Pattern.compile("%(.+?)%");
    Matcher matcher = pattern.matcher(textWithPlaceholders);
    String resolvedPiece = textWithPlaceholders;
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
    String primaryAccessory = pickElementFromJsonArray("primaryAccessory");
    return resolvePlaceholders(primaryAccessory);
  }

  private String rollSecondaryAccessory() {
    String secondaryAccessory = pickElementFromJsonArray("secondaryAccessory");
    return resolvePlaceholders(secondaryAccessory);
  }

  private String rollHair() {
    return pickElementFromJsonArray("hairstyles");
  }

  private String rollAnyColor() {
    String colorStyle = pickElementFromJsonArray("colors");
    return pickElementFromJsonArray(colorStyle);
  }

  @SuppressWarnings("UnusedDeclaration")
  private String rollPattern() {
    String patternStyle = pickElementFromJsonArray("patterns");
    return pickNameFromJsonArray(patternStyle);
  }

  @SuppressWarnings("UnusedDeclaration")//From Random Tables
  private String rollPatternAdjective() {
    String patternStyle = pickElementFromJsonArray("patterns");
    return pickAttributeFromJsonArray(patternStyle);
  }

  @SuppressWarnings("UnusedDeclaration")
  private String rollPrismaticColor() {
    return pickElementFromJsonArray("prismaticColors");
  }

  @SuppressWarnings("UnusedDeclaration")
  private String rollMaterialColor() {
    return pickElementFromJsonArray("materialColors");
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