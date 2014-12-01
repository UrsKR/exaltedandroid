package anathema.android.fashion;

import anathema.android.DiceAndCoins;
import anathema.android.util.CombinedResolver;
import anathema.android.util.FileToString;
import anathema.android.util.JsonRandomizer;
import anathema.android.util.Person;
import anathema.android.util.PlaceholderResolver;
import anathema.android.util.Randomizer;

import java.util.HashMap;

import static java.lang.Character.isWhitespace;
import static java.lang.Character.toUpperCase;

public class GenerateFashion {
  private final PlaceholderResolver resolver;
  private final HashMap<String, String> namedValues = new HashMap<>();
  private DiceAndCoins diceAndCoins;
  private FileToString fileToString;

  public GenerateFashion(DiceAndCoins diceAndCoins, FileToString fileToString) {
    this.diceAndCoins = diceAndCoins;
    this.fileToString = fileToString;
    Randomizer randomizer = new JsonRandomizer("fashion", diceAndCoins, fileToString);
    this.resolver = CombinedResolver.create(GenerateFashion.class, this, randomizer, namedValues);
  }

  public String generate() {
    Person wearer = Person.randomize(diceAndCoins);
    Colors colors = rollColors();
    namedValues.put("personal", wearer.personal);
    namedValues.put("possessive", wearer.possessiveDeterminer);
    namedValues.put("possessivePronoun", wearer.possessivePronoun);
    namedValues.put("primaryColor", colors.primaryColor);
    namedValues.put("highlightColor", colors.highlightColor);
    String pattern = fileToString.loadFile("fashion/pattern.txt");
    String resolvedPattern = resolver.resolvePlaceholders(pattern);
    return capitalizeSentences(resolvedPattern);
  }

  private Colors rollColors() {
    Colors colors = new Colors();
    colors.primaryColor = resolver.resolvePlaceholders("#color#");
    colors.highlightColor = resolver.resolvePlaceholders("#color#");
    return colors;
  }

  private String capitalizeSentences(String text) {
    int pos = 0;
    boolean capitalize = true;
    StringBuilder builder = new StringBuilder(text);
    while (pos < builder.length()) {
      if (builder.charAt(pos) == '.') {
        capitalize = true;
      } else if (capitalize && !isWhitespace(builder.charAt(pos))) {
        builder.setCharAt(pos, toUpperCase(builder.charAt(pos)));
        capitalize = false;
      }
      pos++;
    }
    return builder.toString();
  }
}