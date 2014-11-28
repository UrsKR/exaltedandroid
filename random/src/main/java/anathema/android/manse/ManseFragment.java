package anathema.android.manse;

import anathema.android.DiceAndCoins;
import anathema.android.GeneratorFragment;
import anathema.android.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//http://lovethelabyrinth.blogspot.de/2014/11/this-old-manse.html
public class ManseFragment extends GeneratorFragment {

  private TextView nameView;
  private TextView resultView;

  public ManseFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_manse, container, false);
    nameView = (TextView) view.findViewById(R.id.text_name);
    resultView = (TextView) view.findViewById(R.id.text_detail);
    return view;
  }

  public void generate(DiceAndCoins diceAndCoins) {
    ManseSpecialty specialty = new GenerateManse(diceAndCoins).generate();
    nameView.setText(specialty.getCaption());
    resultView.setText("Risk: " + specialty.getRisk() + "\n\n" + "Reward: " + specialty.getReward() + "\n\n" + specialty.getDetails());
  }
}