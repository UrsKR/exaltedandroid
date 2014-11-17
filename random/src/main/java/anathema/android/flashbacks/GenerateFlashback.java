package anathema.android.flashbacks;

import anathema.android.DiceAndCoins;
import anathema.android.util.FileToString;
import anathema.android.util.JsonRandomizer;

public class GenerateFlashback {
  private final DiceAndCoins diceAndCoins;
  private final JsonRandomizer randomizer;

  public GenerateFlashback(DiceAndCoins diceAndCoins, FileToString fileToString) {
    this.diceAndCoins = diceAndCoins;
    this.randomizer = new JsonRandomizer("flashbacks", diceAndCoins, fileToString);
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
    int roll = diceAndCoins.rollTenSidedDie();
    if (roll <= 7) {
      flashback.append("Kept control of myself...completed ");
      flashback.append(randomizer.pickElementFromJsonArray("resultUnderControl"));
    } else {
      flashback.append("Lost myself to the curse... and yet, completed my mission ");
      flashback.append(randomizer.pickElementFromJsonArray("resultUnderCurse"));
    }
    return flashback.toString();
  }
}
