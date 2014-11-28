package anathema.android.fashion;

import anathema.android.DiceAndCoins;
import anathema.android.Generator;
import anathema.android.R;
import anathema.android.Result;
import anathema.android.util.FileToString;
import android.content.Context;

//http://lovethelabyrinth.blogspot.de/2014/11/random-exalted-fashion.html
public class FashionGenerator implements Generator {
  private Context context;

  public FashionGenerator(Context context) {
    this.context = context;
  }

  @Override
  public Result generate(DiceAndCoins diceAndCoins) {
    Fashion fashion = new GenerateFashion(diceAndCoins, new FileToString(context.getAssets())).generate();
    Result result = new Result();
    result.title = context.getString(R.string.title_fashion);
    result.text = fashion.asText();
    return result;
  }
}