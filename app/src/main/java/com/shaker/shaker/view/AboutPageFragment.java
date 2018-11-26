package com.shaker.shaker.view;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.shaker.shaker.R;

/**
 * The type About page fragment.
 */
public class AboutPageFragment extends Fragment {

  private static AboutPageFragment fragment;

  /**
   * New instance about page fragment.
   *
   * @return the about page fragment
   */
  public static AboutPageFragment newInstance() {
    if (fragment == null) {
      fragment = new AboutPageFragment();
    }
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.about_page_fragment, container, false);

    TextView usgsLink = view.findViewById(R.id.usgs_link);
    String usgsHTML = getString(R.string.usgs_link);
    if (VERSION.SDK_INT < VERSION_CODES.N) {
      usgsLink.setText(Html.fromHtml(usgsHTML));
    } else {
      usgsLink.setText(Html.fromHtml(usgsHTML, Html.FROM_HTML_MODE_COMPACT));
    }
    usgsLink.setMovementMethod(LinkMovementMethod.getInstance());

    TextView licenseLink = view.findViewById(R.id.lic_link);
    String licHTML = getString(R.string.lic_link);
    if (VERSION.SDK_INT < VERSION_CODES.N) {
      licenseLink.setText(Html.fromHtml(licHTML));
    } else {
      licenseLink.setText(Html.fromHtml(licHTML, Html.FROM_HTML_MODE_COMPACT));
    }
    licenseLink.setMovementMethod(LinkMovementMethod.getInstance());

    return view;
  }
}
