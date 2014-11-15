package anathema.android.manse;

public class GuildAwareness implements ManseSpecialty {
  @Override
  public String getCaption() {
    return "Guild Awareness";
  }

  @Override
  public String getRisk() {
    return "The Guild may take over your territory from you, and possibly use your Manse to house things you don't like, such as slaves, drugs, stolen goods.";
  }

  @Override
  public String getReward() {
    return "You could establish connections with the Guild, outlining your rules of use.";
  }

  @Override
  public String getDetails() {
    return "Much like squatters it is risky to allow the Guild to use your manse in any way. But then again they would be one of the few groups who would have resources you'd need to rebuild or enhance it over time. Over time the guild will use your Manse as a supply depot, and trading post. It acts as a great cover for you manse and you can skim a reasonable 'fee' off the top of the caravans moving through.";
  }

  @Override
  public int getRoll() {
    return 3;
  }
}