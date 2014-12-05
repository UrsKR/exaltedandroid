package anathema.android;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {
  private final List<Result> dataset;

  public ResultAdapter(List<Result> dataset) {
    this.dataset = dataset;
  }

  @Override
  public ResultAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_result, viewGroup, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ResultAdapter.ViewHolder viewHolder, int position) {
    Result result = dataset.get(position);
    viewHolder.nameView.setText(result.title);
    viewHolder.detailView.setText(result.text);
  }

  @Override
  public int getItemCount() {
    return dataset.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    private TextView nameView;
    public TextView detailView;

    public ViewHolder(View view) {
      super(view);
      nameView = (TextView) view.findViewById(R.id.text_name);
      detailView = (TextView) view.findViewById(R.id.text_detail);
    }
  }
}
