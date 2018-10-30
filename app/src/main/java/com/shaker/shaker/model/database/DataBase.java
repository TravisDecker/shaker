package com.shaker.shaker.model.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import com.shaker.shaker.model.dao.PinDao;
import com.shaker.shaker.model.dao.QuakeDao;
import com.shaker.shaker.model.database.DataBase.Converters;
import com.shaker.shaker.model.entity.Pin;
import com.shaker.shaker.model.entity.Quake;
import java.util.Date;

@Database(
    entities = {Quake.class, Pin.class},
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters.class)
public abstract class DataBase extends RoomDatabase {

  private static final String DB_NAME = "database";

  private static DataBase instance = null;

  public abstract QuakeDao getQuakeDao();

  public abstract PinDao getPinDao();

  public synchronized static DataBase getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context.getApplicationContext(), DataBase.class, DB_NAME)
          .build();

    }
    return instance;
  }

  public synchronized static void forgetInstance() {
    instance = null;
  }

  public static class Converters {

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
