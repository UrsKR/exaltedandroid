package anathema.android.util;

import anathema.android.DiceAndCoins;

public class PatternBasedGenerator {
  private final PlaceholderResolver resolver;
  private final FileToString fileToString;
  private String tableFolder;

  public PatternBasedGenerator(DiceAndCoins diceAndCoins, FileToString fileToString, String tableFolder) {
    this.fileToString = fileToString;
    this.tableFolder = tableFolder;
    Randomizer randomizer = new JsonRandomizer(tableFolder, diceAndCoins, fileToString);
    this.resolver = new PlaceholderResolver(PatternBasedGenerator.class, this, randomizer);
  }

  public String generate() {
    String unresolved = fileToString.loadFile(tableFolder + "/" + "pattern.txt");
    return resolver.resolvePlaceholders(unresolved);
  }
}
