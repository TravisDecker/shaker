package com.shaker.shaker;

import android.app.Application;
import com.facebook.stetho.Stetho;

public class ShakerApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
  }
}
