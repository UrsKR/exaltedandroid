package anathema.android.lifepath;

import anathema.android.DiceAndCoins;
import anathema.android.Generator;
import anathema.android.R;
import anathema.android.Result;
import anathema.android.util.FileToString;
import android.content.Context;

//http://lovethelabyrinth.blogspot.de/2014/10/exalted-3e-lifepathing-your-character.html
public class LifepathGenerator implements Generator {
  private Context context;

  public LifepathGenerator(Context context) {
    this.context = context;
  }

  @Override
  public Result generate(DiceAndCoins diceAndCoins) {
    String lifepath = new GenerateLifepath(diceAndCoins, new FileToString(context.getAssets())).generate();
    Result result = new Result();
    result.title = context.getString(R.string.title_lifepath);
    result.text = lifepath;
    return result;
  }
}