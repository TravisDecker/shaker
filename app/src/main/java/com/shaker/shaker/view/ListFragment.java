package com.shaker.shaker.view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shaker.shaker.R;
import com.shaker.shaker.controller.MainActivity;
import com.shaker.shaker.controller.MainActivity.QueryCallback;
import com.shaker.shaker.model.entity.Feature;
import java.util.ArrayList;
import java.util.List;

/**
 * The List fragment.
 */
public class ListFragment extends Fragment {

  private static final String KEY = "key";
  /**
   * The constant fragment.
   */
  public static ListFragment fragment;
  private static Bundle bundle;

  private RecyclerView.Adapter adapter;
  private List<Feature> features;

  /**
   * New instance list fragment.
   *
   * @param filter the filter
   * @return the list fragment
   */
  public static ListFragment newInstance(String filter) {
    if (fragment == null) {
      fragment = new ListFragment();
      bundle = new Bundle();
      bundle.putString(KEY, filter);
    }
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    String filter = bundle.getString(KEY);
    features = new ArrayList<>();
    View view = inflater.inflate(R.layout.list_fragment, container, false);
    RecyclerView shakesView = view.findViewById(R.id.recyclerview);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
    shakesView.setLayoutManager(layoutManager);
    adapter = new RecyclerViewAdapter(features, getActivity(), this);
    shakesView.setAdapter(adapter);
    ((MainActivity) getActivity()).queryShakes((QueryCallback<Feature>) features -> {
      updateList(features);
    }, filter);
    return view;
  }

  /**
   * Update list.
   *
   * @param features the features
   */
  public void updateList(List<Feature> features) {
    ListFragment.this.features.clear();
    ListFragment.this.features.addAll(features);
    adapter.notifyDataSetChanged();
  }
}
