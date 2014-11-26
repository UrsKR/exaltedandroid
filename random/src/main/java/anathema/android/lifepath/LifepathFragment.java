package anathema.android.lifepath;


import anathema.android.DiceAndCoins;
import anathema.android.GeneratorFragment;
import anathema.android.R;
import anathema.android.util.FileToString;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//http://lovethelabyrinth.blogspot.de/2014/11/random-exalted-fashion.html
public class LifepathFragment extends GeneratorFragment {

  private TextView lifepathView;

  public LifepathFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_lifepath, container, false);
    lifepathView = (TextView) view.findViewById(R.id.text_lifepath);
    return view;
  }

  public void generate(DiceAndCoins diceAndCoins) {
    Lifepath lifepath = new GenerateLifepath(diceAndCoins, new FileToString(getActivity().getAssets())).generate();
    lifepathView.setText(lifepath.asText());
  }
}