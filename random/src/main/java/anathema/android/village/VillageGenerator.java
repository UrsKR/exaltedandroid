package anathema.android.village;

import anathema.android.DiceAndCoins;
import anathema.android.Generator;
import anathema.android.Result;
import anathema.android.util.FileToString;
import anathema.android.util.PatternBasedGenerator;
import android.content.res.AssetManager;

//http://lovethelabyrinth.blogspot.de/2014/10/exalted-3e-what-is-wrong-with-this.html
public class VillageGenerator implements Generator {
  private AssetManager assets;

  public VillageGenerator(AssetManager assets) {
    this.assets = assets;
  }

  @Override
  public Result generate(DiceAndCoins diceAndCoins) {
    String village = new PatternBasedGenerator(diceAndCoins, new FileToString(assets), "village").generate();
    Result result = new Result();
    result.title = "Village";
    result.text = village;
    return result;
  }
}
