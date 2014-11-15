package anathema.android.manse;

public class HistoricalLandmark implements ManseSpecialty {
  @Override
  public String getCaption() {
    return "Historical Landmark";
  }

  @Override
  public String getRisk() {
    return null;
  }

  @Override
  public String getReward() {
    return null;
  }

  @Override
  public String getDetails() {
    return null;
  }

  @Override
  public int getRoll() {
    return 5;
  }
}
