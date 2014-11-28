package anathema.android;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TitleTextFragment extends Fragment {

  private TextView nameView;
  private TextView resultView;

  public TitleTextFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_titletext, container, false);
    nameView = (TextView) view.findViewById(R.id.text_name);
    resultView = (TextView) view.findViewById(R.id.text_detail);
    return view;
  }

  public void setTitle(String title){
    nameView.setText(title);
  }
  
  public void setText(String text){
    resultView.setText(text);
  }
}