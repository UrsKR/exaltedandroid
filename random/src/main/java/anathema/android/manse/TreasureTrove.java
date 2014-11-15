package anathema.android.manse;

public class TreasureTrove implements ManseSpecialty {
  @Override
  public String getCaption() {
    return "Treasure Trove";
  }

  @Override
  public String getRisk() {
    return "Others may be looking for this very treasure and any great treasure means risk in trying to use it.";
  }

  @Override
  public String getReward() {
    return "New merits, allies who you share the treasure with, resources from successful selling parts of it, artifacts from using in your crafting, or maybe you just sit on it as collateral for those rainy days.";
  }

  @Override
  public String getDetails() {
    return "This doesn't mean more artifacts...it could be anything of worth. Gems, silks from the 1st Age, rare magical grain that grows in the desert. The idea is a treasure of some value is hidden, locked, or protected by your manse. You may or may not have access to the treasure yourself, but you do control the key to claiming it. The key is the treasure while useful is troublesome. They might know you hold a lost alchemical distillery in the basement of your manse, or maybe a Oricalchum forge that needs minor repairs. Either way, you will need to control information about the treasure, figure out how to safely utilize it, and maybe, maybe garner a profit off it. Either way it's yours and you know how to use it when the time comes. Figuring that out is the trick.";
  }

  @Override
  public int getRoll() {
    return 10;
  }
}
