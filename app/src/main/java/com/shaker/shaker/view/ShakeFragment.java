package com.shaker.shaker.view;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    Properties properties = getArguments().getParcelable(KEY);
    View view = inflater.inflate(R.layout.shake_fragment, container, false);

    TextView placeText = view.findViewById(R.id.place_text);
    placeText.setText(properties.getPlace());

    TextView timeText = view.findViewById(R.id.time_text);
    timeText.setText(new Date(properties.getTime()).toString());

    TextView statusText = view.findViewById(R.id.status_text);
    statusText.setText(properties.getStatus());
    TextView status = view.findViewById(R.id.status);
    status.setOnClickListener(v -> Snackbar.make(v,
        " Automatic events are posted by automatic systems, Reviewed events have been looked at by a human.",
        Snackbar.LENGTH_LONG).show());

    TextView magText = view.findViewById(R.id.mag_text);
    magText.setText(properties.getMag().toString());

    TextView nstText = view.findViewById(R.id.nst_text);
    TextView nst = view.findViewById(R.id.nst);
    if (properties.getNst() == null) {
      nstText.setText("N/A");
    } else {
      nstText.setText(properties.getNst().toString());
    }
    nst.setOnClickListener(v -> Snackbar.make(v,
        "Number of seismic stations which reported P- and S-arrival times for this earthquake.",
        Snackbar.LENGTH_LONG).show());

    TextView alertText = view.findViewById(R.id.alert_text);
    TextView alert = view.findViewById(R.id.alert);
    if (properties.getAlert() == null) {
      alertText.setText("None");
    } else {
      alertText.setText(properties.getAlert());
    }
    alert.setOnClickListener(v -> Snackbar
        .make(v, "Typically “green”, “yellow”, “orange”, or “red” see USGS site for more info.",
            Snackbar.LENGTH_LONG).show());




    TextView urlText = view.findViewById(R.id.url_text);
    String html = "<a href='" + properties.getUrl() + "'> Link </a>";
    if (VERSION.SDK_INT < VERSION_CODES.N) {
      urlText.setText(Html.fromHtml(html));
    } else {
      urlText.setText(Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT));
    }
    urlText.setMovementMethod(LinkMovementMethod.getInstance());
    return view;
  }
}
