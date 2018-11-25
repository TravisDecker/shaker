package com.shaker.shaker.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.shaker.shaker.R;
import com.shaker.shaker.model.entity.Feature;
import com.shaker.shaker.model.entity.Geometry;
import com.shaker.shaker.model.entity.Properties;
import java.util.Date;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

  private Fragment fragment;
  private List<Feature> features;
  private Context context;

  public RecyclerViewAdapter(List<Feature> features, Context context, Fragment fragment) {
    this.features = features;
    this.context = context;
    this.fragment = fragment;
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
    holder.bind(features.get(i));
  }

  @Override
  public int getItemCount() {
    return features.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    CardView card;
    TextView placeText;
    TextView timeText;
    TextView magText;
    TextView depthText;
    Properties properties;

    ViewHolder(@NonNull View itemView) {
      super(itemView);
      card = itemView.findViewById(R.id.shake_card);
      card.setOnClickListener((view) -> {
        FragmentManager manager = fragment.getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_container, ShakeFragment.newInstance(properties), null);
        transaction.addToBackStack(null);
        transaction.commit();
      });
      placeText = itemView.findViewById(R.id.place_text);
      timeText = itemView.findViewById(R.id.time_text);
      magText = itemView.findViewById(R.id.mag_text);
      depthText = itemView.findViewById(R.id.depth_text);
    }

    void bind(Feature feature) {
      Geometry geometry = feature.getGeometry();
      // TODO decide where to use geometry
      properties = feature.getProperties();
      placeText.setText(properties.getPlace());
      timeText.setText(new Date(properties.getTime()).toString());
      String mag = context.getString(R.string.magnitude) + properties.getMag().toString();
      magText.setText(mag);
      String depth =
          context.getString(R.string.depth) + geometry.getDepth().toString() + context.getString(
              R.string.km);
      depthText.setText(depth);
    }
  }
}
