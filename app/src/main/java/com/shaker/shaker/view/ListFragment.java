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
import com.shaker.shaker.model.database.DataBase;
import com.shaker.shaker.model.entity.Quake;
import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass. Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

  private String text;
  private RecyclerView shakesView;
  private RecyclerView.Adapter adapter;
  private RecyclerView.LayoutManager mLayoutManager;
  private List<Quake> quakes;

  public static ListFragment newInstance(List<Quake> quakes) {
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
    List<Quake> sInfoText = new LinkedList<>();
    quakes = new LinkedList<>();
    sInfoText.addAll(quakes);
    View view = inflater.inflate(R.layout.list_fragment, container, false);
    shakesView = view.findViewById(R.id.recyclerview);
    mLayoutManager = new LinearLayoutManager(getActivity());
    shakesView.setLayoutManager(mLayoutManager);
    adapter = new RecyclerViewAdapter(quakes, getActivity());
    shakesView.setAdapter(adapter);
    ((MainActivity) getActivity()).queryQuakes(new QueryCallback() {
      @Override
      public void consume(List<Quake> quakes) {
        ListFragment.this.quakes.clear();
        ListFragment.this.quakes.addAll(quakes);
        adapter.notifyDataSetChanged();
      }
    });
    return view;
  }
// TODO Change info text to be more clear.
}
