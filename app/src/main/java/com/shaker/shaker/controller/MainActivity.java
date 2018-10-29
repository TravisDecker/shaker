package com.shaker.shaker.controller;


import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.shaker.shaker.model.database.DataBase;
import com.shaker.shaker.model.entity.Quake;
import com.shaker.shaker.view.ListFragment;
import com.shaker.shaker.R;
import com.shaker.shaker.view.RecyclerViewAdapter;
import java.util.List;


public class MainActivity extends AppCompatActivity
    implements OnNavigationItemSelectedListener, OnMapReadyCallback {

  private static final String TAG = "tag";
  private SupportMapFragment mapFragment;
  private RecyclerViewAdapter adapter;
  private DataBase database;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.map);
    if (mapFragment == null) {
      mapFragment = new SupportMapFragment();
      getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mapFragment)
          .commit();
    }
    mapFragment.getMapAsync(this);

  }

  private void switchFragment(Fragment fragment, boolean useStack, String varient) {
    FragmentManager manager = getSupportFragmentManager();
    String tag = fragment.getClass().getSimpleName() + ((varient != null) ? varient : "");
    if (manager.findFragmentByTag(tag) != null) {
      manager.popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.replace(R.id.fragment_container, fragment, tag);
    if (useStack) {
      transaction.addToBackStack(tag);
    }

    transaction.commit();
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    GoogleMap map = googleMap;

    try {
      // Customise the styling of the base map using a JSON object defined
      // in a raw resource file.
      boolean success = googleMap.setMapStyle(
          MapStyleOptions.loadRawResourceStyle(
              this, R.raw.style_json));

      if (!success) {
        Log.e(TAG, "Style parsing failed.");
      }
    } catch (Resources.NotFoundException e) {
      Log.e(TAG, "Can't find style. Error: ", e);
    }

    // Add a marker in Sydney and move the camera
    LatLng Albuquerque = new LatLng(35.0844, -106.6504);
    map.addMarker(new MarkerOptions()
        .position(Albuquerque)
        .title("Shaker was made here in Albuquerque"));
    map.moveCamera(CameraUpdateFactory.newLatLng(Albuquerque));
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
//       Handle action bar item clicks here. The action bar will
//       automatically handle clicks on the Home/Up button, so long
//      as you specify a parent activity in AndroidManifest.xml
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    String varient = null;
    int id = item.getItemId();

    if (id == R.id.nav_map) {
      switchFragment(mapFragment, true, null);
    } else if (id == R.id.nav_list) {
      switchFragment(ListFragment.newInstance(), true, null);
    } else if (id == R.id.nav_create) {

    } else if (id == R.id.nav_manage) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  private class QueryTask extends AsyncTask<Void, Void, List<Quake>> {

    @Override
    protected void onPostExecute(List<Quake> quakes) {
      quakes.clear();
      quakes.addAll(quakes);
      adapter.notifyDataSetChanged();
    }

    @Override
    protected List<Quake> doInBackground(Void... voids) {
      return database.getQuakeDao().select();
    }
  }
}

