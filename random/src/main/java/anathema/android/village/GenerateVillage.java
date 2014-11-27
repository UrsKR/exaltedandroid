package anathema.android.village;

import anathema.android.DiceAndCoins;
import anathema.android.util.FileToString;
import anathema.android.util.JsonRandomizer;
import anathema.android.util.PlaceholderResolver;
import anathema.android.util.Randomizer;

public class GenerateVillage {

  private final PlaceholderResolver resolver;
  private final FileToString fileToString;

  public GenerateVillage(DiceAndCoins diceAndCoins, FileToString fileToString) {
    this.fileToString = fileToString;
    Randomizer randomizer = new JsonRandomizer("village", diceAndCoins, fileToString);
    this.resolver = new PlaceholderResolver(GenerateVillage.class, this, randomizer);
  }

  public String generate() {
    String unresolved = fileToString.loadFile("flashbacks/villagepattern.txt");
    return resolver.resolvePlaceholders(unresolved);
  }
}