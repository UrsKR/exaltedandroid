package anathema.android.name;

import anathema.android.DiceAndCoins;
import anathema.android.Generator;
import anathema.android.R;
import anathema.android.Result;
import anathema.android.util.FileToString;
import anathema.android.util.PatternBasedGenerator;
import android.content.Context;

public class NameGenerator implements Generator {
  private Context context;

  public NameGenerator(Context context) {
    this.context = context;
  }

  @Override
  public Result generate(DiceAndCoins diceAndCoins) {
    String name = new PatternBasedGenerator(diceAndCoins, new FileToString(context.getAssets()), "name").generate();
    Result result = new Result();
    result.title = context.getString(R.string.title_name);
    result.text = name;
    return result;
  }
}
