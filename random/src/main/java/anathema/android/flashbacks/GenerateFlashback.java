package anathema.android.flashbacks;

import anathema.android.DiceAndCoins;
import anathema.android.util.FileToString;
import anathema.android.util.JsonRandomizer;
import anathema.android.util.PlaceholderResolver;

public class GenerateFlashback {
  private final JsonRandomizer randomizer;
  private final PlaceholderResolver resolver;

  public GenerateFlashback(DiceAndCoins diceAndCoins, FileToString fileToString) {
    this.randomizer = new JsonRandomizer("flashbacks", diceAndCoins, fileToString);
    this.resolver = new PlaceholderResolver(GenerateFlashback.class, this);
  }

  public String generate() {
    StringBuilder flashback = new StringBuilder();
    flashback.append(randomizer.pickElementFromJsonArray("action"));
    flashback.append("...");
    flashback.append(randomizer.pickElementFromJsonArray("object"));
    flashback.append("...");
    flashback.append("\n");
    flashback.append(randomizer.pickElementFromJsonArray("conflict"));
    flashback.append("!");
    flashback.append("\n");
    String curseInfluence = randomizer.pickElementFromJsonArray("curseInfluence");
    String resolvedCurseInfluence = resolver.resolvePlaceholders(curseInfluence);
    flashback.append(resolvedCurseInfluence);
    return flashback.toString();
  }

  @SuppressWarnings("UnusedDeclaration")
  public String rollResultUnderControl() {
    return randomizer.pickElementFromJsonArray("resultUnderControl");
  }

  @SuppressWarnings("UnusedDeclaration")
  public String rollResultUnderCurse() {
    return randomizer.pickElementFromJsonArray("resultUnderCurse");
  }
}
