package anathema.android.manse;

public class DangerousLocation implements ManseSpecialty {
  @Override
  public String getCaption() {
    return "Dangerous Location";
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
    return 6;
  }
}
