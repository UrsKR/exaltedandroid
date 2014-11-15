package anathema.android.manse;

public class DangerousLocation implements ManseSpecialty {
  @Override
  public String getCaption() {
    return "Dangerous Location";
  }

  @Override
  public String getRisk() {
    return "The utility of this Manse is very spotty, and you may have to defend it against the very environment that is empowering it.";
  }

  @Override
  public String getReward() {
    return "The Exalt might tame or channel the dangers around you Manse.";
  }

  @Override
  public String getDetails() {
    return "Sometimes Manses are located in areas you can not get to normally with ease. The Wyld may have surrounded it, there might be growth of dangerous plants, hostile terrain, or local animals that making approaching it impossible for anyone who is not Exalted. It can be used to upgrade it's defenses in your benefit. Imagine taming a Behemoth that lives near your home. The ultimate watch dog.Or shaping the lava around the foundation to make an uber moat that melts anyone trying to cross with out your approval.So many options.";
  }

  @Override
  public int getRoll() {
    return 6;
  }
}
