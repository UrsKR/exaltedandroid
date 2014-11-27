package anathema.android.flashbacks;

import anathema.android.DiceAndCoins;
import anathema.android.util.FileToString;
import anathema.android.util.JsonRandomizer;
import anathema.android.util.PlaceholderResolver;
import anathema.android.util.Randomizer;

public class GenerateFlashback {
  private final PlaceholderResolver resolver;
  private final FileToString fileToString;

  public GenerateFlashback(DiceAndCoins diceAndCoins, FileToString fileToString) {
    this.fileToString = fileToString;
    Randomizer randomizer = new JsonRandomizer("flashbacks", diceAndCoins, fileToString);
    this.resolver = new PlaceholderResolver(GenerateFlashback.class, this, randomizer);
  }

  public String generate() {
    String unresolved = fileToString.loadFile("flashbacks/flashbackpattern.txt");
    return resolver.resolvePlaceholders(unresolved);
  }
}
