package anathema.android.manse;

public class OnLoan implements ManseSpecialty {
  @Override
  public String getCaption() {
    return "On Loan";
  }

  @Override
  public String getRisk() {
    return "You are on call to deal with whatever the loaner requests of you for the power they are granting you.";
  }

  @Override
  public String getReward() {
    return "The reward is specific to who you have been given the loan from.";
  }

  @Override
  public String getDetails() {
    return "Your manse belongs to someone else. Your mentor, your sifu, your allies and they have loaned the hearthstone to you with strings attached. A Sidereal sifu might grant you more training in the Manse on your downtime. A Deathlord may provide Necromancy training if you carry out his wishes. The list goes on. Even Realm born dragonbloods can curry favor with their house, or patron by using the hearthstone for their causes. It may seem simple but it's a great way to make the Manse plot important.";
  }

  @Override
  public int getRoll() {
    return 8;
  }
}