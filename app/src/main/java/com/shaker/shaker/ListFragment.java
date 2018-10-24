package com.shaker.shaker;

import android.support.v4.app.FragmentActivity;
import java.util.ArrayList;

public class ListFragment extends FragmentActivity {

  ArrayList<String> Shakes;

  private void addShakes() {
    Shakes.add("This is shake info");
  }

  private void displayShakes() {
    findViewById(R.id.list_fragment);
  }


}
