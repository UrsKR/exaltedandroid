package anathema.android.flashbacks;

import anathema.android.DiceAndCoins;
import anathema.android.Generator;
import anathema.android.R;
import anathema.android.Result;
import anathema.android.util.FileToString;
import anathema.android.util.PatternBasedGenerator;
import android.content.Context;

//http://lovethelabyrinth.blogspot.de/2014/11/the-random-1st-age-flashback-generator.html
public class FlashbackGenerator implements Generator {
  private Context context;

  public FlashbackGenerator(Context context) {
    this.context = context;
  }

  @Override
  public Result generate(DiceAndCoins diceAndCoins) {
    String flashback = new PatternBasedGenerator(diceAndCoins, new FileToString(context.getAssets()), "flashbacks").generate();
    Result result = new Result();
    result.title = context.getString(R.string.title_flashbacks);
    result.text = flashback;
    return result;
  }
}
