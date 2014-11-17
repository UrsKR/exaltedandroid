package anathema.android.fashion;

import anathema.android.DiceAndCoins;
import anathema.android.Flip;
import anathema.android.util.FileToString;
import anathema.android.util.JsonRandomizer;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateFashion {
  private final JsonRandomizer randomizer;
  private DiceAndCoins diceAndCoins;

  public GenerateFashion(DiceAndCoins diceAndCoins, FileToString fileToString) {
    this.diceAndCoins = diceAndCoins;
    this.randomizer = new JsonRandomizer(diceAndCoins, fileToString);
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
    String primaryPiece = randomizer.pickElementFromJsonArray("primaryPiece");
    return resolvePlaceholders(primaryPiece);
  }

  private String rollSecondaryPiece() {
    String secondaryPiece = randomizer.pickElementFromJsonArray("secondaryPiece");
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
    String primaryAccessory = randomizer.pickElementFromJsonArray("primaryAccessory");
    return resolvePlaceholders(primaryAccessory);
  }

  private String rollSecondaryAccessory() {
    String secondaryAccessory = randomizer.pickElementFromJsonArray("secondaryAccessory");
    return resolvePlaceholders(secondaryAccessory);
  }

  private String rollHair() {
    return randomizer.pickElementFromJsonArray("hairstyles");
  }

  private String rollAnyColor() {
    String colorStyle = randomizer.pickElementFromJsonArray("colors");
    return randomizer.pickElementFromJsonArray(colorStyle);
  }

  @SuppressWarnings("UnusedDeclaration")
  private String rollPattern() {
    String patternStyle = randomizer.pickElementFromJsonArray("patterns");
    return randomizer.pickNameFromJsonArray(patternStyle);
  }

  @SuppressWarnings("UnusedDeclaration")//From Random Tables
  private String rollPatternAdjective() {
    String patternStyle = randomizer.pickElementFromJsonArray("patterns");
    return randomizer.pickAttributeFromJsonArray(patternStyle);
  }

  @SuppressWarnings("UnusedDeclaration")
  private String rollPrismaticColor() {
    return randomizer.pickElementFromJsonArray("prismaticColors");
  }

  @SuppressWarnings("UnusedDeclaration")
  private String rollMaterialColor() {
    return randomizer.pickElementFromJsonArray("materialColors");
  }
}