package anathema.android.manse;

public class PartialUncapping implements ManseSpecialty {
  @Override
  public String getCaption() {
    return "Partial Uncapping";
  }

  @Override
  public String getRisk() {
    return "Growing Wyld contamination, possibly drawing in spirits and monsters that enjoy uncapped Demanse power.";
  }

  @Override
  public String getReward() {
    return "You can upgrade the Manse to contain the excess power, or maybe learn how to channel the uncapped power to other sorcerous projects.";
  }

  @Override
  public String getDetails() {
    return "Manses are structures on Demanses that contain the essence flowing off from the land. Partial uncapping of it means there is a lot of raw unconstrained essence flowing out causing mutations and local conditions to weird up. Either way to turn this risk around will take some serious 1st Age Lore R&D.";
  }

  @Override
  public int getRoll() {
    return 4;
  }
}
