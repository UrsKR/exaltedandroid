package anathema.android.util;

import anathema.android.DiceAndCoins;
import anathema.android.Flip;

public class Person {

  public static Person randomize(DiceAndCoins diceAndCoins) {
    Flip flip = diceAndCoins.flipACoin();
    Person person = new Person();
    if (flip == Flip.Head) {
      person.personal = "he";
      person.possessive = "his";
    } else {
      person.personal = "she";
      person.possessive = "her";
    }
    return person;
  }

  public String personal;
  public String possessive;
}