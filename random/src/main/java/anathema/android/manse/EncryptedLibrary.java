package anathema.android.manse;

public class EncryptedLibrary implements ManseSpecialty{
  @Override
  public String getCaption() {
    return "Encrypted Library";
  }

  @Override
  public String getRisk() {
    return "If you start looking for a cipher (be it artifact, blood line, or sorcery) someone is bound to find out an they might know WHY you are looking for the cipher. And if it has information they don't want getting out, you are bound to get company! Fast!";
  }

  @Override
  public String getReward() {
    return "The Exalt could discover lore, intel on family lines, secrets of new sorcery or necromancy that have not been rediscovered or shared with the world at large.";
  }

  @Override
  public String getDetails() {
    return "This one is something that Solar or paranoid Dragon Bloods might have  done. A manse is a great place to store you diary, research logs, or security files if (big part here) you encrypt it so no one but you can use it. The catch is you eventually die and suddenly all that lore is lost until someone finds the cipher. Imagine a Manse who's very walls are a massive spell book that you have to puzzle out each spell weeks at a time using rituals and very specific reagents. Not only do you get a fun adventure, but the spells to boot!";
  }

  @Override
  public int getRoll() {
    return 7;
  }
}
