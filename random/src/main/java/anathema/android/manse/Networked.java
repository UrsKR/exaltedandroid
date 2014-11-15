package anathema.android.manse;

public class Networked implements ManseSpecialty {
  @Override
  public String getCaption() {
    return "Networked";
  }

  @Override
  public String getRisk() {
    return "The owners of the other Manses may wish to take yours over to link the network completely to reap whatever benefit the total network grants.";
  }

  @Override
  public String getReward() {
    return "Obviously, taking control of the linked Manses either by force or negotiation grants the same meta benefit to you.";
  }

  @Override
  public String getDetails() {
    return "Eh? It's been done in the past where Manses may be part of a larger network of geomatnic lines. The Realm Defense Network is one such example, but there is nothing saying that other Manses may not be linked in a thaumatological manner to another. It could be a localized defense grid, a forge power plant, some form of sorcery enhancement. Anything. ";
  }

  @Override
  public int getRoll() {
    return 9;
  }
}
