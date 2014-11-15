package anathema.android.fashion;

import anathema.android.DiceAndCoins;
import anathema.android.Flip;

import static anathema.android.Flip.Tails;

public class GenerateFashion {
  private DiceAndCoins diceAndCoins;

  public GenerateFashion(DiceAndCoins diceAndCoins) {
    this.diceAndCoins = diceAndCoins;
  }

  public Fashion generate() {
    Fashion fashion = new Fashion();
    rollWearer(fashion);
    fashion.primaryPiece = rollPrimaryPiece();
    fashion.secondaryPiece = rollSecondaryPiece();
    fashion.primaryAccessory = rollPrimaryAccessory();
    //rollSecondaryAccessory();
    //rollHair();
    fashion.primaryColor = rollAnyColor();
    fashion.highlightColor = rollAnyColor();
    return fashion;
  }

  private String rollPrimaryAccessory() {
    /*
    Primary Accessory (Time to add some flair)
    Military War Fan (Folding/Solid)
    Fully Body Jewelry (Roll on Pattern Table for design)
    Prayer Beads
    Detail Body Tattoo (Roll on Pattern Table and Color Table)
    Detailed Clothing Pattern (Roll on Pattern Table and Color Table)
    Long Scarf/Obi (Belly Wrap)
    Impressive Sandals/Boots
    Religious/Family Tabard (Roll on Pattern and Color Table for design and primary color.)
    Multiple Piercings (Metallic/Bone)
    Roll on Secondary Accessory Table and use that result as your primary element. If it has a contradictory element consider them layered either in different locations or mingled together as one. Ignore duplicate rolls or 10 again.
            */
    return null;
  }

  private void rollWearer(Fashion fashion) {
    Flip flip = flipCoin();
    if (flip == Flip.Head) {
      fashion.personal = "he";
      fashion.possessive = "his";
    } else {
      fashion.personal = "she";
      fashion.possessive = "her";
    }
  }

  private String rollPrimaryPiece() {
    int roll = rollD20();
    switch (roll) {
      case 1:
        return "{0} wears nothing but {1} bare chest.";
      case 2:
        return "{0} keeps {1} body wrapped in bandages.";
      case 3:
        return "{0} is protected by smoothly polished tight leathers.";
      case 4:
        return "{0} features tight leathers, barbed with metal.";
      case 5:
      case 6:
        return "{0} is dressed in loose monk robes.";
      case 7:
        return "{0} sports parade armor with an intricately designed breastplate.";
      case 8:
        return "{0} displays parade armor with massive interlocking shoulder plates.";
      case 9:
        return "{0} dons a flowing dress.";
      case 10:
        return "{0} presents a flowing toga.";
      case 11:
        return "{0} wears a worker's leather slacks, covered by a smock.";
      case 12:
        return "{0} is dressed in a worker's denim slacks, covered by a smock.";
      case 13:
      case 14:
        String unitSymbol = rollPattern();
        return "{0} presents a uniform with a {2} " + unitSymbol + " on its high collar.";
      case 15:
        return "{0} wrapped {1} body in chain, tight enough to count as clothing.";
      case 16:
        return "{0} is covered covered by plates, hiding {1} very humanity.";
      case 17:
        return "{0} is decked out in bones and symbols of death.";
      case 18:
        return "{0} is wrapped in vines and foliage.";
      case 19:
      case 20:
        return rollSecondaryPiece();
    }
    throw new IllegalArgumentException("Roll out of range [1,20]");
  }

  private String rollSecondaryPiece() {
    int roll = rollD20();
    switch (roll) {
      case 1:
      case 2:
        return "{0} wears a kilt instead of trousers or more traditional armor.";
      case 3:
        return "{1} arms are protected by leather wraps.";
      case 4:
        return "{1} legs are covered by leather wraps.";
      case 5:
        return "Tight martial arts bands cover {1} extremities.";
      case 6:
        return "Wraps of cloth, oft sported by martial artists, hide {1} arms and legs.";
      case 7:
      case 8:
        return "Pouches and bags are sewn into every piece of {1} outfit. Who knows what {0} is hiding?";
      case 9:
        return "{1} belly is hemmed in by a cummerbund.";
      case 10:
        return "{1} midriff is tied with a heavy belt.";
      case 11:
        return "A flowing cape accentuates {1} every gesture.";
      case 12:
        return "A trench coat hides most of it, though.";
      case 13:
        return "{1} shoulder cape adds to {1} well-traveled look.";
      case 14:
        return "Whatever else {0} might wear is hidden by a poncho.";
      case 15:
      case 16:
        String pattern = rollPatternAdjective();
        return pattern + " symbols give {1} appearance a spiritual air.";
      case 17:
      case 18:
        return "Almost as prominent is {1} massive utility vest, stuffed with tools.";
      case 19:
      case 20:
        return rollPrimaryPiece();
    }
    throw new IllegalArgumentException("Roll out of range [1,20]");
  }

  private String rollPattern() {
    int roll = rollD10();
    switch (roll) {
      case 1:
        return "dragon";
      case 2:
        return "demon";
      case 3:
        return "elemental";
      case 4:
        if (flipCoin() == Tails) {
          return "ghost";
        }
        return "skull";
      case 5:
        if (flipCoin() == Tails) {
          return "fey creature";
        }
        return "fairy noble";
      case 6:
        return "sun";
      case 7:
        return "moon";
      case 8:
        return "star";
      case 9:
        if (flipCoin() == Tails) {
          return "heptagram";
        }
        return "pentagram";
      case 10:
        if (flipCoin() == Tails) {
          return "animal";
        }
        return "plant";
    }
    throw new IllegalArgumentException("Roll out of range [1,10]");
  }

  private String rollPatternAdjective() {
    int roll = rollD10();
    switch (roll) {
      case 1:
        return "draconic";
      case 2:
        return "demonic";
      case 3:
        return "elemental";
      case 4:
        return "ghostly";
      case 5:
        return "fey";
      case 6:
        return "solar";
      case 7:
        return "lunar";
      case 8:
        return "astrologic";
      case 9:
        return "geometric";
      case 10:
        return "animalic";
    }
    throw new IllegalArgumentException("Roll out of range [1,10]");
  }

  private String rollAnyColor() {
    Flip flipCoin = flipCoin();
    if (flipCoin == Tails) {
      return rollPrismaticColor();
    }
    return rollMaterialColor();
  }


  private String rollPrismaticColor() {
    int roll = rollD10();
    switch (roll) {
      case 1:
        return "red";
      case 2:
        return "orange";
      case 3:
        return "yellow";
      case 4:
        return "green";
      case 5:
        return "blue";
      case 6:
        return "indigo";
      case 7:
        return "violet";
      case 8:
        return "black";
      case 9:
        return "white";
      case 10:
        return "grey";
    }
    throw new IllegalArgumentException("Roll out of range [1,10]");
  }

  private String rollMaterialColor() {
    int roll = rollD10();
    switch (roll) {
      case 1:
        return "ruby";
      case 2:
        return "amber";
      case 3:
        return "orichalcum";
      case 4:
        return "emerald";
      case 5:
        return "starmetal";
      case 6:
        return "sapphire";
      case 7:
        return "amethyst";
      case 8:
        return "soulsteel";
      case 9:
        return "moonsilver";
      case 10:
        return "obsidian";
    }
    throw new IllegalArgumentException("Roll out of range [1,10]");
  }

  private Flip flipCoin() {
    return diceAndCoins.flipACoin();
  }

  private int rollD10() {
    return diceAndCoins.rollTenSidedDie();
  }

  private int rollD20() {
    return diceAndCoins.rollTwentySidedDie();
  }
}