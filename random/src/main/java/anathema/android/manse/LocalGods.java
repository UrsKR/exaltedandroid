package anathema.android.manse;

public class LocalGods implements ManseSpecialty{
  @Override
  public String getCaption() {
    return "Local Gods";
  }

  @Override
  public String getRisk() {
    return "Having an essence using god as a rival for control of the Manse is never a good thing.";
  }

  @Override
  public String getReward() {
    return "A powerful ally who can act as a defender of the Manse if you allow for local prayers and offerings to be made to them at the Manse.";
  }

  @Override
  public String getDetails() {
    return " This seems like all risk with little reward but hear me out. Local Gods most likely either were around when the Manse was first built and had preexisting relationships with the creators OR they are new local gods looking to shore up their power base. Nothing like having a personal Manse God who acts as the patron for the Demanse your buildings sit on. Pull out a little of your Chosen of the Gods charisma and schmooze them.";
  }

  @Override
  public int getRoll() {
    return 2;
  }
}