package anathema.android.lifepath;

import anathema.android.DiceAndCoins;
import anathema.android.Generator;
import anathema.android.Result;
import anathema.android.util.FileToString;
import android.content.res.AssetManager;

//http://lovethelabyrinth.blogspot.de/2014/10/exalted-3e-lifepathing-your-character.html
public class LifepathGenerator implements Generator {
  private AssetManager assets;

  public LifepathGenerator(AssetManager assets) {
    this.assets = assets;
  }

  @Override
  public Result generate(DiceAndCoins diceAndCoins) {
    Lifepath lifepath = new GenerateLifepath(diceAndCoins, new FileToString(assets)).generate();
    Result result = new Result();
    result.title = "Lifepath";
    result.text = lifepath.asText();
    return result;
  }
}