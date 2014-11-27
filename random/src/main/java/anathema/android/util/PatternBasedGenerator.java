package anathema.android.util;

import anathema.android.DiceAndCoins;

public class PatternBasedGenerator {
  private final PlaceholderResolver resolver;
  private final FileToString fileToString;
  private String tableFolder;
  private String patternFileName;

  public PatternBasedGenerator(DiceAndCoins diceAndCoins, FileToString fileToString, String tableFolder, String patternFileName) {
    this.fileToString = fileToString;
    this.tableFolder = tableFolder;
    this.patternFileName = patternFileName;
    Randomizer randomizer = new JsonRandomizer(tableFolder, diceAndCoins, fileToString);
    this.resolver = new PlaceholderResolver(PatternBasedGenerator.class, this, randomizer);
  }

  public String generate() {
    String unresolved = fileToString.loadFile(tableFolder + "/" + patternFileName + ".txt");
    return resolver.resolvePlaceholders(unresolved);
  }
}
