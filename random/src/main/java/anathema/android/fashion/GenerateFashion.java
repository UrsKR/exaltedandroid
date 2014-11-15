package anathema.android.fashion;

import anathema.android.DiceAndCoins;
import anathema.android.Flip;

import java.util.HashMap;
import java.util.Map;

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
    if (roll == 13 || roll == 14) {
      String unitSymbol = rollPattern();
      return "{0} presents a uniform with a {2} " + unitSymbol + " on its high collar.";
    }
    if (roll > 19) {
      return rollSecondaryPiece();
    } else {
      Map<Integer, String> piece = new HashMap<>();
      piece.put(1, "{0} wears nothing but {1} bare chest.");
      piece.put(2, "{0} keeps {1} body wrapped in bandages.");
      piece.put(3, "{0} is protected by smoothly polished tight leathers.");
      piece.put(4, "{0} features tight leathers, barbed with metal.");
      piece.put(5, "{0} is dressed in loose monk robes.");
      piece.put(6, "{0} is dressed in loose monk robes.");
      piece.put(7, "{0} sports parade armor with an intricately designed breastplate.");
      piece.put(8, "{0} displays parade armor with massive interlocking shoulder plates.");
      piece.put(9, "{0} dons a flowing dress.");
      piece.put(10, "{0} presents a flowing toga.");
      piece.put(11, "{0} wears a worker's leather slacks, covered by a smock.");
      piece.put(12, "{0} is dressed in a worker's denim slacks, covered by a smock.");
      piece.put(15, "{0} wrapped {1} body in chain, tight enough to count as clothing.");
      piece.put(16, "{0} is covered covered by plates, hiding {1} very humanity.");
      piece.put(17, "{0} is decked out in bones and symbols of death.");
      piece.put(18, "{0} is wrapped in vines and foliage.");
      return piece.get(roll);
    }
  }

  private String rollSecondaryPiece() {
    int roll = rollD20();
    if (roll == 15 || roll == 16) {
      String pattern = rollPatternAdjective();
      return pattern + " symbols give {1} appearance a spiritual air.";
    }
    if (roll > 19) {
      return rollPrimaryPiece();
    }
    Map<Integer, String> piece = new HashMap<>();
    piece.put(1, "{0} wears a kilt instead of more traditional armor.");
    piece.put(2, "{0} wears a kilt instead of trousers.");
    piece.put(3, "{1} arms are protected by leather wraps.");
    piece.put(4, "{1} legs are covered by leather wraps.");
    piece.put(5, "Tight bands cover {1} extremities, giving {1} a martial artist's look.");
    piece.put(6, "Wraps of cloth, oft sported by martial artists, hide {1} arms and legs.");
    piece.put(7, "Pouches and bags are sewn into every piece of {1} outfit. Who knows what {0} is hiding?");
    piece.put(8, "Pouches and bags are sewn into every piece of {1} outfit. Who knows what {0} is hiding?");
    piece.put(9, "{1} belly is hemmed in by a cummerbund.");
    piece.put(10, "{1} midriff is tied with a heavy belt.");
    piece.put(11, "A flowing cape accentuates {1} every gesture.");
    piece.put(12, "A trench coat hides most of it, though.");
    piece.put(13, "{1} shoulder cape adds to {1} well-traveled look.");
    piece.put(14, "Whatever else {0} might wear is hidden by a poncho.");
    piece.put(17, "Almost as prominent is {1} massive utility vest, stuffed with tools.");
    piece.put(18, "Almost as prominent is {1} massive utility vest, stuffed with tools.");
    return piece.get(roll);
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
    String[] colors = new String[]{"red", "orange", "yellow", "green", "blue", "indigo", "violet", "black", "white", "grey"};
    return colors[roll - 1];
  }

  private String rollMaterialColor() {
    int roll = rollD10();
    String[] colors = new String[]{"ruby", "amber", "orichalcum", "emerald", "starmetal", "sapphire", "amethyst", "soulsteel", "moonsilver", "obsidian"};
    return colors[roll - 1];
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