package anathema.android.manse;

public class HistoricalLandmark implements ManseSpecialty {
  @Override
  public String getCaption() {
    return "Historical Landmark";
  }

  @Override
  public String getRisk() {
    return "Outsiders might report the Exalt's coming and goings from the building. This works against you if the Wyld Hunt is after you, or you want to stay covert like Lunars and Sidereals thrive on.";
  }

  @Override
  public String getReward() {
    return "The reward is manifold. You can cultivate a cult, a following, maybe have the site act a focal point for your spy network.";
  }

  @Override
  public String getDetails() {
    return "Manses are very, very important locations. More so if they play into the history of a location. If the Exalts manse is located near populated lands there is good odds the building is a local landmark and is visited by travelers who pray, study, or just enjoy it's beautiful design. You have to come up with ways of masking the Manse true use with some local traditions and social networking. But it can pay off down the line. It makes the locals want to protect the site from outsiders.";
  }

  @Override
  public int getRoll() {
    return 5;
  }
}
