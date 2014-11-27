package anathema.android.fashion;

import anathema.android.DiceAndCoins;
import anathema.android.Flip;
import anathema.android.util.FileToString;
import anathema.android.util.JsonRandomizer;
import anathema.android.util.PlaceholderResolver;

public class GenerateFashion {
  private final JsonRandomizer randomizer;
  private final PlaceholderResolver resolver;
  private DiceAndCoins diceAndCoins;

  public GenerateFashion(DiceAndCoins diceAndCoins, FileToString fileToString) {
    this.diceAndCoins = diceAndCoins;
    this.randomizer = new JsonRandomizer("fashion", diceAndCoins, fileToString);
    this.resolver = new PlaceholderResolver(GenerateFashion.class, this);
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

  public String rollPrimaryPiece() {
    String primaryPiece = randomizer.pickElementFromJsonArray("primaryPiece");
    return resolver.resolvePlaceholders(primaryPiece);
  }

  public String rollSecondaryPiece() {
    String secondaryPiece = randomizer.pickElementFromJsonArray("secondaryPiece");
    return resolver.resolvePlaceholders(secondaryPiece);
  }

  public String rollPrimaryAccessory() {
    String primaryAccessory = randomizer.pickElementFromJsonArray("primaryAccessory");
    return resolver.resolvePlaceholders(primaryAccessory);
  }

  public String rollSecondaryAccessory() {
    String secondaryAccessory = randomizer.pickElementFromJsonArray("secondaryAccessory");
    return resolver.resolvePlaceholders(secondaryAccessory);
  }

  public String rollHair() {
    return randomizer.pickElementFromJsonArray("hairstyles");
  }

  public String rollAnyColor() {
    String colorStyle = randomizer.pickElementFromJsonArray("colors");
    return randomizer.pickElementFromJsonArray(colorStyle);
  }

  @SuppressWarnings("UnusedDeclaration")
  public String rollPattern() {
    String patternStyle = randomizer.pickElementFromJsonArray("patterns");
    return randomizer.pickNameFromJsonArray(patternStyle);
  }

  @SuppressWarnings("UnusedDeclaration")//From Random Tables
  public String rollPatternAdjective() {
    String patternStyle = randomizer.pickElementFromJsonArray("patterns");
    return randomizer.pickAttributeFromJsonArray(patternStyle);
  }

  @SuppressWarnings("UnusedDeclaration")
  public String rollPrismaticColor() {
    return randomizer.pickElementFromJsonArray("prismaticColors");
  }

  @SuppressWarnings("UnusedDeclaration")
  public String rollMaterialColor() {
    return randomizer.pickElementFromJsonArray("materialColors");
  }
}