package anathema.android.flashbacks;

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

//http://lovethelabyrinth.blogspot.de/2014/11/the-random-1st-age-flashback-generator.html
public class FlashbackFragment extends GeneratorFragment {

  private TextView flashbackView;

  public FlashbackFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_flashback, container, false);
    flashbackView = (TextView) view.findViewById(R.id.text_flashback);
    return view;
  }

  public void generate(DiceAndCoins diceAndCoins) {
    String flashback = new PatternBasedGenerator(diceAndCoins, new FileToString(getActivity().getAssets()), "flashbacks", "flashbackpattern").generate();
    flashbackView.setText(flashback);
  }
}