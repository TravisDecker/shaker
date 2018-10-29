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
import android.widget.LinearLayout;
import android.widget.TextView;
import com.shaker.shaker.R;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

  private ArrayList<String> sInfoText;
  private ArrayList<String> sMoreInfoText;
  private Context context;

  public RecyclerViewAdapter(ArrayList<String> sInfoText,
      ArrayList<String> sMoreInfoText, Context context) {
    this.sInfoText = sInfoText;
    this.sMoreInfoText = sMoreInfoText;
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
    holder.infotext.setText(sInfoText.get(i));
    // TODO this varies based on what your displaying, import data?
  }

  @Override
  public int getItemCount() {
    return sInfoText.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    CardView card;
    TextView infotext;
    LinearLayout parent_layout;


    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      card = itemView.findViewById(R.id.shake_card);
      infotext = itemView.findViewById(R.id.info_text);
      parent_layout = itemView.findViewById(R.id.parent_layout);
    }
  }

}
