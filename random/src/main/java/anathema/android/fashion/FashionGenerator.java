package anathema.android.fashion;

import anathema.android.DiceAndCoins;
import anathema.android.Generator;
import anathema.android.Result;
import anathema.android.util.FileToString;
import android.content.res.AssetManager;

//http://lovethelabyrinth.blogspot.de/2014/11/random-exalted-fashion.html
public class FashionGenerator implements Generator {
  private AssetManager assets;

  public FashionGenerator(AssetManager assets) {
    this.assets = assets;
  }

  @Override
  public Result generate(DiceAndCoins diceAndCoins) {
    Fashion fashion = new GenerateFashion(diceAndCoins, new FileToString(assets)).generate();
    Result result = new Result();
    result.title = "Fashion";
    result.text = fashion.asText();
    return result;
  }
}