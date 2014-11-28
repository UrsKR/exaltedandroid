package anathema.android.manse;

import anathema.android.DiceAndCoins;
import anathema.android.Generator;
import anathema.android.Result;

//http://lovethelabyrinth.blogspot.de/2014/11/this-old-manse.html
public class ManseGenerator implements Generator {
  @Override
  public Result generate(DiceAndCoins diceAndCoins) {
    ManseSpecialty specialty = new GenerateManse(diceAndCoins).generate();
    Result result = new Result();
    result.title = specialty.getCaption();
    result.text = "Risk: " + specialty.getRisk() + "\n\n" + "Reward: " + specialty.getReward() + "\n\n" + specialty.getDetails();
    return result;
  }
}