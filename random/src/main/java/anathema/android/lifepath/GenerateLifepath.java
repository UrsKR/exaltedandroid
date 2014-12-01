package anathema.android.lifepath;

import anathema.android.DiceAndCoins;
import anathema.android.util.CombinedResolver;
import anathema.android.util.FileToString;
import anathema.android.util.JsonRandomizer;
import anathema.android.util.Person;
import anathema.android.util.PlaceholderResolver;
import anathema.android.util.Randomizer;

public class GenerateLifepath {
  private final DiceAndCoins diceAndCoins;
  private FileToString fileToString;
  private final PlaceholderResolver resolver;

  public GenerateLifepath(DiceAndCoins diceAndCoins, FileToString fileToString) {
    this.diceAndCoins = diceAndCoins;
    this.fileToString = fileToString;
    Randomizer randomizer = new JsonRandomizer("lifepath", diceAndCoins, fileToString);
    this.resolver = CombinedResolver.createWithoutNamedValues(GenerateLifepath.class, this, randomizer);
  }

  public String generate() {
    String pattern = fileToString.loadFile("lifepath/pattern.txt");
    return resolver.resolvePlaceholders(pattern);
  }

  @SuppressWarnings("UnusedDeclaration")    // called via JSON reflection
  public String rollPossessive() {
    return Person.randomize(diceAndCoins).possessiveDeterminer;
  }
}