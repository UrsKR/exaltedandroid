package anathema.android.manse;

import java.io.Serializable;

public interface ManseSpecialty extends Serializable {
  
  String getCaption();
  String getRisk();
  String getReward();
  String getDetails();

  int getRoll();
}