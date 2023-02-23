package com.shaker.shaker.view;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
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

/**
 * The Recycler view adapter class.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

  private Fragment fragment;
  private List<Feature> features;
  private Context context;

  /**
   * Instantiates a new Recycler view adapter.
   *
   * @param features the features
   * @param context the context
   * @param fragment the fragment
   */
  RecyclerViewAdapter(List<Feature> features, Context context, Fragment fragment) {
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


  /**
   * The View holder class.
   */
  class ViewHolder extends RecyclerView.ViewHolder {

    private CardView card;
    private TextView placeText;
    private TextView timeText;
    private TextView magText;
    private TextView depthText;
    private Properties properties;

    private ViewHolder(@NonNull View itemView) {
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

    private void bind(Feature feature) {
      Geometry geometry = feature.getGeometry();
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
