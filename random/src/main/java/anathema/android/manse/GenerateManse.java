package anathema.android.manse;

import anathema.android.DiceAndCoins;

public class GenerateManse {

  private DiceAndCoins diceAndCoins;

  public GenerateManse(DiceAndCoins diceAndCoins) {
    this.diceAndCoins = diceAndCoins;
  }

  public ManseSpecialty generate() {
    int roll = diceAndCoins.rollTenSidedDie();
    switch (roll) {
      case (1):
        return new Squatters();
      case (2):
        return new LocalGods();
      case (3):
        return new GuildAwareness();
      case (4):
        return new PartialUncapping();
      case (5):
        return new HistoricalLandmark();
      case (6):
        return new DangerousLocation();
      case (7):
        return new EncryptedLibrary();
      case (8):
        return new OnLoan();
      case (9):
        return new Networked();
      case (10):
        return new TreasureTrove();
    }
    throw new IllegalArgumentException("Roll out of range [1,10]");
  }
}