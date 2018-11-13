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

  public static FilterFragment newInstance() {
    FilterFragment fragment = new FilterFragment();
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    Context context = getContext();
    View view = inflater.inflate(R.layout.filter_fragment, container, false);
    TextView text = view.findViewById(R.id.today);
    Button todayButton = view.findViewById(R.id.today_button);
    todayButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
      }
    });
    return view;
  }
}
