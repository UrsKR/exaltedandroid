package anathema.android.fashion;

import anathema.android.DiceAndCoins;
import anathema.android.Flip;
import anathema.android.util.FileToString;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
    //fashion.primaryAccessory = rollPrimaryAccessory();
    //rollSecondaryAccessory();
    //rollHair();
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
    if (roll == 13 || roll == 14) {
      String unitSymbol = rollPattern();
      return "{0} presents a uniform with a {2} " + unitSymbol + " on its high collar.";
    }
    if (roll > 19) {
      return rollSecondaryPiece();
    } else {
      Map<Integer, String> piece = new HashMap<>();
      piece.put(1, "{0} wears nothing but {1} bare chest.");
      piece.put(2, "{0} keeps {1} body wrapped in bandages.");
      piece.put(3, "{0} is protected by smoothly polished tight leathers.");
      piece.put(4, "{0} features tight leathers, barbed with metal.");
      piece.put(5, "{0} is dressed in loose monk robes.");
      piece.put(6, "{0} is dressed in loose monk robes.");
      piece.put(7, "{0} sports parade armor with an intricately designed breastplate.");
      piece.put(8, "{0} displays parade armor with massive interlocking shoulder plates.");
      piece.put(9, "{0} dons a flowing dress.");
      piece.put(10, "{0} presents a flowing toga.");
      piece.put(11, "{0} wears a worker's leather slacks, covered by a smock.");
      piece.put(12, "{0} is dressed in a worker's denim slacks, covered by a smock.");
      piece.put(15, "{0} wrapped {1} body in chain, tight enough to count as clothing.");
      piece.put(16, "{0} is covered covered by plates, hiding {1} very humanity.");
      piece.put(17, "{0} is decked out in bones and symbols of death.");
      piece.put(18, "{0} is wrapped in vines and foliage.");
      return piece.get(roll);
    }
  }

  private String rollSecondaryPiece() {
    int roll = rollD20();
    if (roll == 15 || roll == 16) {
      String pattern = rollPatternAdjective();
      return pattern + " symbols give {1} appearance a spiritual air.";
    }
    if (roll > 19) {
      return rollPrimaryPiece();
    }
    Map<Integer, String> piece = new HashMap<>();
    piece.put(1, "{0} wears a kilt instead of more traditional armor.");
    piece.put(2, "{0} wears a kilt instead of trousers.");
    piece.put(3, "{1} arms are protected by leather wraps.");
    piece.put(4, "{1} legs are covered by leather wraps.");
    piece.put(5, "Tight bands cover {1} extremities, giving {1} a martial artist's look.");
    piece.put(6, "Wraps of cloth, oft sported by martial artists, hide {1} arms and legs.");
    piece.put(7, "Pouches and bags are sewn into every piece of {1} outfit. Who knows what {0} is hiding?");
    piece.put(8, "Pouches and bags are sewn into every piece of {1} outfit. Who knows what {0} is hiding?");
    piece.put(9, "{1} belly is hemmed in by a cummerbund.");
    piece.put(10, "{1} midriff is tied with a heavy belt.");
    piece.put(11, "A flowing cape accentuates {1} every gesture.");
    piece.put(12, "A trench coat hides most of it, though.");
    piece.put(13, "{1} shoulder cape adds to {1} well-traveled look.");
    piece.put(14, "Whatever else {0} might wear is hidden by a poncho.");
    piece.put(17, "Almost as prominent is {1} massive utility vest, stuffed with tools.");
    piece.put(18, "Almost as prominent is {1} massive utility vest, stuffed with tools.");
    return piece.get(roll);
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