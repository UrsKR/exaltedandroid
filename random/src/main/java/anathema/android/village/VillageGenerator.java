package anathema.android.village;

import anathema.android.DiceAndCoins;
import anathema.android.Generator;
import anathema.android.R;
import anathema.android.Result;
import anathema.android.util.FileToString;
import anathema.android.util.PatternBasedGenerator;
import android.content.Context;

//http://lovethelabyrinth.blogspot.de/2014/10/exalted-3e-what-is-wrong-with-this.html
public class VillageGenerator implements Generator {
  private Context context;

  public VillageGenerator(Context context) {
    this.context = context;
  }

  @Override
  public Result generate(DiceAndCoins diceAndCoins) {
    String village = new PatternBasedGenerator(diceAndCoins, new FileToString(context.getAssets()), "village").generate();
    Result result = new Result();
    result.title = context.getString(R.string.title_village);
    result.text = village;
    return result;
  }
}
