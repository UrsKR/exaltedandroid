package anathema.android.village;

import anathema.android.DiceAndCoins;
import anathema.android.util.FileToString;
import anathema.android.util.JsonRandomizer;
import anathema.android.util.PlaceholderResolver;

public class GenerateVillage {

  private final JsonRandomizer randomizer;
  private final DiceAndCoins diceAndCoins;
  private final PlaceholderResolver resolver;

  public GenerateVillage(DiceAndCoins diceAndCoins, FileToString fileToString) {
    this.diceAndCoins = diceAndCoins;
    this.randomizer = new JsonRandomizer("village", diceAndCoins, fileToString);
    this.resolver = new PlaceholderResolver(GenerateVillage.class, this);
  }

  public String generate() {
    int sizeRoll = diceAndCoins.rollTenSidedDie();
    StringBuilder village = new StringBuilder();
    int numberOfEvents = findNumberOfEvents(village, sizeRoll);
    for (int event = 1; event <= numberOfEvents; event++) {
      String eventText = generateIssues();
      if (event == 1 || event == 3) {
        village.append(eventText);
        if (numberOfEvents != 2) {
          village.append(".");
        }
      }
      if (event == 2) {
        if (numberOfEvents == 2) {
          village.append(" and ");
          village.append(eventText);
          village.append(".");
        } else {
          village.append(" It ");
          village.append(eventText);
          village.append(" and ");
        }
      }
      if (event == 4) {
        village.append(" On top of that, it ");
        village.append(eventText);
        village.append(".");
      }
    }
    return village.toString();
  }

  private String generateIssues() {
    StringBuilder issue = new StringBuilder();
    int roll = diceAndCoins.rollTenSidedDie();
    if (roll <= 6) {
      String behaviour = randomizer.pickElementFromJsonArray("externalBehavior");
      String cause = randomizer.pickElementFromJsonArray("externalIssues");
      issue.append(behaviour);
      issue.append(" ");
      issue.append(cause);
    } else {
      String behaviour = randomizer.pickElementFromJsonArray("internalBehavior");
      behaviour = resolver.resolvePlaceholders(behaviour);
      String cause = randomizer.pickElementFromJsonArray("internalIssues");
      issue.append(behaviour);
      issue.append(" ");
      issue.append(cause);
    }
    return issue.toString();
  }

  private int findNumberOfEvents(StringBuilder village, int sizeRoll) {
    switch (sizeRoll) {
      case 1:
      case 2:
      case 3:
        village.append("The hamlet ");
        return 1;
      case 4:
      case 5:
        village.append("The village ");
        return 2;
      case 6:
      case 7:
      case 8:
      case 9:
        village.append("The town ");
        return 3;
      case 10:
        int actualSize = diceAndCoins.rollTenSidedDie();
        while (actualSize == 10) {
          actualSize = diceAndCoins.rollTenSidedDie();
        }
        findNumberOfEvents(village, actualSize);
        return 4;
    }
    throw new IllegalArgumentException("Roll out of range [1,10]");
  }
  
  @SuppressWarnings("UnusedDeclaration")
  public String rollRelief(){
    return randomizer.pickElementFromJsonArray("relief");
  }
}
