package anathema.android.manse;

import anathema.android.Die;

public class GenerateManse {

  private Die die;

  public GenerateManse(Die die) {
    this.die = die;
  }

  public ManseSpecialty generate() {
    int roll = die.roll();
    return lookUpManseSpecialty(roll);
  }

  private ManseSpecialty lookUpManseSpecialty(int roll) {
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
