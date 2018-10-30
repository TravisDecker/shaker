package com.shaker.shaker.view;


import android.arch.persistence.room.RoomDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shaker.shaker.R;
import com.shaker.shaker.controller.MainActivity;
import com.shaker.shaker.model.database.DataBase;
import com.shaker.shaker.model.entity.Quake;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass. Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

  private String text;
  private RecyclerView shakesView;
  private RecyclerView.Adapter adapter;
  private RecyclerView.LayoutManager mLayoutManager;

  private DataBase database;


  private ArrayList<String> sInfoText = new ArrayList<>();
  private ArrayList<String> sMoreInfoText = new ArrayList<>();


  public ListFragment() {
    // Required empty public constructor
  }

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
    DataBase.getInstance(getActivity());

    sInfoText.add("");
    sMoreInfoText.add("world");
    View view = inflater.inflate(R.layout.list_fragment, container, false);
    shakesView = view.findViewById(R.id.recyclerview);
    mLayoutManager = new LinearLayoutManager(getActivity());
    shakesView.setLayoutManager(mLayoutManager);
    adapter = new RecyclerViewAdapter(sInfoText, sMoreInfoText, getActivity());
    shakesView.setAdapter(adapter);
    return view;
  }

}
