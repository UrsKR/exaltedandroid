package anathema.android.lifepath;

import anathema.android.DiceAndCoins;
import anathema.android.util.FileToString;
import anathema.android.util.JsonRandomizer;
import anathema.android.util.PlaceholderResolver;

public class GenerateLifepath {
  private final DiceAndCoins diceAndCoins;
  private final JsonRandomizer randomizer;
  private final PlaceholderResolver resolver;
  private Lifepath lifepath;

  public GenerateLifepath(DiceAndCoins diceAndCoins, FileToString fileToString) {
    this.diceAndCoins = diceAndCoins;
    this.randomizer = new JsonRandomizer("lifepath", diceAndCoins, fileToString);
    this.resolver = new PlaceholderResolver(GenerateLifepath.class, this);
  }

  public Lifepath generate() {
    this.lifepath = new Lifepath();
    rollOrigin();
    lifepath.lifepath.append("\n");
    rollExaltation();
    lifepath.lifepath.append("\n");
    lifepath.lifepath.append("Now, forge your destiny!");
    return lifepath;
  }

  public void rollOrigin() {
    String generalOrigin = randomizer.pickElementFromJsonArray("generalOrigin");
    String origin = resolver.resolvePlaceholders(generalOrigin);
    lifepath.lifepath.append("You hail from ");
    lifepath.lifepath.append(origin);
    lifepath.lifepath.append(".");
  }

  public void rollExaltation() {
    String exaltation = resolver.resolvePlaceholders("When %rollExaltationTrigger%, you exalted as a %rollExaltationResult% and found yourself %rollExaltationFallout% shortly after.");
    lifepath.lifepath.append(exaltation);
  }

  public String rollExaltationTrigger() {
    String exaltationTrigger = randomizer.pickElementFromJsonArray("exaltationTrigger");
    String trigger = resolver.resolvePlaceholders(exaltationTrigger);
    return trigger;
  }

  public String rollAssassin() {
    return randomizer.pickElementFromJsonArray("assassin");
  }

  public String rollAngryCreature() {
    return randomizer.pickElementFromJsonArray("angryCreature");
  }

  public String rollConflict() {
    return randomizer.pickElementFromJsonArray("conflict");
  }

  public String rollHorror() {
    return randomizer.pickElementFromJsonArray("horror");
  }

  public String rollJourney() {
    return randomizer.pickElementFromJsonArray("journey");
  }

  public String rollExaltationResult() {
    return randomizer.pickElementFromJsonArray("exaltationResult");
  }

  public String rollExaltationFallout() {
    int roll = diceAndCoins.rollTenSidedDie();
    switch (roll) {
      case 1:
        addTrait("Charms to improve your reputation");
        addTrait("Charms to stay out of sight");
        return "hunted by Immaculates";
      case 2:
        addTrait("a Cult");
        addTrait("some Followers");
        return "worshipped by a local village";
      case 3:
        addTrait("Martial Arts Charms");
        return "mentored by a Sidereal";
      case 4:
        addTrait("Artifacts");
        return "supported by a Deathlord";
      case 5:
        addTrait("social influence");
        return "in an alliance with a local God";
      case 6:
        addTrait("a Manse");
        return "drawn by dreams to a lost Manse";
      case 7:
        addTrait("a Familiar");
        addTrait("Charms to interact with beasts");
        return "bonded with a local beast";
      case 8:
        addTrait("an Ally");
        return "in the arms of your Lunar mate";
      case 9:
        addTrait("Sorcery");
        return "awakening to a deeper understanding of Creation";
      case 10:
        addTrait("merits to represent you Mortal cover");
        return "hidden from Creation's eye, remaining secret to everyone";
      default:
        throw new IllegalArgumentException("Die roll out of bounds [1,10].");
    }
  }

  private void addTrait(String object) {
    lifepath.suggestedTraits.add(object);
  }

  public String rollBeastmanOrigin() {
    addTrait("racial merits");
    return randomizer.pickElementFromJsonArray("beastmanOrigin");
  }

  public String rollBarbarianOrigin() {
    return randomizer.pickElementFromJsonArray("barbarianOrigin");
  }

  public String rollShadowlandOrigin() {
    return randomizer.pickElementFromJsonArray("shadowlandOrigin");
  }

  public String rollNorthernOrigin() {
    return randomizer.pickElementFromJsonArray("northernOrigin");
  }

  public String rollEasternOrigin() {
    return randomizer.pickElementFromJsonArray("easternOrigin");
  }

  public String rollWesternOrigin() {
    return randomizer.pickElementFromJsonArray("westernOrigin");
  }

  public String rollSouthernOrigin() {
    return randomizer.pickElementFromJsonArray("southernOrigin");
  }

  public String rollSoutheastOrigin() {
    return randomizer.pickElementFromJsonArray("southeastOrigin");
  }

  public String rollSouthwestOrigin() {
    return randomizer.pickElementFromJsonArray("southwestOrigin");
  }
}