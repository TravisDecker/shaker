package com.shaker.shaker.view;


import static android.support.constraint.Constraints.TAG;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.shaker.shaker.R;
import com.shaker.shaker.model.entity.Quake;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

  private Context context;
  private List<Quake> quakes;

  public RecyclerViewAdapter(List<Quake> quakes, Context context) {
    this.quakes = quakes;
    this.context = context;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.layout_listitem, viewGroup, false);
    ViewHolder holder = new ViewHolder(view);
    return holder;
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
    Log.d(TAG, "onBindViewHolder: called");
    holder.bind(quakes.get(i));
  }

  @Override
  public int getItemCount() {
    return quakes.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    CardView card;
    TextView titleText;
    TextView alertText;
    TextView depthText;



    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      card = itemView.findViewById(R.id.shake_card);
      titleText = itemView.findViewById(R.id.title_text);
      alertText = itemView.findViewById(R.id.alert_text);
      depthText = itemView.findViewById(R.id.depth_text);

    }

    public void bind(Quake quake) {
      titleText.setText(quake.getTitle());
      alertText.setText("Alert Code: " + quake.getAlert());
      depthText.setText("Depth " + Double.toString(quake.getDepth()) + " km");
    }
  }

}
