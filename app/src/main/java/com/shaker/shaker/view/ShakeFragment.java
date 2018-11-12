package com.shaker.shaker.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.shaker.shaker.R;
import com.shaker.shaker.model.entity.Properties;
import java.util.Date;

public class ShakeFragment extends Fragment {


  private static final String KEY = "key";
  private static String TAG = "tag";
  TextView placeText;
  TextView timeText;
  TextView statusText;
  TextView urlText;

  public static ShakeFragment newInstance(Properties properties) {
    ShakeFragment fragment = new ShakeFragment();
    Bundle bundle = new Bundle();
    bundle.putParcelable(KEY, properties);
    fragment.setArguments(bundle);

    return fragment;
  }


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    Properties properties = getArguments().getParcelable(KEY);
    View view = inflater.inflate(R.layout.shake_fragment, container, false);
    placeText = view.findViewById(R.id.place_text);
    timeText = view.findViewById(R.id.time_text);
    statusText = view.findViewById(R.id.status_text);
    urlText = view.findViewById(R.id.url_text);
    placeText.setText(properties.getPlace());
    timeText.setText(new Date(properties.getTime()).toString());
    statusText.setText(properties.getStatus());
    urlText.setText(properties.getUrl());
    return view;

  }
}
