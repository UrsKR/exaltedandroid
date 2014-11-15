package anathema.android.fashion;


import anathema.android.DiceAndCoins;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import anathema.android.R;
import android.widget.TextView;

//http://lovethelabyrinth.blogspot.de/2014/11/random-exalted-fashion.html
public class FashionFragment extends Fragment {

  private TextView fashionView;
  private TextView fashionTitleView;

  public FashionFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_fashion, container, false);
    fashionView = (TextView) view.findViewById(R.id.text_fashion);
    fashionTitleView = (TextView) view.findViewById(R.id.text_fashiontitle);
    return view;
  }

  public void generateFashion(DiceAndCoins diceAndCoins) {
    Fashion fashion = new GenerateFashion(diceAndCoins).generate();
    fashionTitleView.setText("Fashion");
    fashionView.setText(fashion.asText());
  }
}