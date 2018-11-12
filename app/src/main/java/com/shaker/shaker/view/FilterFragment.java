package com.shaker.shaker.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.shaker.shaker.R;


public class FilterFragment extends Fragment {

  TextView text;
  Button todayButton;
  Context context;

  public static FilterFragment newInstance() {
    FilterFragment fragment = new FilterFragment();
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    context = getContext();
    View view = inflater.inflate(R.layout.filter_fragment, container, false);
    text = view.findViewById(R.id.today);
    todayButton = view.findViewById(R.id.today_button);
    todayButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
    return view;

  }
}
