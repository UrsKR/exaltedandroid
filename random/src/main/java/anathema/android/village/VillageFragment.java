package anathema.android.village;


import anathema.android.DiceAndCoins;
import anathema.android.GeneratorFragment;
import anathema.android.R;
import anathema.android.util.FileToString;
import anathema.android.util.PatternBasedGenerator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//http://lovethelabyrinth.blogspot.de/2014/10/exalted-3e-what-is-wrong-with-this.html
public class VillageFragment extends GeneratorFragment {

  private TextView villageView;

  public VillageFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_village, container, false);
    villageView = (TextView) view.findViewById(R.id.text_village);
    return view;
  }

  public void generate(DiceAndCoins diceAndCoins) {
    String village = new PatternBasedGenerator(diceAndCoins, new FileToString(getActivity().getAssets()), "village", "villagepattern").generate();
    villageView.setText(village);
  }
}