package com.shaker.shaker.controller;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shaker.shaker.R;
import com.shaker.shaker.model.database.DataBase;
import com.shaker.shaker.model.entity.Feature;
import com.shaker.shaker.model.entity.Geometry;
import com.shaker.shaker.model.entity.Properties;
import com.shaker.shaker.model.entity.Shake;
import com.shaker.shaker.service.ShakerService;
import com.shaker.shaker.view.FilterFragment;
import com.shaker.shaker.view.ListFragment;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
    implements OnNavigationItemSelectedListener, OnMapReadyCallback {

  private static final String TAG = "tag";

  private SupportMapFragment mapFragment;
  private ShakerService service;
  private QuakeTask quakeTask;
  private List<Feature> features;
  private ProgressBar spinner;
  DataBase database;
  Context context;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupUI();
    setupService();
    database = DataBase.getInstance(this);
    quakeTask = new QuakeTask();
    quakeTask.execute();
    Toast.makeText(this, "Please wait while we connect...", Toast.LENGTH_LONG).show();
  }

  private void setupService() {
    Gson gson = new GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        // TODO set date format for date specific requests
        .create();
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(getString(R.string.base_url))
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();
    service = retrofit.create(ShakerService.class);
  }

  private void setupMap() {
    Toast.makeText(this, "Connected to server...", Toast.LENGTH_LONG).show();
    mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.map);
    if (mapFragment == null) {
      mapFragment = new SupportMapFragment();
      getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mapFragment)
          .commit();
    }
    mapFragment.getMapAsync(this);
  }

  private void setupUI() {
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    spinner = findViewById(R.id.spinner);
    spinner.setVisibility(View.GONE);
  }

  public void queryShakes(QueryCallback callback) {
    new QueryTask(callback).execute();
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
    transaction.setTransition(4097);
    transaction.commit();
  }

  private void addFragment(Fragment fragment, boolean useStack, String varient) {
    FragmentManager manager = getSupportFragmentManager();
    String tag = fragment.getClass().getSimpleName() + ((varient != null) ? varient : "");
    if (manager.findFragmentByTag(tag) != null) {
      manager.popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.add(R.id.fragment_container, fragment, tag);
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
    int i;
    if (features != null) {
      for (i = 0; i < features.size(); i++) {
        Feature feature = features.get(i);
        Geometry geometry = feature.getGeometry();
        Properties properties = feature.getProperties();
        LatLng Shake = new LatLng(geometry.getLatitude(), geometry.getLongitude());
        map.addMarker(new MarkerOptions()
            .position(Shake)
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ring))
            .snippet(properties.getTitle())
            .title(new Date(properties.getTime()).toString()));
      }
    }
    LatLng Albuquerque = new LatLng(35.0844, -106.6504);
    map.addMarker(new MarkerOptions()
        .position(Albuquerque)
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        .title(getString(R.string.abq_pin))
        .snippet(getString(R.string.shaker_made_here)));
    map.moveCamera(CameraUpdateFactory.newLatLng(Albuquerque));
    spinner.setVisibility(View.GONE);
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
//    noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    String varient = null;
    int id = item.getItemId();
    if (id == R.id.nav_map) {
      switchFragment(mapFragment, true, null);
    } else if (id == R.id.nav_list) {
      switchFragment(ListFragment.newInstance(), true, null);
      Toast.makeText(this, "List View", Toast.LENGTH_LONG).show();
    } else if (id == R.id.nav_create) {
      //TODO handle nav create
    } else if (id == R.id.nav_filter) {
      addFragment(FilterFragment.newInstance(), true, null);
    } else if (id == R.id.filter_date) {

    } else if (id == R.id.filter_mag) {

    }
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  public interface QueryCallback<T> {

    void consume(List<T> features);
  }

  private class QueryTask extends AsyncTask<Void, Void, List<Feature>> {

    private QueryCallback callback;

    public QueryTask(QueryCallback callback) {
      this.callback = callback;
    }

    @Override
    protected void onPostExecute(List<Feature> features) {
      if (callback != null) {
        callback.consume(features);
      }
    }

    @Override
    protected List<Feature> doInBackground(Void... voids) {
      DataBase.getInstance(context);
      return database.getFeatureDao().select();
    }
  }

  public class QuakeTask extends AsyncTask<Void, Void, List<Feature>> {

    @Override
    protected void onPreExecute() {
      spinner.setVisibility(View.VISIBLE);
    }

    @Override
    protected List<Feature> doInBackground(Void... voids) {
      List<Feature> features = null;
      try {
        Response<Shake> response = service.get().execute();
        Shake shake = response.body();
        features = shake.getFeatures();
        DataBase.convertCords(features);
        database.getFeatureDao().insert(features);
      } catch (Exception e) {
        Log.d(TAG, e.getLocalizedMessage() + "caught exception ");
      }
      return features;
    }

    @Override
    protected void onPostExecute(List<Feature> features) {
      queryShakes(new QueryCallback() {
        @Override
        public void consume(List features) {
          MainActivity.this.features = new ArrayList<>();
          MainActivity.this.features.addAll(features);
          setupMap();
        }
      });
    }

    @Override
    protected void onCancelled() {
      super.onCancelled();
      Log.d(TAG, "onCancelled called");
    }
  }
}

