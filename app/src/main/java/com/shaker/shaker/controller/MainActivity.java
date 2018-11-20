package com.shaker.shaker.controller;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
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
import com.shaker.shaker.view.ListFragment;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
    implements OnNavigationItemSelectedListener, OnMapReadyCallback {

  private static final String TAG = "tag";

  private SupportMapFragment mapFragment;
  private ShakerService service;
  private List<Feature> features;
  private ProgressBar progressbar;
  private Spinner spinner;
  private DataBase database;
  private GoogleMap map;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupUI();
    setupService();
    database = DataBase.getInstance(this);
    queryShakes(features -> {
      MainActivity.this.features = new ArrayList<>();
      MainActivity.this.features.addAll(features);
      MainActivity.this.setupMap();
    }, getFromSharedPrefs("string"));
    ShakeTask shakeTask = new ShakeTask();
    shakeTask.execute();
  }

  private void setupService() {
    Gson gson = new GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .create();
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(getString(R.string.base_url))
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();
    service = retrofit.create(ShakerService.class);
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

    progressbar = findViewById(R.id.progress_bar);
    progressbar.setVisibility(View.GONE);
  }

  private void setupMap() {
    mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.map);
    if (mapFragment == null) {
      mapFragment = new SupportMapFragment();
      getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mapFragment)
          .commit();
    }
    mapFragment.getMapAsync(this);
  }

  private void saveToSharedPref(String filter, int filterId) {
    SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString(getString(R.string.string_key), filter);
    editor.putInt(getString(R.string.int_key), filterId);
    editor.apply();
  }

  private String getFromSharedPrefs(String variant) {
    SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
    String result = "";
    switch (variant) {
      case "int":
        int temp;
        temp = sharedPreferences.getInt(getString(R.string.int_key), 0);
        result = Integer.toString(temp);
        break;
      case "string":
        result = sharedPreferences.getString(getString(R.string.string_key), "12 Hours");
        break;
    }
    return result;
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
    map = googleMap;
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
    addPins(map);
  }

  public void addPins(GoogleMap map) {
    if (map != null) {
      map.clear();
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
          .icon(BitmapDescriptorFactory.fromResource(R.drawable.abqpin))
          .title(getString(R.string.abq_pin))
          .snippet(getString(R.string.shaker_made_here)));
      map.moveCamera(CameraUpdateFactory.newLatLng(Albuquerque));
    }
  }

  private long getTime() {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    long timestampStartDay = calendar.getTimeInMillis();
    return timestampStartDay;
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
    getMenuInflater().inflate(R.menu.main, menu);
    MenuItem item = menu.findItem(R.id.filter_spinner);
    MainActivity.this.spinner = (Spinner) item.getActionView();
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.spinner_list_item_array, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);

    String selection = getFromSharedPrefs("int");
    spinner.setSelection(Integer.parseInt(selection));
    spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(MainActivity.this, "Applying Filter...", Toast.LENGTH_SHORT).show();
        queryShakes(features -> {
          // MainActivity.this.features = new ArrayList<>();
          MainActivity.this.features.clear();
          MainActivity.this.features.addAll(features);
          addPins(MainActivity.this.map);
        }, spinner.getSelectedItem().toString());
        if (ListFragment.fragment != null) {
          if (ListFragment.fragment.isVisible()) {
            Fragment fragment = ListFragment.newInstance(null);
            ((ListFragment) fragment).updateList(features);
          }
        }
      }
      @Override
      public void onNothingSelected(AdapterView<?> parent) {
        //Do Nothing, Spinner selects item on start.
      }
    });
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
    String varient = null;
    int id = item.getItemId();
    if (id == R.id.nav_map) {
      switchFragment(mapFragment, true, null);
    } else if (id == R.id.nav_list) {
      switchFragment(ListFragment.newInstance(spinner.getSelectedItem().toString()), true, null);
    } else if (id == R.id.nav_create) {
      //TODO handle nav create
    } else if (id == R.id.nav_about) {
      //TODO handle nav about
    }
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  public void queryShakes(QueryCallback callback, String filter) {
    new QueryTask(callback).execute(filter);
  }

  public interface QueryCallback<T> {

    void consume(List<T> features);
  }

  @Override
  protected void onDestroy() {
    saveToSharedPref(spinner.getSelectedItem().toString(), spinner.getSelectedItemPosition());
    super.onDestroy();
  }

  public class ShakeTask extends AsyncTask<Void, Void, List<Feature>> {

    private Exception exception;


    @Override
    protected void onPreExecute() {
      progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    protected List<Feature> doInBackground(Void... voids) {
      List<Feature> features = null;

      try {
        Response<Shake> response = service.get().execute();
        Shake shake = response.body();
        features = shake.getFeatures();
      } catch (UnknownHostException e) {
        Log.d(TAG, e.getLocalizedMessage());
        exception = e;
        cancel(true);
      } catch (IOException e) {
        Log.d(TAG, e.getLocalizedMessage());
        exception = e;
        cancel(true);
      }
      try {
        DataBase.convertCords(features);
        database.getFeatureDao().insert(features);
      } catch (Exception e) {
        Log.d(TAG, e.getLocalizedMessage());
      }
      return features;
    }

    @Override
    protected void onPostExecute(List<Feature> features) {
      addPins(map);
    }

    @Override
    protected void onCancelled() {
      super.onCancelled();
      Log.d(TAG, "onCancelled called");
      if (exception instanceof UnknownHostException) {
        Toast.makeText(MainActivity.this, "Unable to connect to server.. check internet connection",
            Toast.LENGTH_LONG).show();
      } else {
        //FIXME do what?
      }

    }
  }

  private class QueryTask extends AsyncTask<String, Void, List<Feature>> {

    private QueryCallback callback;

    public QueryTask(QueryCallback callback) {
      this.callback = callback;
    }

    @Override
    protected void onPostExecute(List<Feature> features) {
      if (callback != null) {
        callback.consume(features);
        Toast.makeText(MainActivity.this, Integer.toString(features.size()) + " Events",
            Toast.LENGTH_LONG)
            .show();
      }
    }

    @Override
    protected List<Feature> doInBackground(String... strings) {
      List<Feature> features = new ArrayList<>();
      String filter = strings[0];
      DataBase.getInstance(MainActivity.this);
      switch (filter) {
        case "12 Hours":
          features = (database.getFeatureDao().select12(getTime()));
          break;
        case "24 Hours":
          features = (database.getFeatureDao().select24(getTime()));
          break;
        case "30 Days":
          features = (database.getFeatureDao().select30(getTime()));
          break;
        case "None":
          features = (database.getFeatureDao().select());
          break;
      }
      return features;
    }
  }
}



