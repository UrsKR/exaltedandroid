package anathema.android.manse;

import anathema.android.DiceAndCoins;

import java.util.HashMap;

public class GenerateManse {

  private DiceAndCoins diceAndCoins;

  public GenerateManse(DiceAndCoins diceAndCoins) {
    this.diceAndCoins = diceAndCoins;
  }

  public ManseSpecialty generate() {
    int roll = diceAndCoins.rollTenSidedDie();
    HashMap<Integer, ManseSpecialty> specialty = new HashMap<>();
    specialty.put(1, new Squatters());
    specialty.put(2, new LocalGods());
    specialty.put(3, new GuildAwareness());
    specialty.put(4, new PartialUncapping());
    specialty.put(5, new HistoricalLandmark());
    specialty.put(6, new DangerousLocation());
    specialty.put(7, new EncryptedLibrary());
    specialty.put(8, new OnLoan());
    specialty.put(9, new Networked());
    specialty.put(10, new TreasureTrove());
    return specialty.get(roll);
  }
}