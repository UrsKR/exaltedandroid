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
    PatternBasedGenerator generator = new PatternBasedGenerator(diceAndCoins, new FileToString(context.getAssets()),
            "name");
    StringBuilder names = new StringBuilder();
    for (int i = 0; i < 10; i++) {
      String name = generator.generate();
      names.append(name);
      names.append("\n");
    }
    Result result = new Result();
    result.title = context.getString(R.string.title_name);
    result.text = names.toString();
    return result;
  }
}
