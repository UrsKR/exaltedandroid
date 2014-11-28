package anathema.android.fashion;

import anathema.android.DiceAndCoins;
import anathema.android.util.CombinedResolver;
import anathema.android.util.FileToString;
import anathema.android.util.JsonRandomizer;
import anathema.android.util.Person;
import anathema.android.util.PlaceholderResolver;
import anathema.android.util.Randomizer;

import java.util.HashMap;

public class GenerateFashion {
  private final Randomizer randomizer;
  private final PlaceholderResolver resolver;
  private final HashMap<String, String> namedValues = new HashMap<>();
  private DiceAndCoins diceAndCoins;

  public GenerateFashion(DiceAndCoins diceAndCoins, FileToString fileToString) {
    this.diceAndCoins = diceAndCoins;
    this.randomizer = new JsonRandomizer("fashion", diceAndCoins, fileToString);
    this.resolver = CombinedResolver.create(GenerateFashion.class, this, randomizer, namedValues);
  }

  public Fashion generate() {
    Person wearer = Person.randomize(diceAndCoins);
    Colors colors = rollColors();
    Fashion fashion = new Fashion();
    namedValues.put("personal", wearer.personal);
    namedValues.put("possessive", wearer.possessive);
    namedValues.put("primaryColor", colors.primaryColor);
    namedValues.put("highlightColor", colors.highlightColor);
    fashion.primaryPiece = rollPrimaryPiece();
    fashion.secondaryPiece = rollSecondaryPiece();
    fashion.primaryAccessory = rollPrimaryAccessory();
    fashion.secondaryAccessory = rollSecondaryAccessory();
    fashion.hairStyle = rollHair();
    return fashion;
  }

  private Colors rollColors() {
    Colors colors = new Colors();
    colors.primaryColor = rollAnyColor();
    colors.highlightColor = rollAnyColor();
    return colors;
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
    String hairstyle = randomizer.pickElementFromJsonArray("hairstyles");
    return resolver.resolvePlaceholders(hairstyle);
  }

  public String rollAnyColor() {
    return resolver.resolvePlaceholders("#color#");
  }

  @SuppressWarnings("UnusedDeclaration")                    //From Random Tables
  public String rollPattern() {
    String patternStyle = randomizer.pickElementFromJsonArray("patterns");
    return randomizer.pickNameFromJsonArray(patternStyle);
  }

  @SuppressWarnings("UnusedDeclaration")//From Random Tables
  public String rollPatternAdjective() {
    String patternStyle = randomizer.pickElementFromJsonArray("patterns");
    return randomizer.pickAttributeFromJsonArray(patternStyle);
  }
}