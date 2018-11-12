package com.shaker.shaker.model.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;
import com.shaker.shaker.model.dao.FeatureDao;
import com.shaker.shaker.model.dao.PinDao;
import com.shaker.shaker.model.database.DataBase.Converters;
import com.shaker.shaker.model.entity.Feature;
import com.shaker.shaker.model.entity.Geometry;
import com.shaker.shaker.model.entity.Pin;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

@Database(
    entities = {Feature.class, Pin.class},
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters.class)
public abstract class DataBase extends RoomDatabase {

  private static final String DB_NAME = "database";
  private static DataBase instance = null;


  public synchronized static DataBase getInstance(final Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context.getApplicationContext(), DataBase.class, DB_NAME)
          .addCallback(
              new Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                  super.onOpen(db);
                }

                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                  super.onCreate(db);
                  Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                    }
                  });
                }
              })
          .build();

    }
    return instance;
  }

  public synchronized static void forgetInstance() {
    instance = null;
  }

  public static List<Feature> convertCords(List<Feature> features) {
    int i;
    for (i = 0; i < features.size(); i++) {
      Feature feature = features.get(i);
      Geometry geometry = feature.getGeometry();
      List<Double> coordinates = geometry.getCoordinates();
      geometry.setLongitude(coordinates.get(0));
      geometry.setLatitude(coordinates.get(1));
      geometry.setDepth(coordinates.get(2));
    }
    return features;
  }

  public abstract PinDao getPinDao();

  public abstract FeatureDao getFeatureDao();

  public static class Converters {

//    @TypeConverter
//    private static String stringFromCord(Geometry geometry) {
//      String cords;
//      cords = geometry.getCoordinates().get(0).toString() + " " +
//          geometry.getCoordinates().get(1).toString() + " " +
//          geometry.getCoordinates().get(2).toString();
//      return cords;
//    }
//
//    @TypeConverter
//    Geometry geoFromString(String string) {
//      Geometry geometry = new Geometry();
//
//      String[] cords = string.split(" ");
//      ArrayList<Double> coordinates = new ArrayList<>();
//
//      coordinates.add(Double.parseDouble(cords[0]));
//      coordinates.add(Double.parseDouble(cords[0]));
//      coordinates.add(Double.parseDouble(cords[0]));
//
//      geometry.setCoordinates(coordinates);
//
//      return geometry;
//   }

    @TypeConverter
    public static Date dateFromLong(Long time) {
      return (time != null) ? new Date(time) : null;
    }

    @TypeConverter
    public static Long longFromDate(Date date) {
      return (date != null) ? date.getTime() : null;
    }
  }


}
