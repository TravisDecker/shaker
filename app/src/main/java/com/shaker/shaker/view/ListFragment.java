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


/**
 * A simple {@link Fragment} subclass. Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

  private RecyclerView shakesView;
  private RecyclerView.Adapter adapter;
  private RecyclerView.LayoutManager mLayoutManager;
  private List<Feature> features;

  public static ListFragment newInstance() {
    ListFragment fragment = new ListFragment();
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    features = new ArrayList<>();

    View view = inflater.inflate(R.layout.list_fragment, container, false);
    shakesView = view.findViewById(R.id.recyclerview);
    mLayoutManager = new LinearLayoutManager(getActivity());
    shakesView.setLayoutManager(mLayoutManager);
    adapter = new RecyclerViewAdapter(features, getActivity(), this);
    shakesView.setAdapter(adapter);
    ((MainActivity) getActivity()).queryShakes(new QueryCallback<Feature>() {
      @Override
      public void consume(List<Feature> features) {
        ListFragment.this.features.clear();
        ListFragment.this.features.addAll(features);
        adapter.notifyDataSetChanged();
      }
    });
    return view;
  }

}
