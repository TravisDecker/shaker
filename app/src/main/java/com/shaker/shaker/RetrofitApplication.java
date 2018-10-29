package com.shaker.shaker;

import android.app.Application;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApplication extends Application {

  private static RetrofitApplication instance;
  private Retrofit retrofit;
  // private RetrofitService service;

  @Override
  public void onCreate() {
    super.onCreate();
    // Setup singleton instance
    instance = this;
    // Setup Retrofit
    retrofit = new Retrofit.Builder()
        .baseUrl(getString(R.string.base_url))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();

//    service = retrofit.create(RetrofitService.class);
  }

  public static RetrofitApplication getInstance() {
    return instance;
  }

  public Retrofit getRetrofit() {
    return retrofit;
  }

  // public RetrofitService getService() { return service; }
}