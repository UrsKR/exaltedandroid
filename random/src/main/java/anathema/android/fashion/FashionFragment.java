package anathema.android.fashion;


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
public class FashionFragment extends GeneratorFragment {

  private TextView fashionView;

  public FashionFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_fashion, container, false);
    fashionView = (TextView) view.findViewById(R.id.text_fashion);
    return view;
  }

  public void generate(DiceAndCoins diceAndCoins) {
    Fashion fashion = new GenerateFashion(diceAndCoins, new FileToString(getActivity().getAssets())).generate();
    fashionView.setText(fashion.asText());
  }
}