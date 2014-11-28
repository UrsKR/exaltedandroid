package anathema.android.flashbacks;

import anathema.android.DiceAndCoins;
import anathema.android.Generator;
import anathema.android.Result;
import anathema.android.util.FileToString;
import anathema.android.util.PatternBasedGenerator;
import android.content.res.AssetManager;

//http://lovethelabyrinth.blogspot.de/2014/11/the-random-1st-age-flashback-generator.html
public class FlashbackGenerator implements Generator {
  private AssetManager assets;

  public FlashbackGenerator(AssetManager assets) {
    this.assets = assets;
  }

  @Override
  public Result generate(DiceAndCoins diceAndCoins) {
    String flashback = new PatternBasedGenerator(diceAndCoins, new FileToString(assets), "flashbacks").generate();
    Result result = new Result();
    result.title = "Flashback";
    result.text = flashback;
    return result;
  }
}
