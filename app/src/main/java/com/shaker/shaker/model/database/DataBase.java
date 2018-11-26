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
import com.shaker.shaker.model.database.DataBase.Converters;
import com.shaker.shaker.model.entity.Feature;
import com.shaker.shaker.model.entity.Geometry;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * The type Data base.
 */
@Database(
    entities = {Feature.class},
    version = 1,
    exportSchema = true
)
//TODO export schema for documentation
@TypeConverters(Converters.class)
public abstract class DataBase extends RoomDatabase {

  private static final String DB_NAME = "database";
  private static DataBase instance = null;

  /**
   * Gets instance.
   *
   * @param context the context
   * @return the instance
   */
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
                  Executors.newSingleThreadScheduledExecutor().execute(() -> {
                  });
                }
              })
          .build();
    }
    return instance;
  }

  /**
   * Forget instance.
   */
  public synchronized static void forgetInstance() {
    instance = null;
  }

  /**
   * Convert cords.
   *
   * @param features the features
   */
  public static void convertCords(List<Feature> features) {
    int i;
    for (i = 0; i < features.size(); i++) {
      Feature feature = features.get(i);
      Geometry geometry = feature.getGeometry();
      List<Double> coordinates = geometry.getCoordinates();
      geometry.setLongitude(coordinates.get(0));
      geometry.setLatitude(coordinates.get(1));
      geometry.setDepth(coordinates.get(2));
    }
  }

  /**
   * Gets feature dao.
   *
   * @return the feature dao
   */
  public abstract FeatureDao getFeatureDao();

  /**
   * The type Converters.
   */
  public static class Converters {

    /**
     * Date from long date.
     *
     * @param time the time
     * @return the date
     */
    @TypeConverter
    public static Date dateFromLong(Long time) {
      return (time != null) ? new Date(time) : null;
    }

    /**
     * Long from date long.
     *
     * @param date the date
     * @return the long
     */
    @TypeConverter
    public static Long longFromDate(Date date) {
      return (date != null) ? date.getTime() : null;
    }
  }
}
