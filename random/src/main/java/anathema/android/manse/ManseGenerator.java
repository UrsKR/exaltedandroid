package anathema.android.manse;

import anathema.android.DiceAndCoins;
import anathema.android.Generator;
import anathema.android.R;
import anathema.android.Result;
import android.content.Context;

//http://lovethelabyrinth.blogspot.de/2014/11/this-old-manse.html
public class ManseGenerator implements Generator {
  private Context context;

  public ManseGenerator(Context context) {
    this.context = context;
  }

  @Override
  public Result generate(DiceAndCoins diceAndCoins) {
    ManseSpecialty specialty = new GenerateManse(diceAndCoins).generate();
    Result result = new Result();
    result.title = specialty.getCaption();
    result.text = context.getString(R.string.label_risk)+": " + specialty.getRisk() + "\n\n" + context.getString(R.string.label_reward)+": " + specialty.getReward() + "\n\n" + specialty.getDetails();
    return result;
  }
}