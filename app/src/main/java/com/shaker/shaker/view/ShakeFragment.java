package com.shaker.shaker.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.shaker.shaker.R;
import com.shaker.shaker.model.entity.Properties;
import java.util.Date;

public class ShakeFragment extends Fragment {

  private static final String KEY = "key";

  static ShakeFragment newInstance(Properties properties) {
    ShakeFragment fragment = new ShakeFragment();
    Bundle bundle = new Bundle();
    bundle.putParcelable(KEY, properties);
    fragment.setArguments(bundle);
    return fragment;
  }

  /**
   *
   * @param inflater
   * @param container
   * @param savedInstanceState
   * @return
   */
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    Properties properties = getArguments().getParcelable(KEY);
    View view = inflater.inflate(R.layout.shake_fragment, container, false);
    TextView placeText = view.findViewById(R.id.place_text);
    TextView timeText = view.findViewById(R.id.time_text);
    TextView statusText = view.findViewById(R.id.status_text);
    TextView magText = view.findViewById(R.id.mag_text);
    TextView nst = view.findViewById(R.id.nst);
    nst.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(getActivity(), "yup", Toast.LENGTH_LONG).show();
        Snackbar.make(v, "NST SELECTED", Snackbar.LENGTH_LONG).show();
      }
    });
    TextView nstText = view.findViewById(R.id.nst_text);
    TextView alertText = view.findViewById(R.id.alert_text);
    TextView urlText = view.findViewById(R.id.url_text);
    placeText.setText(properties.getPlace());
    timeText.setText(new Date(properties.getTime()).toString());
    statusText.setText(properties.getStatus());
    magText.setText(properties.getMag().toString());
    if (properties.getNst() == null) {
      nstText.setText("N/A");
    } else {
      nstText.setText(properties.getNst().toString());
    }
    if (properties.getAlert() == null) {
      alertText.setText("None");
    } else {
      alertText.setText(properties.getAlert());
    }
    String html = "<a href='" + properties.getUrl() + "'> Link </a>";
    urlText.setText(Html.fromHtml(html));
    return view;
  }
}
