package anathema.android.util;

import anathema.android.DiceAndCoins;
import anathema.android.Flip;

public class Person {



  public static Person randomize(DiceAndCoins diceAndCoins) {
    Flip flip = diceAndCoins.flipACoin();
    Person person = new Person();
    if (flip == Flip.Head) {
      person.personal = "he";
      person.possessiveDeterminer = "his";
      person.possessivePronoun = "his";
    } else {
      person.personal = "she";
      person.possessiveDeterminer = "her";
      person.possessivePronoun = "hers";
    }
    return person;
  }

  public String personal;
  public String possessiveDeterminer;
  public String possessivePronoun;
}