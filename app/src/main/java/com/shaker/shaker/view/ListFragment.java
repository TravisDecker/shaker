package com.shaker.shaker.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shaker.shaker.R;
import com.shaker.shaker.controller.MainActivity;
import com.shaker.shaker.controller.MainActivity.QueryCallback;
import com.shaker.shaker.model.entity.Feature;
import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

  private static final String KEY = "key";
  public static ListFragment fragment;
  private static Bundle bundle;

  private RecyclerView.Adapter adapter;
  private List<Feature> features;

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

  public void updateList(List<Feature> features) {
    ListFragment.this.features.clear();
    ListFragment.this.features.addAll(features);
    adapter.notifyDataSetChanged();
  }
}
