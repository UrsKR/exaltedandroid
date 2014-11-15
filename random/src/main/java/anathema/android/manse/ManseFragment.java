package anathema.android.manse;

import anathema.android.DiceAndCoins;
import anathema.android.R;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

//http://lovethelabyrinth.blogspot.de/2014/11/this-old-manse.html
public class ManseFragment extends Fragment {

  private TextView nameView;
  private TextView resultView;
  private TextView riskView;
  private TextView rewardView;

  public ManseFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_manse, container, false);
    nameView = (TextView) view.findViewById(R.id.text_name);
    resultView = (TextView) view.findViewById(R.id.text_detail);
    rewardView = (TextView) view.findViewById(R.id.text_reward);
    riskView = (TextView) view.findViewById(R.id.text_risk);
    return view;
  }

  public void generateManse(DiceAndCoins diceAndCoins) {
    ManseSpecialty specialty = new GenerateManse(diceAndCoins).generate();
    Context context = getActivity().getApplicationContext();
    Toast toast = Toast.makeText(context, context.getString(R.string.toast_dieroll)+ specialty.getRoll(), Toast.LENGTH_SHORT);
    toast.show();
    nameView.setText(specialty.getCaption());
    resultView.setText(specialty.getDetails());
    riskView.setText(specialty.getRisk());
    rewardView.setText(specialty.getReward());
  }
}